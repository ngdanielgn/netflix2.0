<%@ page import = "project2.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Full screen background cover page. - Bootsnipp.com</title>
    
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="main-page.css">
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="movie-popup.js"></script>



</head>


<style>
/* Popup container - can be anything you want */


.tooltip {
    position: relative;
    display: inline-block;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: #555;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}
</style>



<body>


	<div class="site-wrapper">
	  <div class="site-wrapper-inner">
	    <div class="cover-container">
	      <div class="masthead clearfix">
	        <div class="inner">
	          <h3 class="masthead-brand">C I N E P H I M</h3>
	
	          <ul class="nav masthead-nav">	
		        <li>
	              <a href="main-page.jsp">Home</a>
	            </li>
	                    
	            <li>
	              <a href="search-view.jsp">Search</a>
	            </li>
	            
	         	<li>
	              <a href="Browse.jsp">Browse</a>
	            </li>
	
	            <li>
	              <a href="shopping-cart-view.jsp">Shopping Cart</a>
	            </li>
	            
	           	<li>
	              <a href="checkout-view.jsp">Checkout</a>
	            </li>
	            

	          </ul>
	        </div>
	      </div>

			<div class="row cover-container options-padding text-color">
			
			
				<div class="row">
						<form action="MovieListServlet" method="GET">
							<select name="sort">
								<option value = "title"> Title </option>
								<option value = "year"> Year </option>
							</select>
									
							<select name="order">
								<option value  = "ASC"> Ascending </option>
								<option value = "DESC"> Descending </option>
							</select>
							<input class="btn btn-success btn-sm" type = "submit" value = "Sort" />
						</form>
	
	
						<form action="MovieListServlet" method="GET">		
							<select name="limit">
								<option value = 10> 10 </option>
								<option value = 25> 25 </option>
								<option value = 50> 50 </option>
								<option value = 100> 100 </option>
							</select>
							<input class="btn btn-success btn-sm" type = "submit" value="Sort"/>
						</form>
	
					
				</div>
			</div>
			
			
			<div class="row cover-container text-color">
				<table class="table table-responsive table-background">
			        <thead>
			            <tr>
			                <th><p align="center">ID</p></th>
			                <th><p align="center">Title</p></th>
			                <th><p align="center">Director</p></th>
			                <th><p align="center">Year</p></th>
			                <th><p align="center">Stars</p></th>
			                <th><p align="center">Genres</p></th>
			                <th></th>
			            </tr>
			        </thead>
			        <tbody>
			        
			
			        
			        	<c:forEach var = "tempMovie" items = "${MOVIES_PER_PAGE}">
				            <tr>
				                <td> ${tempMovie.id}</td>
				                <td> <h3 class="movieCardPopup" id="${tempMovie.id}"> <a class="text-color" href = "SingleMovieServlet?movieId=${tempMovie.id}">
				                
				                	
				             ${tempMovie.title} 	
				                <!-- 	<div class = "tooltip"> ${tempMovie.title} 	
				                		<span class = "tooltiptext" > Hello</span></div>  -->
				                
				              	<!--    <div class = "popup" onmouseover="myFunction()" > ${tempMovie.title} 
				              	  	<span class="popuptext" id="myPopup">A Simple Popup!</span></div> --> </a> </h3> </td>
									
									
				                <td>${tempMovie.director}</td>
				                <td>${tempMovie.year}</td>
				                <td>
									<c:forEach var = "stars" items = "${tempMovie.stars}"> 
										<a class="text-color" href = "SingleStarServlet?starId=${stars.id}"> ${stars.firstName} ${stars.lastName} <br/> </a>
									</c:forEach>			                
				                </td>
				                <td>
									<c:forEach var = "genres" items = "${tempMovie.genres}"> 
										${genres.name} <br/>
									</c:forEach>			                
				                </td>
				                
								<td> 
									<form action="FabflixControllerServlet" method="GET">
										<input type="hidden" name="movieId" value="${tempMovie.id}">
										<button class="btn button btn-success btn-sm" type="submit" name="command" value="addToCart">Add to Cart</button>
									</form>
								</td>			                
				            </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
			

			<div class="inner">
				<c:if test="${page ne 1}">
						<a href="MovieListServlet?page=${page - 1}">
							<button class="btn button btn-success btn-sm">Previous</button>
						</a>
				</c:if>
				
				<c:if test="${page lt maxPage}">
						<a href="MovieListServlet?page=${page + 1}">
							<button class="btn button btn-success btn-sm" >Next</button>
						</a>
				</c:if>
			</div>		

		</div>

	    </div>
	</div>

  <script>
// When the user clicks on div, open the popup
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}
</script>
</div>
</body>
</html>