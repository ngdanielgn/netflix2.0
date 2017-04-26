package jdbc;

import java.util.List;

public class Star {
	private int id;
	private String name;
	private String dob;
	private String photoUrl;
	private List<Movie> movie;
	
	
	
	
	public Star() {
		super();
	}

	public Star(int id, String name, String dob, String photoUrl, List<Movie> movie) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.photoUrl = photoUrl;
		this.movie = movie;
	}



	public Star(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}

	
	
}
