package jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;
public class MoviedbUtil {
	
	private DataSource dataSource;
	
	public MoviedbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
		
	}
	
	public List<Movie> getMovies() throws Exception {
		
		List<Movie> movies = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String nextTitle= "";
		String fullStarName = "";
		List<Star> star = new ArrayList<Star>();
		List<Integer> tempStarId = new ArrayList<Integer>();
		List<String> genreList = new ArrayList<String>();
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			
			
			// create sql statement
			String sql = "select m.id, m.title, m.year, m.director, s.first_name,"
					+ "s.last_name, s.id AS s_Id, g.name from movies m, genres_in_movies gim, stars s,"
					+ " stars_in_movies sim, genres g where "
					+ "(sim.star_id = s.id and sim.movie_id = m.id and g.id = gim.genre_id "
					+ "and gim.movie_id = m.id)";
			
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			        ResultSet.CONCUR_READ_ONLY);
			
			// execute query
			myRs = myStmt.executeQuery(sql);
//			ResultSet nextRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// Moves RS cursor forward, used to check if current RS row is dupilcate
				if (!myRs.isLast()) {
				myRs.next();
				nextTitle = myRs.getString("title");
				System.out.print(nextTitle);
				myRs.previous();
				}
				else {
					nextTitle = "";
				}
				

				String title = myRs.getString("title");
				System.out.print(title);
				int id = myRs.getInt("id");
				int sId = myRs.getInt("s_Id");
				int year = myRs.getInt("year");
				String director = myRs.getString("director");
				String genres = myRs.getString("name");
				
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				fullStarName = firstName + " " + lastName;
				if(!tempStarId.contains(sId)) {
				//if (!fullStarName.contains(firstName) && !fullStarName.contains(lastName)) {
					//fullStarName += firstName + " " + lastName + "<br/>";
					Star tempStar = new Star(sId, fullStarName);
					star.add(tempStar);
					tempStarId.add(sId);
				}
				
				if (!genreList.contains(genres)) {
					genreList.add(genres);
				}
					
					
			// create new student object
				if(!title.equals(nextTitle)){
//					Movie tempMovie = new Movie(id, title, director, year, fullStarName, genreList);
					Movie tempMovie = new Movie(id, title, director, year, star, genreList);
					
					// add it to the list of students
					movies.add(tempMovie);	
					fullStarName  = "";
					tempStarId = new ArrayList<Integer>();
					genreList = new ArrayList<String>();
					star = new ArrayList<Star>();
				}
			}
			
			return movies;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	
	public Movie getSingleMovie(String movieid) throws Exception {
		
		Movie singleMovie = new Movie();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String nextTitle= "";
		String fullStarName = "";
		List<Star> star = new ArrayList<Star>();
		List<Integer> tempStarId = new ArrayList<Integer>();
		List<String> genreList = new ArrayList<String>();
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
		
			
			String sql =String.format("select m.id, m.title, m.year, m.director,s.id AS s_Id, s.first_name,"
					+ "s.last_name, g.name, m.banner_url, m.trailer_url from movies m, genres_in_movies gim, stars s,"
					+ " stars_in_movies sim, genres g where "
					+ "(sim.star_id = s.id and sim.movie_id = m.id and g.id = gim.genre_id "
					+ "and gim.movie_id = m.id and m.id = '%s')",movieid);
			
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			        ResultSet.CONCUR_READ_ONLY);
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
					
					
					if (!myRs.isLast()) {
					myRs.next();
					nextTitle = myRs.getString("title");
					System.out.print(nextTitle);
					myRs.previous();
					}
					else{
						nextTitle = "";
					}
					

					String title = myRs.getString("title");
					int id = myRs.getInt("id");
					int year = myRs.getInt("year");
					int sId = myRs.getInt("S_Id");
					String director = myRs.getString("director");
					String genres = myRs.getString("name");
					String firstName = myRs.getString("first_name");
					String lastName = myRs.getString("last_name");
					String banner_url = myRs.getString("banner_url");
					String trailer_url = myRs.getString("trailer_url");
					
					
					fullStarName = firstName + " " + lastName;
					if(!tempStarId.contains(sId)) {
						Star tempStar = new Star(sId, fullStarName);
						star.add(tempStar);
						tempStarId.add(sId);
						
					}
					
					if (!genreList.contains(genres)) {
						genreList.add(genres);
					}
						
						
				// create new student object
					if(!title.equals(nextTitle)){
//						Movie tempMovie = new Movie(id, title, director, year, fullStarName, genreList);
						 singleMovie = new Movie(id, title, director, year,banner_url, trailer_url, genreList, star);
//						 singleMovie = new Movie(124, "jlj", "asdas", 11,"9999", "901", star, genreList);
						
						// add it to the list of students
						fullStarName  = "";
						star = new ArrayList<Star>();
						genreList = new ArrayList<String>();
						tempStarId = new ArrayList<Integer>();
					}
				}
			
			
			
			
			return singleMovie;
		}
			finally {
				// close JDBC objects
				close(myConn, myStmt, myRs);
			}
	}
	
public Star getSingleStar(String starId) throws Exception {
		
		Star star = new Star();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String nextTitle= "";
		String fullStarName = "";
		List<Movie> movie = new ArrayList<Movie>();
		List<String> titleList = new ArrayList<String>();
		List<Integer> movieIdList = new ArrayList<Integer>();
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
		
			
			String sql =String.format("select s.id, s.dob, s.first_name, s.last_name, s.photo_url, m.id AS m_Id, m.title "
					+ "from movies m, stars s, stars_in_movies sim "
					+ "where sim.star_id = s.id and sim.movie_id = m.id and s.id = '%s'",starId);
			
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			        ResultSet.CONCUR_READ_ONLY);
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
					
					
					if (!myRs.isLast()) {
					myRs.next();
					nextTitle = myRs.getString("title");
					myRs.previous();
					}
					else{
						nextTitle = "";
					}
					

					String title = myRs.getString("title");
					int id = myRs.getInt("id");
					int mId = myRs.getInt("m_Id");
					String firstName = myRs.getString("first_name");
					String lastName = myRs.getString("last_name");
					String dob = myRs.getString("dob");
					String photoUrl = myRs.getString("photo_url");
					fullStarName = firstName + " " + lastName; 	
					
					if(!titleList.contains(title)) {
						titleList.add(title);
						Movie tempMovie = new Movie(mId, title);
						movie.add(tempMovie);
					}
					
				// create new student object
					if(!title.equals(nextTitle)){
//						Movie tempMovie = new Movie(id, title, director, year, fullStarName, genreList);
						 star = new Star(id, fullStarName, dob, photoUrl, movie);
//						 singleMovie = new Movie(124, "jlj", "asdas", 11,"9999", "901", star, genreList);
						
						// add it to the list of students
						
					}
				}
			
			
			
			
			return star;
		}
			finally {
				// close JDBC objects
				close(myConn, myStmt, myRs);
			}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}