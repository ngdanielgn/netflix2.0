

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Movie List </title>
	
	
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
				
					<th> Id </th>
					<th> Title </th>
					<th> Year </th>
					<th> Director </th>
					<th> Stars </th>
					<th> Genres </th>
					
				</tr>
				<c:forEach var = "tempMovie" items = "${MOVIE_LIST}">
					<tr>
						<td>${tempMovie.id} </td>
						<td> <a href = "SingleMovieServlet?movieid=${tempMovie.id}"> ${tempMovie.title} </a> </td>
						<td>${tempMovie.director} </td>
						<td>${tempMovie.year} </td>
						
						<td>   <c:forEach var = "stars" items = "${tempMovie.star}"> 
									${stars}
								</c:forEach>
						</td>
						<td>${tempMovie.genres} </td>
					</tr>
				
				
				</c:forEach>
				
			</table>
		</div>
	</div>
<body>


</body>
</html>
