<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "project2.*" %>

<link href="Style/movie_card.css" rel="stylesheet" />

<table>
  	<tr>
    	
		<td>
			<h3 id="movieCardAnchor">${movie.title}</h3>
			<p><img src="${movie.bannerUrl}" width="200" height="200" alt="Image Unavailable" id="moviePoster"/></p>
			<p>Year Released: ${movie.year}</p>
				<c:forEach items="${movie.stars}" var="star">
					<a href="SingleStarServlet?starId=${star.id}">${star.firstName} ${star.lastName}</a>, 
				</c:forEach>
			
			
			<form action="FabflixControllerServlet" method="GET">
				<input type="hidden" name="movieId" value="${movie.id}">
				<button class="btn button btn-success btn-sm" type="submit" name="command" value="addToCart">Add to Cart</button>
			</form>
    	</td>
	</tr>
</table>