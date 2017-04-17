<?php 

$sesion_close = $_POST['op-ses-close'];//cerrar sesión

//botones opciones reportes

$separador='/';

$arrayOpDetail = explode($separador, $_POST['btn-op-detail']);
$arrayOpAsign = explode($separador, $_POST['btn-op-asign']);

$id_1 = $op_detail = $arrayOpDetail[0];//ver detalle
$id_2 = $op_estate = $_POST['btn-op-estate'];//cambiar estado
$id_3 = $op_asign = $arrayOpAsign[0];//cambiar estado

//listado reportes-trabajador
$op_repoAsig = $_POST['btn-list-repoAsig'];
$op_repoAten = $_POST['btn-list-repoAten'];

//opciones de asignacion:
$op_addAsig = $_POST['btn-add-asign'];
$op_delAsig = $_POST['btn-del-asign'];


//Datos POST
if($op_detail!=''){
	$direccion_reportegrupal = base64_decode($arrayOpDetail[1]);
}else if($op_asign != ''){
	$direccion_reportegrupal = base64_decode($arrayOpAsign[1]);
}



$dat_trab_tipo     = htmlspecialchars($_POST['trab_tipo']);
$dat_trab_nombre   = htmlspecialchars($_POST['trab_nombre']);
$dat_trab_correo   = htmlspecialchars($_POST['trab_correo']);
$dat_trab_codigo   = htmlspecialchars($_POST['trab_codigo']);
$dat_trab_pass     = htmlspecialchars($_POST['trab_contrasena']);
$dat_trab_pass_rep = htmlspecialchars($_POST['trab_contrasena_rep']);






?>