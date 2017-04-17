<?php 
error_reporting(E_ALL ^ E_NOTICE);

include '../config/config.php';
include '../models/general.class.php';
include '../models/personal.class.php';

//Object
$objPers = new Personal();

//Data Get

$id_trabajador = $_GET['idTrabajador'];
$id_reporteG = $_GET['idReporteG'];

//Set
$objPers->setId($id_trabajador);
$objPers->setRepoId($id_reporteG);

//Function
$asignRepo = $objPers->addAsignReporte();


if($asignRepo){
	echo $msg['repo_asign'];	
}else{
	echo $msg['oper_err'];
	echo "<center><a href='".URL."'>Volver</a></center>";
}


 ?>
