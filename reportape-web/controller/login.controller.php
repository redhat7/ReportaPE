<?php 
error_reporting(E_ALL ^ E_NOTICE);
require_once '../datasend/dataLogin.php';
include '../config/config.php';
include '../models/login.class.php';

// Carga de datos de la BD
$log=new Login();
//$lista=$log->listarTipoTrabajador();

/* 
Variables enviadas desde Data:
	$usuario_post
	$contrasena_post
	$tipotrab_post 
	*/

if(isset($op_login) and $op_login == 1){
	
	//Asignamos los valores enviados del formulario
	$log->setUsuario($usuario_post);
	$log->setPassword($contrasena_post);
	$log->setTipo_trabajador($tipotrab_post);

	//Invocamos a la funcion que valida si el usuario tiene una cuenta y puede loguearse
	$validacionSesion = $log->Validar();

	//Retorna valor de 1 si es correcto y 2 si es incorrecto.
	if($validacionSesion == 1){
		sleep(3);
		$msg_view = $msg['login_ok'];
		header('Location: '.$path['login_redirect']);
	}else if($validacionSesion == 2){
		$msg_view = $msg['login_error'];
	}


}
?>


