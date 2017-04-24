package jdbc;

import java.util.List;

public class Star {
	private int id;
	private String name;
	private String dob;
	private String photoUrl;
	private List<String> title;
	private List<String> movieId;
	
	public Star(int id, String name, String dob, String photoUrl, List<String> title, List<String> movieId) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.photoUrl = photoUrl;
		this.title = title;
		this.movieId = movieId;
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

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getMovieId() {
		return movieId;
	}

	public void setMovieId(List<String> movieId) {
		this.movieId = movieId;
	}
	
	
}
