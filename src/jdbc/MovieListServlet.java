package jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javax.servlet.RequestDispatcher;
//import org.apache.catalina.servlet4preview.RequestDispatcher;

/**
 * Servlet implementation class MovieListServlet
 */
@WebServlet("/MovieListServlet")
@MultipartConfig
public class MovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MoviedbUtil moviedbUtil;
	@Resource(name="jdbc/moviedb")
	private DataSource dataSource;
	private HttpSession session;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			moviedbUtil = new MoviedbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			listMovies(request, response);	

			
		
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void listMovies(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		session = request.getSession();

		Integer limit = null;
		Integer page = null;
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String browseTitle = null;
		String browseGenre = null;
		String titleSort = null;
		String genreName = null;

		
		if(request.getParameter("genreName") !=null){
			genreName = request.getParameter("genreName");
			session.setAttribute("browseGenre", genreName );
			//Sets the browseTitle to null to keep genre browsing
			session.removeAttribute("browseTitle"); 
			
		}
		else if(request.getParameter("titleSort")!=null){
			titleSort = request.getParameter("titleSort");
			session.setAttribute("browseTitle",titleSort);
			//Sets the browseGenre to null to keep title browsing
			session.removeAttribute("browseGenre"); 
		}
		
		
		if ( request.getParameter("limit") != null){
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit",limit);
		}
		else{
			limit = 10;
		}
		if ( request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}
		else{
			page = 1;
			session.setAttribute("page",page);		
		}
		
		browseTitle = (String) session.getAttribute("browseTitle");
		browseGenre = (String) session.getAttribute("browseGenre");
		
		
		List<Movie> movies = moviedbUtil.getMovies(sort, order, limit, page,  browseTitle, browseGenre);
		request.setAttribute("MOVIE_LIST", movies);
		request.setAttribute("maxPage", (int)Math.ceil(movies.size() / limit));
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/movie-list.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
