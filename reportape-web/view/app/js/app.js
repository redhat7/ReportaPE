//tab js//
$(document).ready(function(e) {
    
  //MENU//
  $('#menu_icon').click(function(){
  	if($("#content_details").hasClass('drop_menu'))
  	{
         $("#content_details").addClass('drop_menu1').removeClass('drop_menu');
     }
  	else{
  		$("#content_details").addClass('drop_menu').removeClass('drop_menu1');
  		}
  });

  // sticky js//

  $(window).scroll(function(){
      if ($(window).scrollTop() >= 500) {
         $('nav').addClass('stick');
      }
      else {
         $('nav').removeClass('stick');
      }
  });


  $("#btn-ses-close").click(function(){
    $("#form-user-head").submit();
  });


  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
  })


  $('#btn-op-user').click(function(){
    var user = $('#btn-op-user').val();
    $("#form-user-head").attr('action','usuario.php');
    $("#form-user-head").submit();
  });



  //Validar passwords

  $('#btn-add-pers').click(function(){
    var pass1 = $('#trab_contrasena').val();
    var pass2 = $('#trab_contrasena_rep').val();
    
    var msgOK =  '<div class="alert alert-success" role="alert">'+
                 '  <center>'+
                 '    <big>'+
                 '      <b>Contraseñas iguales.</b>'+
                 '    </big>'+
                 '  </center>'+
                 '</div>';

    var msgERR = '<div class="alert alert-danger" role="alert">'+
                 '  <center>'+
                 '    <big>'+
                 '      <b>Contraseñas diferentes.</b>'+
                 '    </big>'+
                 '  </center>'+
                 '</div>';
                 
    if(pass1==pass2){
      $('#msg-pass').html(msgOK);
      $("#frm-personal").submit();
    }else{
      $('#msg-pass').html(msgERR);
      document.getElementById('trab_contrasena').value="";
      document.getElementById('trab_contrasena_rep').value="";
    }

  });



});


function asignReport(valSend){
  var arrSend = valSend.split("/");
  var  idTabajador = arrSend[0];
  var  idReporteG = arrSend[1];

  if (idTabajador=="") {
    document.getElementById("sec-btn-asign-"+idTabajador).innerHTML="";
    return;
  }

  var contador = document.getElementById("cantRepo"+idTabajador).value;

  if (window.XMLHttpRequest) {
    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }

  xmlhttp.onreadystatechange=function() {

    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
      document.getElementById("sec-btn-asign-"+idTabajador).innerHTML=xmlhttp.responseText;
      document.getElementById("cantRepo"+idTabajador).innerHTML=parseInt(contador)+1;
    }
  }

  xmlhttp.open("GET","../modules/jx_reportAsign.php?idTrabajador="+idTabajador+"&idReporteG="+idReporteG,true);
  xmlhttp.send();
}


function msgConfirm(id){

  bootbox.confirm("¿Está seguro(a) de cambiar de estado?", function(result){ 
    if(result==true){
      document.getElementById('btn-op-estate-'+id).setAttribute("type", "submit");
      document.getElementById('btn-op-estate-'+id).click();
    }else{
      document.getElementById('btn-op-estate-'+id).setAttribute("type", "button");
    }

  });

}
