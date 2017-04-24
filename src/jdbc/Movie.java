package jdbc;

import java.util.List;
import java.util.Vector;

public class Movie {

	private int id;
	private String title;
	private String director;
	private int year;
	private int starId;
	private String banner_url;
	private String trailer_url;
	private List<String> genres;
	private List<String> star;
	

	public Movie() {
		super();
	}


	public Movie(int id, String title, String director, int year, int starId, List<String> star, List<String> genres) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.year = year;
		this.starId = starId;
		this.star = star;
		this.genres = genres;
	}

	
	public Movie(int id, String title, String director, int year, String banner_url, String trailer_url,
			List<String> genres, List<String> star) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.year = year;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		this.genres = genres;
		this.star = star;
	}


	/*
	public Movie(int id, String title, String director, int year, String stars, String genres) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.year = year;
		this.stars = stars;
		this.genres = genres;
	}
	
	public Movie(int id, String title, String director, int year) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.year = year;
	}
		*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

//	public String getGenres() {
//		return genres;
//	}
//
//	
//	public void setGenres(String genres) {
//		this.genres = genres;
//	}
	public List<String> getStar() {
		return star;
	}

	public void setStar(List<String> star) {
		this.star = star;
	}
	public List<String> getGenres() {
		return genres;
	}


	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
	public String getBanner_url() {
		return banner_url;
	}


	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}


	public String getTrailer_url() {
		return trailer_url;
	}


	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}


	public int getStarId() {
		return starId;
	}


	public void setStarId(int starId) {
		this.starId = starId;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", stars="
				+ ", genres=" + genres + "]";
	}

}
