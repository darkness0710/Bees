var data;
var number = Math.floor(Math.random() * 80);

$(document).ready(function() {
	$(":button").click(function() {
		$("#bee_table").css("visibility", "visible");
	});
		
	function fix_menu() {
		$("#menu-toggle").click(function(e) {
		     e.preventDefault();
		     $("#wrapper").toggleClass("toggled");
		     $("#short-icon").css("left", "-15px"); 
		     $('*').css('visibility', 'visible');
		});
	}
	
	fix_menu();
	
	function ajax_table() {
		$.ajax({
			type: 'GET',
			url: 'bee?action=createBee',
			headers: {
				Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
			}, 
			success: function(result) {
				console.log(result);
				$("#table_body").empty();
				var listBees = $.parseJSON(result);
				data = listBees;
				for(var i = 0; i < listBees.length; i++) {
					$("#table_body").append('<tr><td>' + listBees[i].id +'</td><td>' + listBees[i].name +'</td><td>' + listBees[i].type +'</td><td>' + listBees[i].health +'</td><td>' + listBees[i].status +'</td></tr>');
				 } 
			}
		});
	}
	
	function getLabel() {
		$('#bee_table td').each(function(){
	        if ($(this).text() == 'Alive') {
	            $(this).css('color','green');
	        } else {
	        	if ($(this).text() == 'Dead') {
		            $(this).css('color', 'red');
	        	}
	        }
	    });
	}
	
	
	$('#create_bee').click(function() {
		var notification = alertify.notify('Successfully created 10 bees', 'success', 5, function() {  
			console.log('dismissed'); 
		});
		ajax_table();
	});
	
	
	$('#damage_bee').click(function(e) {
		if(data == null) {
			var notification = alertify.notify('Not found bees - You can create or Load bee!', 'error', 5, function() {  
				console.log('dismissed'); 
			});
		} else 
			$.ajax({
			  url: 'bee?action=damage',
			  type: 'GET',
			  data: {
				   number: number
			  },
			  contentType: "application/json; charset=utf-8",
			  dataType: 'json',
			  success: function (result) {
					console.log(result);
					var notification = alertify.notify('You attack and damage bees with dame: ' + number + ' % of max health current' , 'success', 5, function() {  
						console.log('dismissed'); 
					});
					$("#table_body").empty();
					var listBees = result;
					data = listBees;
					for(var i = 0; i < listBees.length; i++) {
						$("#table_body").append('<tr><td>' + listBees[i].id +'</td><td>' + listBees[i].name +'</td><td>' + listBees[i].type +'</td><td>' + listBees[i].health +'</td><td>' + listBees[i].status +'</td></tr>');
					 }
					number = Math.floor(Math.random() * 80);
			  },
			  error: function (data, event) {
				  console.log(data);
			  },
			});
	});
	
	$('#usage_bee').click(function() {
		
		
	});
	
	$('#save_bee').click(function() {
		if(data == null) {
			var notification = alertify.notify('Not found bees - You can create or Load bee!', 'error', 5, function() {  
				console.log('dismissed'); 
			});
		} else {
			$.ajax({
				  url: 'bee?action=save',
				  type: 'GET',
				  contentType: "application/json; charset=utf-8",
				  dataType: 'json',
				  success: function (result) {
					  console.log(result);
				  },
				  error: function (data, event) {
					  console.log(data);
				  },
				});
			var notification = alertify.notify('Successfully save list bees', 'success', 5, function() {  
				console.log('dismissed'); 
			});
		}	
		
	});
	
	$('#load_bee').click(function(e) {
		$.ajax({
			  url: 'bee?action=load',
			  type: 'GET',
			  contentType: "application/json; charset=utf-8",
			  dataType: 'json',
			  success: function (result) {
				  	if (result.length == 0) {
				  		var notification = alertify.notify('Not found bees in Database - You can create and save ', 'error', 5, function() {  
							console.log('dismissed'); 
						});
					} else {
						$("#table_body").empty();
						var listBees = result;
						data = listBees;
						for(var i = 0; i < listBees.length; i++) {
							$("#table_body").append('<tr><td>' + listBees[i].id +'</td><td>' + listBees[i].name +'</td><td>' + listBees[i].type +'</td><td>' + listBees[i].health +'</td><td>' + listBees[i].status +'</td></tr>');
						 }
						/*number = Math.floor(Math.random() * 80);*/
						var notification = alertify.notify('Successfully load list bees', 'success', 5, function() {  
							console.log('dismissed'); 
						});
					}	
			  },
			  error: function (data, event) {
				  console.log(data);
			  },
			});		
	});
	
	
	$(document).ajaxComplete(function( event,request, settings ) {
		getLabel();
	});
});