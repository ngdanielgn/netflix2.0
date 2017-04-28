package jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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



	@SuppressWarnings("unchecked")
	private void listMovies(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		session = request.getSession();

		Integer limit = null;
		Integer page = null;
		Integer maxPage = null;
		String sort = null;
		String order = null;
		System.out.println("BEGINNING :" + sort + order);
		String browseTitle = null;
		String browseGenre = null;
		String titleSort = null;
		String genreName = null;
		List<Movie> newMovieList = new ArrayList<Movie>();
		List<Movie> movies = new ArrayList<Movie>();

		
		
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
		else if(session.getAttribute("limit") !=null)
			limit = (Integer) session.getAttribute("limit");
		else
			limit = 10;
		
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
		
		if(session.getAttribute("MOVIES") !=null || request.getAttribute("titleSort") !=  null||
				request.getAttribute("genreName") !=null){
			
			movies = (List<Movie>) session.getAttribute("MOVIES");
		}
		else if (request.getAttribute("titleSort") !=  null||
				request.getAttribute("genreName") !=null) {
		
			movies = moviedbUtil.getMovies(sort, order, limit, page,  browseTitle, browseGenre);
			session.setAttribute("MOVIES", movies);
		}
		else{
			movies = moviedbUtil.getMovies(sort, order, limit, page,  browseTitle, browseGenre);
			session.setAttribute("MOVIES", movies);
		}
		
		
		if(request.getAttribute("maxPage") != null){
			
		}
		else{
			double movieListSize = movies.size();
			maxPage = (int) Math.ceil(movieListSize / limit);
		}
		
		
		if (session.getAttribute("sort") != null && session.getAttribute("order") !=null){
			sort = (String) session.getAttribute("sort");
			order = (String) session.getAttribute("order");
		}
		else if(request.getParameter("sort")!= null && request.getParameter("order") !=null){
		
			sort = request.getParameter("sort");
			order = request.getParameter("order");
			session.setAttribute("sort", sort);
			session.setAttribute("order", order);
		}

		Comparator<Movie> COMPARATOR = new Comparator<Movie>()
	    {
			
	    // This is where the sorting happens.
			
	        public int compare(Movie o1, Movie o2)
	        {	
	        	
	        	//Sorts by Year ASC, if 2 movies the same year sort by title
	        	if (sort!=null && order!=null ){
	        		System.out.println("TESTING COMAPRE");
		            if (sort.equals("year") && order.equals("ASC")) {
		            	int yearDifference = o1.getYear() - o2.getYear();
		            	if (yearDifference == 0){
		            		
		            		System.out.println("TESTING YEAR TIE");
		            		return o1.getTitle().compareTo(o2.getTitle()); 
		            	}
		            	else
		            	{System.out.println("TESTING YEAR ASC");
		            		return yearDifference;
		            	}
		            }		
		          //Sorts by Year DESC, if 2 movies the same year sort by title
		            if (sort.equals("year") && order.equals("DESC")){
		            	int yearDifference = o2.getYear() - o1.getYear();
		            	if (yearDifference == 0)
		            		return o1.getTitle().compareTo(o2.getTitle());
		            	else
		            		return yearDifference;
		            }
		            //Sorts by Title ASC .....
		            if (sort.equals("title") && order.equals("ASC")){
		            	if (!o1.getTitle().equals(o2.getTitle())) {
		            		return o1.getTitle().compareTo(o2.getTitle());
		            	}
		            	else return o1.getYear() - o2.getYear();
		            }
		            
		            //Sorts by Title DESC....
		            if (sort.equals("title") && order.equals("DESC")){
		            	if (!o2.getTitle().equals(o1.getTitle())) {
		            		return o2.getTitle().compareTo(o1.getTitle());
		            	}
		            	else return o1.getYear() - o2.getYear();
		            }
	        	}
	            //Return Title ASC by default
	            return o1.getTitle().compareTo(o2.getTitle());
        	}
	    };
	    //Sorts by title/year if option chosen
	    	Collections.sort(movies, COMPARATOR);
		
	    
	    newMovieList = new ArrayList<Movie>();
		int offset = (page - 1) * limit;
		
		//pagination to select n movies from movies
		for(int i = 0; i < limit && offset < movies.size() ; i++) {
			if (movies.get(offset) != null){
				newMovieList.add(movies.get(offset));
				offset++;
			}
		}
		request.setAttribute("MOVIE_LIST", newMovieList);
		request.setAttribute("maxPage", maxPage);System.out.println(movies.size());
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/movie-list.jsp");
				dispatcher.forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
