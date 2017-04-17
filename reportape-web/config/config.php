<?php 
// DEFINIMOS VARIABLES

//Parametros de Base de Datos
define('BASE_DE_DATOS', 'de2heed_reportape_bd');
define('NOMBRE_HOST', 'localhost');
define('USUARIO', 'de2heed_reportap');
define('CONTRASENA', 'reportape2017%');

//Links generales - view
define('LINK_WEB','http://agenciahdc.com/');
define('LINK_WEB_HDC','http://agenciahdc.com/');
define('SUPER_US','superadmin');
define('SUPER_PASS','admin');

//css class
define('VISIBLE','visible-sec');
define('HIDDEN','hidden-sec');

//DATOS GENERALES - view
define('NOMBRE_EMPRESA','REPORTA PE');
define('NOMBRE_EMPRESA_IMG','<img class="img-responsive img-c" id="logo" src="app/img/logo.png">');


define('URL',basename($_SERVER['REQUEST_URI']));


//Path URL
$path = array(
	'login_redirect' => 'inicio.php' 
	);


// Validaciones-mensajes
$msg = array(
	'login_ok' => '
					<div class="alert alert-success">
					  <strong>Inicio de sesión correcto, Redireccionando...</strong>
					</div>
	',
	'login_error' => '
					<div class="alert alert-danger alert dismissable">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					  <strong>El inicio de sesión no es válido.</strong>
					</div>
	',
	'add_reg' => '
					<div class="alert alert-success alert dismissable">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					  <strong>Registro guardado.</strong>
					</div>
	',
	'repo_asign' => '
					<div class="alert alert-success alert dismissable">
					  <strong>Reporte asignado.</strong>
					</div>
	',
	'trab_disp' => '
					<center class="alert alert-warning alert dismissable">
					  <strong>Todos los trabajadores están asignados a este reporte.</strong>
					</center>
	',
	'no_reg' => '
					<center class="alert alert-warning alert dismissable">
					  <strong>Actualmente no contiene registros.</strong>
					</center>
	',
	'oper_err' => '
					<div class="alert alert-danger alert dismissable">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					  <strong>Hubo un ERROR en el sistema.</strong>
					</div>
	'
	);


 ?>