$(function(){
	$('#basicButton').click(function(event) {
		var foo = $('input[name=endpoint]:radio');
		var path;
		if (foo.name === 'whoami') {
			path = '/user/whoami';
		}
		$.ajax({
			dataType : "json",
			url : "https://gw1-corp.networkfleet.com:8443/api" + path,
			success : function() {
				console.log("Ajax Success!")
			}
		});

	});
});