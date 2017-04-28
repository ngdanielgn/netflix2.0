

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
<body>
<form action= MovieListServlet method= "GET">
	Sort by: <select name="sort">
				<option value = "Title"> Title </option>
				<option value = "Year"> Year </option>
			</select>
			
			<select name="order">
				<option value  = "ASC"> Ascending </option>
				<option value = "DESC"> Descending </option>
			</select>
			<br/>
	Results per page: <select name="limit">
				<option value = 10> 10 </option>
				<option value = 25> 25 </option>
				<option value = 50> 50 </option>
				<option value = 100> 100 </option>
			</select>
		<input type = "submit" value = "Sort" />
	</form>



	<div id="container">
		<div id="content">
		
			<table>
				<tr>
				
					<th> Id </th>
					<th> Title </th>
					<th> Director </th>
					<th> Year </th>
					<th> Stars </th>
					<th> Genres </th>
					<th> </th>
					
				</tr> 
				<c:forEach var = "tempMovie" items = "${MOVIE_LIST}">
					<tr>
						<td>${tempMovie.id} </td>
						<td> <a href = "SingleMovieServlet?movieId=${tempMovie.id}"> ${tempMovie.title} </a> </td>
						<td>${tempMovie.director} </td>
						<td>${tempMovie.year} </td>
						
						<td>   <c:forEach var = "stars" items = "${tempMovie.star}"> 
								<a href = "SingleStarServlet?starId=${stars.id}">	${stars.firstName} ${stars.lastName} <br/> </a>
								</c:forEach>
						</td>
						<td> <c:forEach var = "genres" items = "${tempMovie.genres}"> 
								${genres.name} <br/>
								</c:forEach>
						</td>
						
						<td> 
							<form action="FabflixControllerServlet" method="GET">
							<button type="submit" name="command" value="${tempMovie.id}">Add to Cart</button>
							</form>
						</td>
						 
						 <td> 
					</tr>
				
				
				</c:forEach>
				
			</table>
		</div>
	</div>

<c:if test="${page ne 1}">
		<a href="MovieListServlet?page=${page - 1}">
			<button>Previous</button>
		</a>
	</c:if>
<c:if test="${page lt maxPage}">
		<a href="MovieListServlet?page=${page + 1}">
			<button>Next</button>
		</a>
</c:if>
</body>
</html>
