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
	
	      <ul class="nav masthead-nav">	
		        <li>
	              <p align = "center"> Add/Update a Movie</p>
	           </li>
	            

	          </ul>
	        </div>
	      </div>

<br/>
<br/>
		<div class="inner cover">
			<form class="form-horizontal" action="FabflixControllerServlet" method="GET">
				<input type="hidden" name="method" value="search">		<!-- METHOD -->
				<input type="hidden" name="newSearchQuery" value="true">
				<fieldset>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtStreet">Title</label>  
						<div class="col-md-4">
						<input id="title" name="title" type="text" placeholder="Movie Title" class="form-control input-md">
						</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtCity">Year</label>  
						<div class="col-md-4">
						<input id="year" name="year" type="text" placeholder="Year" class="form-control input-md">    
						</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber">Director</label>  
						<div class="col-md-4">
						<input id="director" name="director" type="text" placeholder="Director" class ="form-control input-md">    
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber">First Name</label>  
						<div class="col-md-4">
						<input id="starFirstName" name="starFirstName" type="text" placeholder="Star's First Name" class="form-control input-md">    
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber">Last Name</label>  
						<div class="col-md-4">
						<input id="starLasttName" name="starLastName" type="text" placeholder="Star's Last Name" class="form-control input-md">    
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber">Genre </label>  
						<div class="col-md-4">
						<input id="genre" name="genre" type="text" placeholder="Genre" class="form-control input-md">    
						</div>
					</div>
				
					<button type="submit" class="btn btn-success btn-sm" name="command" value="addMovie">Add Movie</button>
				</fieldset>
				
				
				
				
			</form>	
			</div>	
			<form class="form-horizontal" action="_dashboard.jsp" method="GET">
			<br/>
			<button type="submit" class="btn btn-success btn-sm">Back</button>
			</form>
				<div>
				  	<c:if test="${FAIL}">		<!-- Failed star add -->
				  		<p class="form-title">Invalid inputs</p>
					</c:if>
					<c:if test="${SUCCESS == '0'}">		<!--  Successful star add -->
				  		<p class="form-title">Movie added</p>
					</c:if>
					<c:if test="${SUCCESS == '1'}">		<!--  Successful star add -->
				  		<p class="form-title">Movie Updated</p>
					</c:if>
                </div>

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