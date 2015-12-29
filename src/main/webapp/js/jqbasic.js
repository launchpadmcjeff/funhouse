
$(document).ready(function() {

	$("#bar").click(function(event) {

		// alert( "Thanks for visiting!" );
		event.preventDefault();
		$("a").addClass("test");

	});

	$("#foo").click(function(event) {

		// alert( "As you can see, the link no longer took you to jquery.com" );

		event.preventDefault();
		$("a").removeClass("test");

	});
	$("#a").click(function(event) {
		$(this).hide("slow");
	});

	$('#seePersons').click(function(event) {
		$.ajax({
			dataType : "json",
			url : "http://localhost:8080/jbosswildfly3/rest/persons",
			success : function() {
				console.log("Ajax Success!")
			}
		});

	});

});