package jdbc;

import java.util.List;

public class Star {
	private int id;
	private String firstName;
	private String lastName;
	private String dob;
	private String photoUrl;
	private List<Movie> movies;
	
	
	
	
	public Star() {
		super();
	}

	public Star(int id, String firstName, String lastName, 
			String dob, String photoUrl, List<Movie> movies) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.photoUrl = photoUrl;
		this.movies = movies;
	}
	public Star(int id, String firstName, String lastName, 
			String dob, String photoUrl) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.photoUrl = photoUrl;

	}

	


	public Star(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public List<Movie> getMovie() {
		return movies;
	}

	public void setMovie(List<Movie> movies) {
		this.movies = movies;
	}

	
	
}
