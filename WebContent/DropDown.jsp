<%@ page import = "project2.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
  #search_results{
	border-width:1px; 
	border-color:rgb(0, 255, 0); 
	border-style:double;
	font-size: 11px; 
	font-family:Impact;
	background-color:hsl(120, 100%, 75%);
  }
   #Bar {
	background-color: hsl(120, 100%, 25%);
	padding: 5px;
	font-weight: bold;
	font-size:11px; 
	display:block; 
	color:white;
 }
  #Margins{
    padding: 8px;
  }
  
  #Title{
	color: black;
  }
</style>

<div id="search_results">
<div id="Bar">Movie List</div>
	<div id="Margins">
		<div style="overflow:scroll;height:95px;width:100%;overflow:auto">
			<c:forEach items="${movies}" var="movie">
				<div class="movieCardAnchor" id="${movie.id}">		
					<table>
						<tr>		
							<td>
								<a class="text-color" href = "SingleMovieServlet?movieId=${movie.id}">				        	
							        <span id="Title">${movie.title}</span> <br />
							    </a>
							</td>
						</tr>
					</table>
		      	</div>
			</c:forEach>
		</div>
	</div>
</div>





