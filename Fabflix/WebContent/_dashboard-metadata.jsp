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

			<div class="row cover-container options-padding text-color">
			
			
			
			<div class="row cover-container text-color">
				<table class="table table-responsive table-background">
			         <thead>
			         <tr>
			         	<th><p align="center">MetaData</p> </th>
			         	</tr>
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
			<form class="form-horizontal" action="_dashboard.jsp" method="GET">
			<br/>
			<button type="submit" class="btn btn-success btn-sm">Back</button>
			</form>

		
		</div>

	    </div>
	</div>

</body>
</html>