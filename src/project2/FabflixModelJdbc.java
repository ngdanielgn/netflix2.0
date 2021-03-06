// Brian Huynh
package project2;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
  

public class FabflixModelJdbc {
	
	private DataSource dataSource;
	boolean [] status = {false, false, false, false, false};
	int holder = 1;

	public FabflixModelJdbc(DataSource dataSource) {
		/* 
		 * Critical for connecting the mySQL database
		 * and tomcat in eclipse. 
		 */
		this.dataSource = dataSource;
	}
	
	private void close(Connection connection, 
			Statement statement, ResultSet result) {
		/* Closes connections to the database. */
		try {
			if (connection != null)
				connection.close();
			if (statement != null)
				statement.close();
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String[] stripSpaces(String sentence) {
		/* Splits a string by '', " ", \t, or \n as delimiters. */
		return sentence.split("\\s+");
	}
	
	private boolean hasPattern(String regex, String str) {
		/* 
		 * Returns true if the regex pattern is found 
		 * in str otherwise return false. 
		 */ 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/*private String createGeneralSearchQuery(String keywords) {

		//System.out.println(keywords + "1");
		String query = null;
		if (keywords == null || keywords.equalsIgnoreCase(""))
			return null;
		else {
			query = "SELECT DISTINCT m.id, m.title, m.year, m.director, "
					+ "m.banner_url, m.trailer_url "
					+ "FROM movies m, stars s, stars_in_movies sim "
					+ "WHERE ";
			
			List<String> subqueries = new ArrayList<String>(Arrays.asList(
					"m.title LIKE",
					"m.year =",
					"m.director LIKE",
					"s.id = sim.star_id AND m.id = sim.movie_id AND s.first_name LIKE",
					"s.id = sim.star_id AND m.id = sim.movie_id AND s.last_name LIKE"
					));

			List<String> result = new ArrayList<String>();
			
			String[] words = stripSpaces(keywords);
			for (String q : subqueries) {
				String temp = "";
				for (int i = 0; i < words.length; ++i) {
					if (temp.equalsIgnoreCase("") 
							&& subqueries.indexOf(q) == 1 
							&& hasPattern("^[0-9]+$", words[i]))
						temp += String.format("%s %s", q, words[i]);
					
					else if (subqueries.indexOf(q) == 1 
							&& hasPattern("^[0-9]+$", words[i]))
						temp += String.format(" OR %s %s", q, words[i]);
					
					else if (temp.equalsIgnoreCase("") 
							&& subqueries.indexOf(q) > 1 
							&& hasPattern("^[a-zA-Z\\-]+$", words[i]))
						temp += String.format("%s '%%%s%%'", q, words[i]);
					
					else if (subqueries.indexOf(q) > 1 
							&& hasPattern("^[a-zA-Z\\-]+$", words[i]))
						temp += String.format(" OR %s '%%%s%%'", q, words[i]);
					
					else if (temp.equalsIgnoreCase("") 
							&& subqueries.indexOf(q) == 0)
						temp += String.format("%s '%%%s%%'", q, words[i]);
			
					else if (subqueries.indexOf(q) == 0)
						temp += String.format(" OR %s '%%%s%%'", q, words[i]);
				}
				if (!temp.equalsIgnoreCase(""))
					result.add(temp);
			}
			
			for (String q : result) {
				if (result.indexOf(q) == 0)
					query += q;
				else
					query += String.format(" OR %s", q);
			}

		}

		return query;
	}
	*/
	private String createSearchQuery(String title, String year, String director, 
								String firstName, String lastName, Connection connection) throws SQLException {
		/* 
		 * Dynamically generates mySQL query strings
		 * covering 120 (5!) possible search combinations. 
		 */
		//System.out.println("2");
		holder = 1;
		String query = null;
		String[] argv = {title, year, director, firstName, lastName};
		String[] queries = {
				"m.title LIKE ?", //title
				"m.year = ?", //year
				"m.director LIKE ?", //director
				"s.first_name LIKE ? AND "
						+ "m.id = sim.movie_id AND s.id = sim.star_id", // first
				"s.last_name LIKE ? AND "
						+ "m.id = sim.movie_id AND s.id = sim.star_id" //last
				};

		for(int j = 0; j<status.length;j++)
		{
			status[j] = false;
		}
		/* 
		 * Input values are null on initialization 
		 * but are "" after 'submit' button is pressed.
		 * Be aware that calling equalsIgnoreCase() on a 
		 * variable of type String that assigned to null
		 * will yield to a NullPointerException.
		 */
		if ((title == null && year == null && director == null 
				&& firstName == null && lastName == null) 
				|| (title.equalsIgnoreCase("") && year.equalsIgnoreCase("") 
				&& director.equalsIgnoreCase("") && firstName.equalsIgnoreCase("") 
				&& lastName.equalsIgnoreCase("")))
			return null;
		
		else if (firstName.equalsIgnoreCase("") && lastName.equalsIgnoreCase(""))
			query = "SELECT * "
					+ "FROM movies m "
					+ "WHERE ";
		else
			query = "SELECT m.id, m.title, m.year, m.director, "
					+ "m.banner_url, m.trailer_url "
					+ "FROM movies m, stars s, stars_in_movies sim "
					+ "WHERE ";
		
		boolean firstAttribute = true;
		for (int i = 0; i < argv.length; ++i) {
			if (!argv[i].equalsIgnoreCase("") && firstAttribute) {
				query += queries[i];
				firstAttribute = false;
				status[i] = true;
				holder++;
			}
			else if (!argv[i].equalsIgnoreCase("")) {
				query += " AND " + queries[i];
				status[i] = true;
				holder++;
			}
		}
		//System.out.println(query);

		// query gets created here
		
		return query;
	}
	
	private List<Genre> movieGenres(int movieId) throws Exception {
		/* 
		 * Returns a list of Genre objects corresponding to the movie
		 * queried by its movieId. 
		 */
		
		//not sure where this is being called from
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement PStat = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "SELECT g.id, g.name "
						+ "FROM genres_in_movies gim, genres g "
						+ "WHERE gim.movie_id = ? AND "
						+ "gim.genre_id = g.id";
			PStat = connection.prepareStatement(query);
			PStat.setInt(1,movieId);
			result = PStat.executeQuery();
			while (result.next()) {
				genres.add(new Genre(result.getInt("id"), 
								result.getString("name"))
						);
			}
			
		} finally {
			close(connection, PStat, result);
		}
		
		return genres;
	}
	
	private List<Movie> starringIn(int starId) throws Exception {
		List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement PStat = null;

		try {
			connection = dataSource.getConnection();
			//statement = connection.createStatement();
			String query ="SELECT m.id, m.title, m.year, m.director, "
										+ "m.banner_url, m.trailer_url "
										+ "FROM movies m, stars_in_movies sim "
										+ "WHERE m.id = sim.movie_id "
										+ "AND sim.star_id = ?";
			PStat = connection.prepareStatement(query);
			PStat.setInt(1,starId);
			result = PStat.executeQuery();
			
			//result = statement.executeQuery(query);
			while (result.next()) {
				movies.add(new Movie(result.getInt("id"), 
									result.getString("title"))
									);
				
			}
		} finally {
			close(connection, PStat, result);
		}
		
		return movies;
	}
	
	private List<Star> movieStars(int movieId) throws Exception {
		/*
		 * Returns a list of Star objects corresponding to the movie 
		 * queried by its movieId.
		 */
		
		List<Star> stars = new ArrayList<Star>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement PStat = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "SELECT s.id, s.first_name, s.last_name, "
										+ "s.dob, s.photo_url "
										+ "FROM stars s, stars_in_movies sim "
										+ "WHERE s.id = sim.star_id AND "
										+ "sim.movie_id = ?";
			PStat = connection.prepareStatement(query);
			PStat.setInt(1,movieId);
			result = PStat.executeQuery();
			
			while (result.next()) {
				int starId = result.getInt("id");
				stars.add(new Star(starId,
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getString("dob"), 
						result.getString("photo_url"),
						starringIn(starId))
						);
				
			}
			
		} finally {
			close(connection, PStat, result);
		}
		
		return stars;
	}
	
	private List<Movie> movieList(ResultSet result) throws Exception {
		/* Helper function populating a list of movies returned from a query. */
		
		List<Movie> movies = new ArrayList<Movie>();
		while (result.next()) {
			int movieId = result.getInt("id");
			movies.add(new Movie(movieId, 
					result.getString("title"),
					result.getInt("year"), 
					result.getString("director"), 
					result.getString("banner_url"), 
					result.getString("trailer_url"),
					movieGenres(movieId), 
					movieStars(movieId))
					);
		}
		return movies;
	}
	
	/*public List<Movie> moviesByKeywords(String keywords) throws Exception {
		

		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			query = this.createGeneralSearchQuery(keywords);
			
			if (query != null) {
				result = statement.executeQuery(query);
				return movieList(result);
			}
		} finally {
			close(connection, statement, result);
		}
		return null;
	}*/
	
	public List<Movie> searchMovies(String title, String year, 
			String director, String firstName, String lastName) throws Exception {
		
		/* 
		 * Establishes an individual connection to the database 
		 * and retrieves records from the database that match 
		 * the given mySQL query. 
		 */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = null;
		PreparedStatement PStat = null;
		int placement = 1;

		try {
			connection = dataSource.getConnection();


			//statement = connection.createStatement();
			query = this.createSearchQuery(title, year, director, firstName, lastName,connection);
			
			if (query != null) {
				PStat = connection.prepareStatement(query);
				for(int k = 0; k < status.length; k++)
				{
					//System.out.println(k +" fn "+status[k]);

				}
				for(int o = 0; o<5;o++)
				{	
					if(status[o] == true && o == 0)
					{
						//title
						//System.out.println("title "+placement);

			            PStat.setString((placement),"%"+ title + "%");
			           // System.out.println(o);
						placement++;

					}
					else if(status[o] == true && o == 1)
					{
						//year
						//System.out.println("dyear "+placement);

			            PStat.setString(placement,year);
			         //   System.out.println(o);
						placement++;

					}
					else if(status[o] == true && o == 2)
					{
						//director
						//System.out.println("durectir "+placement);
			            PStat.setString(placement,"%"+ director + "%");
			            System.out.println(o);
						placement++;

					}
					else if(status[o] == true && o == 3)
					{
						//first name
						//System.out.println("fn "+placement);

			            PStat.setString(placement,"%"+ firstName + "%");
			            //System.out.println(o);
						placement++;

					}
					else if(status[o] == true && o == 4)
					{
						//System.out.println("ln "+placement);

			            PStat.setString(placement,"%"+ lastName + "%");
			           // System.out.println(o);
						placement++;

					}
				}
				//System.out.println("tunnnn");

				result = PStat.executeQuery();
				return movieList(result);
			}
			
		} finally {
			close(connection, PStat, result);
		}
		return null;
	}

	public boolean processPayment(String firstName, String lastName, 
								String ccid, String expDate) throws Exception {
		/* Returns true if the customer entered a valid credit card. */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement PStat = null;

		if (firstName == null || lastName == null || ccid == null || expDate == null || 
				firstName.equalsIgnoreCase("") || lastName.equalsIgnoreCase("") || 
				ccid.equalsIgnoreCase("") || expDate.equalsIgnoreCase(""))
			return false;
			
		String query ="SELECT cc.first_name, cc.last_name "
				+ "FROM creditcards cc, customers cust "
				+ "WHERE cc.first_name = ? AND cc.last_name = ? "
				+ "AND cc.id = ? AND cc.expiration = ?";
		
		//firstName , lastName, ccid, expDate
		try {
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setString(1,firstName);
			PStat.setString(2,lastName);
			PStat.setString(3, ccid);
			PStat.setString(4,expDate);
			result = PStat.executeQuery();
			return result.next();		// returns true if ResultSet is not empty

		} finally {
			close(connection, PStat, result);
		}
	}

	public Movie getMovie(String movieId) throws Exception{
		/* 
		 * Gets a single movie from the database by movieId
		 * and returns a new Movie object. 
		 */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Movie movie = null;
		PreparedStatement PStat = null;

		if (movieId == null)
			return null;
		
		try {
			String query = "SELECT * "
					+ "FROM movies m WHERE m.id = ?";
			
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setString(1,movieId);
			result = PStat.executeQuery();
			
			if (result.next()) {
				movie = new Movie(Integer.parseInt(movieId), 
						result.getString("title"),
						result.getInt("year"), 
						result.getString("director"), 
						result.getString("banner_url"), 
						result.getString("trailer_url"), 
						movieGenres(Integer.parseInt(movieId)), 
						movieStars(Integer.parseInt(movieId)));
			}
		} finally {
			close(connection, PStat, result);
		}
		return movie;
	}

	public Customer login(String email, String password) throws Exception {
		/* Implemented login function that returns a Customer object. */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Customer customer = null;
		PreparedStatement PStat = null;

		if (email == null || password == null)
			return null;
		
		try {
			String query = "SELECT * "
										+ "FROM customers c "
										+ "WHERE c.email = ? AND c.password = ?";
			
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setString(1,email);
			PStat.setString(2,password);
			result = PStat.executeQuery();
			
			if (result.next()) {
				customer = new Customer(result.getInt("id"), 
										result.getString("first_name"),
										result.getString("last_name"), 
										result.getString("cc_id"),
										result.getString("address"), 
										email);
			}
			
		} finally {
			close(connection, PStat, result);
		}
		
		return customer;
	}
	
	public Employee employeeLogin(String email, String password) throws Exception {
		/* Implemented login function that returns a Customer object. */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Employee employee = null;
		PreparedStatement PStat = null;

		if (email == null || password == null)
			return null;
		
		try {
			String query = "SELECT * "
										+ "FROM employees e "
										+ "WHERE e.email = ? AND e.password = ?";
			
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setString(1,email);
			PStat.setString(2,password);
			result = PStat.executeQuery();
			
			if (result.next()) {
				employee = new Employee(result.getString("email"), 
										result.getString("password"),
										result.getString("fullname"));
			}
			
		} finally {
			close(connection, PStat, result);
		}
		
		return employee;
	}

	public int addSale(int customerId, int movieId, String currentDate) throws Exception {
		/* 
		 * Inserts a new sale into the "sales" table, 
		 * returning the number of records updated if successful. 
		 */
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Sale sale = new Sale(0, customerId, movieId, currentDate);		// sale ID is auto-incremented in mySQL, hence the 0	
		int newRecords = 0;
		PreparedStatement PStat = null;

		try {
			String query = "INSERT INTO sales VALUES(?, ?, ?, ?)";
			
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setInt(1,sale.getId());
			PStat.setInt(2,sale.getCustomerId());
			PStat.setInt(3,sale.getMovieId());
			PStat.setString(4,sale.getSaleDate());
			PStat.executeUpdate();
			
		} finally {
			close(connection, PStat, result);
		}
		
		return newRecords;
	}
	
	public void addStar(String firstName, String lastName, String dob, String photoUrl) throws SQLException{
		/*Adds a star to the database*/
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = null;
		PreparedStatement PStat = null;

		
		try {
			if (dob.equals("") && photoUrl.equals("")) {
				 query = "INSERT INTO stars VALUES(0, ?, ?, null, null)";
				 //execute update
				connection = dataSource.getConnection();
				PStat = connection.prepareStatement(query);

				PStat.setString(1,firstName);
				PStat.setString(2,lastName);
				PStat.executeUpdate();
			}
			else if(dob.equals("")){
				 query = "INSERT INTO stars VALUES(0, ?, ?, null, ?)";
				connection = dataSource.getConnection();
				PStat = connection.prepareStatement(query);
				PStat.setString(1,firstName);
				PStat.setString(2,lastName);
				PStat.setString(3,photoUrl);
				PStat.executeUpdate();
			}
			
			else if(photoUrl.equals("")) {
				query = "INSERT INTO stars VALUES(0, ?, ?, ?, null)";
				connection = dataSource.getConnection();
				PStat = connection.prepareStatement(query);
				PStat.setString(1,firstName);
				PStat.setString(2,lastName);
				PStat.setString(3,dob);
				PStat.executeUpdate();
			}
			else {
				query = "INSERT INTO stars VALUES(0, ?, ?, ?, ?)";
				connection = dataSource.getConnection();
				PStat = connection.prepareStatement(query);
				PStat.setString(1,firstName);
				PStat.setString(2,lastName);
				PStat.setString(3,dob);
				PStat.setString(4,photoUrl);
				PStat.executeUpdate();
			}
				


			
		} finally {
			close(connection, PStat, result);
		}
		
	}
	
	public List<String> getMetadata() throws Exception {		
		/* Provide the metadata of the database; 
		 * in particular, print out the name of each table and, 
		 * for each table, each attribute and its type. */
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String tableMeta = null;
		String format = null;
		List<String> metaDataList = new ArrayList<>();
		String[] TABLES = {
				"movies", "stars","employees", "stars_in_movies",
				"genres", "genres_in_movies", "customers",
				"sales", "creditcards"
				};
//		List<String>[] meta = {movies};
		
		try {
			connection = dataSource.getConnection();
			
			DatabaseMetaData metadata = connection.getMetaData();
			
			for (int i = 0; i < TABLES.length; ++i) {
				ResultSet columns = metadata.getColumns(null, null, TABLES[i], "%");
				format = "";
				format = format.concat(TABLES[i] + "-- " + '\n');
				while (columns.next()) {
					 tableMeta = columns.getString(4) + ": " 
										+ columns.getString(6) + "    ";
					 format = format.concat(tableMeta + " " + "||" + '\t');
					 format = format.concat(" ");
						// column name : mySQL data type
				}
				
				metaDataList.add(format);
			}
			return metaDataList;
		}
		finally {
			close(connection, statement, result);
		}

	}
	
	public List<Sale> getSales(int customerId) throws Exception {
		/* Returns a list of movies bought by a customer. */
		
		List<Sale> sales = new ArrayList<Sale>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement PStat = null;

		try {
			String query ="SELECT * "
										+ "FROM sales s "
										+ "WHERE s.customer_id = ? "
										+ "ORDER BY s.sale_date DESC";
			
			
			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setInt(1,customerId);
			result = PStat.executeQuery();
			
			while (result.next()) {
				sales.add(new Sale(result.getInt("id"),
									result.getInt("customer_id"),
									result.getInt("movie_id"),
									result.getString("sale_date"))
							);
			}
			
			return sales;
			
		} finally {
			close(connection, PStat, result);
		}
		
	}
	
	public int addMovie(String title, int year, String director, String starFirstName,
							String starLastName, String genre) throws Exception {
		/*Adds a movie to the database*/
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement PStat = null;

		int movieAddorUpdateCheck = 0;
		try{
			String query = "Call add_movie(?, ?, ?, ?, ?, ?);";
			

			connection = dataSource.getConnection();
			PStat = connection.prepareStatement(query);
			PStat.setString(1,title);
			PStat.setInt(2,year);
			PStat.setString(3,director);
			PStat.setString(4,starFirstName);
			PStat.setString(5,starLastName);
			PStat.setString(6,genre);
			result = PStat.executeQuery();
			
			while(result.next()) {
				movieAddorUpdateCheck = result.getInt("answer");
			}
			
			return movieAddorUpdateCheck;
		}
		finally {
			close(connection, PStat, result);
		}
	}
	

}
