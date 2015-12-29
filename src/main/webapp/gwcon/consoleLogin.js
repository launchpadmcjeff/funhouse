$(function(){
	$('#login').click(function(event) {
		var val = $('input[name=endpoint]:checked').val();
		if (val === 'whoami') {
			$.ajax({
				dataType : "json",
				url : "https://gw1-corp.networkfleet.com:8443/api/user/whoami",
				success : function() {
					console.log("Ajax Success!");
				},
				crossDomain: true,
				xhrFields: {
					withCredentials: true
			   }
			});
		} else if (val === 'login') {
			$.ajax({
				method: 'POST',
				dataType : "json",
				url : "https://gw1-corp.networkfleet.com:8443/api/auth/login",
				success : function() {
					console.log("Ajax Success!");
				},
				crossDomain: true,
				xhrFields: {
					withCredentials: true
				},
				data: {password: $('#passwd').val(), username: $('#uname').val()}
			});
		} else if (val === 'service') {
			$.ajax({
				method: 'GET',
				dataType : "json",
				url : "https://gw1-corp.networkfleet.com:8443/api/service",
				success : function() {
					console.log("Ajax Success!");
				},
				crossDomain: true,
				xhrFields: {
					withCredentials: true
				},
				data: {password: $('#passwd').val(), username: $('#uname').val()}
			});
	}

	});
});

$(function(){
	$('.tabPanels .tabs li').on('click', function(){
		$('.tabPanels .tabs li.active').removeClass('active');
		$(this).addClass('active');
		
		var panelToShow = $(this).attr('data-rel');
		
		$('.tabPanels .tabPanel.active').slideUp(300, function(){
			$(this).removeClass('active');
			
			$('#'+panelToShow).slideDown(300, function(){
				$(this).addClass('active');
			});
		});
	});
});