<%@ page import = "java.util.*, jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Single Star </title>
	
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

 <div id="wrapper">
		<div id="header">
			<h2>Star</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
			<table>
				<tr>
					<th> Poster</th>
					<th> Id </th>
					<th> Name </th>
					<th> DOB </th>
					<th> Movies </th>
					
				</tr>
				
				<tr> 
				<!--  -->
				<td><img src="${STAR.photoUrl}" width="200" height="200" alt="Image Unavailable" id="moviePoster"/>
			</td>
					<td> ${STAR.id} </td>
					<td> ${STAR.name} </td>
					<td> ${STAR.dob} </td>
					<td> <c:forEach var = "movies" items = "${STAR.movie}"> 
						 <a href = "SingleMovieServlet?movieId=${movies.id}">	${movies.title} <br/> </a>
						</c:forEach>
					 </td>
				</tr>
				
			</table>
		</div>
	</div>
<body>


</body>
</html>
