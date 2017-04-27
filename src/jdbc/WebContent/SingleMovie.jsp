<%@ page import = "java.util.*, jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Single Movie </title>
	
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

 <div id="wrapper">
		<div id="header">
			<h2>Movie List</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
			<table>
				<tr>
					<th> Poster</th>
					<th> Id </th>
					<th> Title </th>
					<th> Year </th>
					<th> Director </th>
					<th> Stars </th>
					<th> Genres </th>
					
				</tr>
				
				<tr> 
				<!--  -->
					<td> <img src="${MOVIE.banner_url}" width="200" height="200" alt="Image Unavailable" id="moviePoster"/> </td>
				
					<td> ${MOVIE.id} </td>
					<td> ${MOVIE.title} </td>
					<td> ${MOVIE.year} </td>
					<td> ${MOVIE.director} </td>
					
					<td> <c:forEach var = "stars" items = "${MOVIE.star}"> 
								<a href = "SingleStarServlet?starId=${stars.id}">	${stars.name} <br/> </a>
								</c:forEach>
					 </td>
								
					<td> <c:forEach var = "genres" items = "${MOVIE.genres}">
						 	<a href = "MovieListServlet?genreName=${genres.name}"> ${genres.name} <br/> </a>
						 	</c:forEach>
				 	 </td>
					
					
				</tr>
				
			</table>
			<table>
				<tr>
					<th> Trailer </th>
				</tr>
				
				<tr>
					<td> <c:out value = "${MOVIE.trailer_url}" default = "Trailer Unavailable">  </c:out></td>
				</tr>
			
			</table>
		</div>
	</div>
<body>


</body>
</html>
