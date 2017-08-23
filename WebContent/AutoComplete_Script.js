
$(document).ready(function() {
	
	var dropdown = $('<div id="DropDown"></div>');
	var pos = $('#auto').offset();
    var height = $('#auto').height();
    var width = $('#auto').width();
    
	$('body').append(dropdown);
	$('#DropDown').hide();
    $('#DropDown').css({
    	'position':'fixed',
    	minWidth: width + 'px',
        top: pos.top + height + 10 + 'px',
        left: pos.left + 'px',
    });
    
    $('#bar_category').css({
    	minWidth: width + 'px'
    });
	
	$('#auto').focus(function() {
		$('#DropDown').fadeIn();
	});
	
	$('#auto').blur(function() {
		$('#DropDown').fadeOut();
	});
	
});




function lookup(text) 
{
	if (text.length == 0) 
	{
		$('#DropDown').fadeOut();
	} 
	else 
	{
		$.get("AutoSearch", {Words: text}, function(list) {
			
			$('#DropDown').html(list);
			$('#DropDown').fadeIn();
		});
	}
};	