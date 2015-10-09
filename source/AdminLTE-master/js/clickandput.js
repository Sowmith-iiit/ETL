$(document).ready(function(){

	state = 0;

	$('.treeview-menu > li').click(function(){
		if(state == 0)
		{
			a = $(this).find('a');
			a.css("font-weight","bold");
			state = 1;
		}
		else
		{
			a = $(this).find('a');
			a.css("font-weight","normal");
			state = 0;
		}	
	});



});
