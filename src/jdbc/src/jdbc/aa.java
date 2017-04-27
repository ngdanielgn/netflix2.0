package jdbc;

import java.util.ArrayList;
import java.util.List;

public class aa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Movie> listA = new ArrayList<Movie>();
		List<Movie> listB = new ArrayList<Movie>();
		Movie a = new Movie(1, "opop");
		Movie b = new Movie(2, "abc");
		listA.add(a);
		listA.add(b);
		
		listB.add(listA.get(0));

		listB.add(listA.get(1));
		for(Movie movies: listB){
			System.out.println(movies.getId());
			movies.getTitle();
		}
		
		
	}

}
