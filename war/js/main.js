
$(document).ready(function() {

});




function displayError(error) {
	$(".alert-danger").css("display", "visible").html(error);
}

function activateLink(linkName){
	$(".navbar li").removeClass('active');
	$(".navbar a:contains('" + linkName + "')").parent().addClass("active");
}