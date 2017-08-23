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
	             <p align = "center"> Add a Movie Star </p>
	           </li>
	            

	          </ul>
	        </div>
	      </div>


		<div class="inner cover">
			<form class="form-horizontal" action="FabflixControllerServlet" method="GET">
				<input type="hidden" name="method" value="search">		<!-- METHOD -->
				<input type="hidden" name="newSearchQuery" value="true">
				<fieldset>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtStreet">First Name</label>  
						<div class="col-md-4">
						<input id="firstName" name="firstName" type="text" placeholder="First Name" class="form-control input-md">
						</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtCity">Last Name</label>  
						<div class="col-md-4">
						<input id="lastName" name="lastName" type="text" placeholder="Last Name" class="form-control input-md">    
						</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber">Date of Birth</label>  
						<div class="col-md-4">
						<input id="dob" name="dob" type="text" placeholder="YYYY/MM/DD" class ="form-control input-md">    
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="txtFlatNumber"> Photo URL</label>  
						<div class="col-md-4">
						<input id="photoUrl" name="photoUrl" type="text" placeholder="Photo Url" class="form-control input-md">    
						</div>
					</div>
					
				
					<button type="submit" class="btn btn-success btn-sm" name="command" value="addStar">Add Star</button>
				</fieldset>
				
				
				
				
			</form>	
			</div>	
			<form class="form-horizontal" action="_dashboard.jsp" method="GET">
			<br/>
			<button type="submit" class="btn btn-success btn-sm">Back</button>
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