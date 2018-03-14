$(document).ready(function () {
	
	var search = {}
	
	$(".sunsign").on('click', event => {
		$(event.currentTarget).addClass("btn-sunsign-focus");
		$(event.currentTarget).siblings().removeClass("btn-sunsign-focus");
		search["sunsign"] = $(event.currentTarget).val();
	});
	
	$(".day").on('click', event => {
		$(event.currentTarget).addClass("btn-day-focus");
		$(event.currentTarget).siblings().removeClass("btn-day-focus");
		search["day"] = $(event.currentTarget).val();
	});
	
	$("#show_horoscope").on('click', event => {
	    
	    $("#show_horoscope").prop("disabled", true);
	    $("html, body").animate({scrollTop: $("#horoscope_description").offset().top});

	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/api/search",
	        data: JSON.stringify(search),
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {

	            var json = "<pre>" + JSON.stringify(data, null, 4) + "</pre>";
	            $('#feedback').html(json);

	            var obj = JSON.parse(JSON.stringify(data));
	            $('#horoscope_description').html(
	            		"<h4>Horoscope for <b>"+obj.result.sunsign+"</b></h4>" +
	    	            "<p>Date: "+obj.result.date+"</p>" +
	    	            "<p>Keywords: "+obj.result.meta.keywords+"</p>" +
	    	            "<p>Mood: "+obj.result.meta.mood+"</p>" +
	    	            "<p>Intensity: "+obj.result.meta.intensity+"</p>" +
	    	            "<p class='description_justify'>"+obj.result.horoscope+"</p>"
	            );
	            
	            show_hide_json_format();
	            
	            $("#show_horoscope").prop("disabled", false);

	        },
	        error: function (e) {

	            var json = "<pre>" + e.responseText + "</pre>";
	            $('#feedback').html(json);
	            
	            $('#horoscope_description').html(
	            		"<h1 class='error'>Something went wrong</h1>" +
	            		"<h3 class='error'>Try again: choose your zodiac sign and a day.</h3>"
	            );
	            
	            show_hide_json_format();

	            $("#show_horoscope").prop("disabled", false);

	        }
	    });  
	})
})

function show_hide_json_format() {
	
	$('#json_response').show();
    $('#json_response_btn').unbind('click'); //a way to remove all previous click events that have been assigned to a button ('event') 
    $('#json_response_btn').on('click', () => {
    	$('#feedback').toggle('slow', () => {
    		if ($("#feedback").is(':visible')) {
        		$('#json_response_btn').text("JSON Response: hide");
        	    $("html, body").animate({scrollTop: $("#feedback").offset().top},1000,'linear');
        	}
        	else {
        		$('#json_response_btn').text("JSON Response: show");
        	}
    	});
    });
}