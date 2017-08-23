$(document).ready(function()
{
	$('#MovieCard').remove();
	
	var templateMovieCard = $(
			'<div id="movieCard">' + '<div id="movieCardContent"></div>' + '</div>');
	
	$('body').append(templateMovieCard);
	
	$('#movieCard').hide();
	
	var movieid;

    
	$('.movieCardPopup').mouseenter(function() {		
		$('#movieCard').hide();
		
		movieid = $(this).attr('id');
		

		var pos = $(this).offset();
	    var height = $(this).height();
		
		$.get('FabflixControllerServlet', {command: "movieCard", movieid: movieid}, function(responseText) {
            $('#movieCardContent').html(responseText);
            $('#movieCard').fadeIn();
        });
		
	    $('#movieCard').css({
	    	
	    	'background-color': 'green',
	    	'position': 'absolute',
	        top: pos.top  + 'px',
	        left: pos.left + 'px'
	    });
		
	});
	
	$('#movieCard').mouseleave(function() {
		
		
			$('#movieCard').hide();
			$('#movieCardContent').html("");
		
	});
	
});