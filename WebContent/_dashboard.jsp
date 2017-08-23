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
	          <h3 class="masthead-brand">D A S H B O A R D</h3>
	
	         
	        </div>
	      </div>


		<div class="inner cover">
			
			
			</div>	
			<form class="form-horizontal" action="FabflixControllerServlet" method="GET">
			<br/>
			<button type="submit" class="btn btn-success btn-sm" name="command" value="metaData">Print Metadata</button>
			</form>
			
<br/>			
			
			<form class="form-horizontal" action="_dashboard-add-star.jsp" >
			<br/>
			<button type="submit" class="btn btn-success btn-sm">Add a Star</button>
			</form>
			
<br/>
			<form class="form-horizontal" action="_dashboard-add-movie.jsp" >
			<br/>
			<button type="submit" class="btn btn-success btn-sm">Add a Movie</button>
			</form>
			
			
				<div>
				  	<c:if test="${FAIL}">		<!-- Failed star add -->
				  		<p class="form-title">Need Star's last name</p>
					</c:if>
					<c:if test="${SUCCESS}">		<!--  Successful star add -->
				  		<p class="form-title">Star added</p>
					</c:if>
                </div>
<%-- 
	      <div class="inner cover">
	        <h1 class="cover-heading">Welcome Back</h1>
	
	        <p class="lead"> ${CUSTOMER.firstName} </p>
	
	        <p class="lead"><a class="btn btn-lg btn-info" href="search-view.jsp">Search Now</a></p>
	      </div>
--%>		
	      <div class="mastfoot">
	        <div class="inner">
	          <!-- Validation -->
	
	
	        </div>
	      </div>
	    </div>
	</div>
	</div>

</body>
</html>