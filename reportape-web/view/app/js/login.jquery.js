$(document).ready(function(){
	$('.toggle').on('click', function() {
	  $('.container').stop().addClass('active');
	});

	$('.close').on('click', function() {
	  $('.container').stop().removeClass('active');
	});

});

//Validacion jquery para logueo

$(document).ready(function(){
	$("#btn-send-log").click(function(){	
		var usuario = $("#Username").val();
		var password = $("#Password").val();
		if(usuario != '' && password != ''){

			$("#load").html("<img src='app/img/loading3.gif'><br>Cargando...");

			$('#form-id').animate({
				opacity: "toggle",
				duration: 1000,
				opacity: "toggle"
			});	

			$('#logo').animate({
				marginTop:"100px",
				duration: 3000
			});				

		}
	});
});

