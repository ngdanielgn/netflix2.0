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
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>

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
	                </thead>
		                
			        <tbody>
			        <td>
				        <c:forEach var = "METADATA"  items = "${METADATA}">
							<c:out value = "${METADATA}" ></c:out> <br/> <br/>
						
						</c:forEach>
		        	</td>
			        </tbody>
			    </table>
			</div>
			

		
		</div>

	    </div>
	</div>

</body>
</html>