package project2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;



@WebServlet("/AutoSearch")
public class AutoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	@Resource(name="jdbc/moviedb") 		// Critical for connecting the SQL database and tomcat in eclipse.
	private DataSource dataSource;
	
    /**
     * @throws NamingException 
     * @see HttpServlet#HttpServlet()
     */
    public AutoSearch() throws NamingException {
        super();
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/moviedb");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Storage, Words;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		PreparedStatement PStat = null;
		int hold = 1;

			try {
				connection= dataSource.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Words = request.getParameter("Words");
			
			if (Words == null) 
			{
				return;
			}
			//start tokenizing and create sql statement
			ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(Words.split(" ")));
			System.out.println(keywords);
			Storage = createMatches(keywords);
			String sql = "SELECT movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url FROM movies " + Storage + " ORDER BY movies.title ASC;";
			//System.out.println(sql);
			//execute statement
			//System.out.println(sql);
			try {
				//Statement searchStatement = (Statement) connection.createStatement();
				//if last do prefix
				PStat = connection.prepareStatement(sql);

				for(String token: keywords)
				{
					if (!token.equals("") && !token.contains(" "))
					{
			            if (keywords.indexOf(token) == keywords.size() - 1) {
							token = token.replace("'", "''");
							PStat.setString(hold, "'"+ token + "*'");
						}
						else {
							token = token.replace("'", "''");
							PStat.setString(hold,"'"+ token+ "'");
						}
			            hold++;
					}
					
				}
				ResultSet resultSet = PStat.executeQuery();
				
				while (resultSet.next())
				{				
					Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
					movies.add(movie);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
			request.setAttribute("movies", movies);
			RequestDispatcher dispatcher =  request.getRequestDispatcher("DropDown.jsp");
			dispatcher.forward(request, response);
	}
	public String createMatches(ArrayList<String> keywords)
	{	
		String Storage = " WHERE ";
		//System.out.println(keywords.size());
		for (String token: keywords)
		{	
			if (!token.equals("") && !token.contains(" "))
			{		
				
				if (keywords.indexOf(token) == keywords.size() - 1) {

					Storage += "MATCH (movies.title) AGAINST (? IN BOOLEAN MODE)";
				}
				else {
					Storage += "MATCH (movies.title) AGAINST (? IN BOOLEAN MODE) AND ";
				}
			}
		}
		return Storage;
		
	}

}
