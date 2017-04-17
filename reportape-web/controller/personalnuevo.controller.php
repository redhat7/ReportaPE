<?php
session_start(); 
error_reporting(E_ALL ^ E_NOTICE);
require_once '../datasend/dataGeneral.php';
include '../config/config.php';
include '../models/login.class.php';
include '../models/general.class.php';
include '../models/personal.class.php';

//login
$log = new Login();
$log->validarSesion();
$log->cerrarSesion($sesion_close);
$datos_user = $log->getDataUser();
$ses_usuario = $datos_user['ses_usuario'];
$ses_tipoTrabajador = $datos_user['ses_tipoTrabajador_nombre'];
$ses_codigoTrab = $datos_user['ses_tipoTrabajador_codigo'];
$ses_idTrabajador = $datos_user['ses_tipoTrabajador_id'];
//fin login


//Objeto consultas generales
$gnrlObj = new Generalquery();

//Variables para Vista: 
$titulo_sec='Añadir Trabajador';


//DATOS PARA LISTAR TIPO DE TRABAJADORES

//Definimos consulta de tipo trabajador segun usuario:
if($ses_codigoTrab=='AD001'){
	$estadoVal = 'TR001';
	$class_view = HIDDEN;
	$required = '';
}else if($ses_codigoTrab=='admin'){
	$estadoVal = 'AD001';
	$class_view = VISIBLE;
	$required = 'required';
}

$gnrlObj->setTabla('tipotrabajador');
$gnrlObj->setCampo_order('tipotrabajador_id');
$gnrlObj->setOrder('DESC');
$gnrlObj->setCampo('tipotrabajador_codigo');
$getTipotrabajador = $gnrlObj->getAllFieldStr($estadoVal);

// optiones de tipo de trabajadores para formulario
foreach($getTipotrabajador as $col){
	$html_tt = $html_tt.'<option value="'.$col['tipotrabajador_id'].'"> '.$col['tipotrabajador_nombre'].' </option>';
}

// html de formulario 
$html_rg = '
			<div class="col-xs-12 col-md-6 form-input">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<select class="form-control" placeholder="gf" name="trab_tipo">
						<option value="0" disabled selected>Tipo de trabajador</option>
						'.$html_tt.'
					</select>										
				</div>
			</div>
			<div class="col-xs-12 col-md-6 form-input">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<input class="form-control" type="text" name="trab_nombre" placeholder="Nombre" aria-describedby="basic-addon1" title="Mínimo 4 caracteres" maxlength="40" required>										
				</div>
			</div>
			<div class="col-xs-12 col-md-6 form-input">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<input class="form-control" type="email" name="trab_correo" placeholder="Correo" aria-describedby="basic-addon1" title="Formato Mail" maxlength="50" required>										
				</div>
			</div>	
			<div class="col-xs-12 col-md-6 form-input">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<input class="form-control" type="text" name="trab_codigo" placeholder="Código" aria-describedby="basic-addon1" title="Mínimo 4 caracteres" maxlength="10" required>
				</div>
			</div>  
			<div class="col-xs-12 col-md-6 form-input '.$class_view.'">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<input class="form-control" id="trab_contrasena" type="password" name="trab_contrasena" placeholder="Contraseña" aria-describedby="basic-addon1" maxlength="8" '.$required.'>
				</div>
			</div>	
			<div class="col-xs-12 col-md-6 form-input '.$class_view.'">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-pencil"></span></span>
					<input class="form-control" id="trab_contrasena_rep" type="password" name="trab_contrasena_rep" placeholder="Repetir Contraseña" aria-describedby="basic-addon1" maxlength="8" '.$required.'>
				</div>
			</div> 
			<div class="col-xs-12 form-input" id="msg-pass" style="padding-bottom:0px;">
			</div>
			<div class="col-xs-12 text-right form-input">
				<button type="button" class="btn btn-success" id="btn-add-pers">
				<span class="glyphicon glyphicon-floppy-saved"></span> Guardar
				</button>
			</div> 
';


if($dat_trab_pass == $dat_trab_pass_rep){
	$persObj = new Personal();

	$persObj->setNombre($dat_trab_nombre);//Datos POST/Form
	$persObj->setCorreo($dat_trab_correo);//Datos POST/Form
	$persObj->setContrasena(md5($dat_trab_pass));//Datos POST/Form
	$persObj->setCodigo($dat_trab_codigo);//Datos POST/Form
	$persObj->setTipo($dat_trab_tipo);//Datos POST/Form

	$getvalidaForm = $persObj->validaForm();

	//echo $getvalidaForm;
	if($getvalidaForm==1){
		$persObj->addPersonal();
		header('Location: '.URL.'?add=ok');
	}
}

if($_GET['add']=='ok'){
	$msg_func = $msg['add_reg'];
}

 ?>