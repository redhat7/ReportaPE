<?php
session_start(); 
error_reporting(E_ALL ^ E_NOTICE);
require_once '../datasend/dataGeneral.php';
include '../config/config.php';
include '../models/login.class.php';
include '../models/general.class.php';
include '../models/personal.class.php';
include '../models/reportes.class.php';

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


//Armamos lista de reportes recientes:


//Objeto consultas generales
$gnrlObj = new Generalquery();


$def_link = $_GET['ref'];


//Variables para Vista: 
$countRows = 0;
$html_rg = '';
$btn_return = '';
$titulo_sec='ERROR';

//Objecto de clase Personal
$pers = new Personal();
$repo = new Reportes();

if($op_repoAsig != ''){ // listar reportes asignados por trabajador

	$titulo_sec = 'Lista de reporte asignados';
	$btn_return = '
		<a href="'.URL.'" class="btn btn-warning btn-lg" data-toggle="tooltip" data-placement="top" title="Volver">
		    <span class="glyphicon glyphicon-arrow-left"></span>
		</a>
	';

	$pers->setId($op_repoAsig);
	$pers->setRgEstado(1);

	$repoAsignados = $pers->reportesAsignados();

	foreach ($repoAsignados as $col) {
		$rg_id = $col['reportegrupal_id'];
		$rg_contador = $col['reportegrupal_contador'];
		$direccion=$col['reportegrupal_direccion'];

		if($direccion!=''){
			$countRows = $countRows+1;
			$html_rg = $html_rg.'
				<div class="list-group-item">
				    <div class="row">
				        <div class="col-xs-2 col-md-1">
				            <img src="app/img/warning.png" class="img-circle img-responsive" alt=""  data-toggle="tooltip" data-placement="bottom" title="Reporte No Atendido" />
				            <center>ID: '.$rg_id.'</center>
				        </div>
				        <div class="col-xs-10 col-md-11">
				            <div class="col-md-7">
				                <h4>
				                    <span class="glyphicon glyphicon-map-marker"></span> '.$direccion.'
				                </h4>
				                <div class="mic-info">
				                    Cantidad de reportes: <big><b>'.$rg_contador.'</b></big>
				                </div>
				            </div>
				            <div class="col-md-5 action">
				                <!--button type="submit" name="btn-op-estate" value="'.$rg_id.'" id="btn-op-estate" class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Cambiar estado">
				                    <span class="glyphicon glyphicon-ok"></span>
				                </button-->
				            </div>
				        </div>
				    </div>
				</div>
			';
		}

	}
	if($countRows==0){
		$html_rg = $msg['no_reg'];
	}
}else if($op_repoAten != ''){ // listar historial de reportes atendidos del trabajador
	
	$titulo_sec = 'Lista de reportes atendidos';
	$btn_return = '
		<a href="'.URL.'" class="btn btn-warning btn-lg" data-toggle="tooltip" data-placement="bottom" title="Volver">
		    <span class="glyphicon glyphicon-arrow-left"></span>
		</a>
	';

	$pers->setId($op_repoAten);
	$pers->setRgEstado(2);

	$repoAsignados = $pers->reportesAsignados();

	foreach ($repoAsignados as $col) {
		$rg_id = $col['reportegrupal_id'];
		$rg_contador = $col['reportegrupal_contador'];
		$direccion=$col['reportegrupal_direccion'];

		if($direccion!=''){
			$countRows = $countRows+1;
			$html_rg = $html_rg.'
				<div class="list-group-item">
				    <div class="row">
				        <div class="col-xs-2 col-md-1">
				            <img src="app/img/success.png" class="img-circle img-responsive" alt=""  data-toggle="tooltip" data-placement="bottom" title="Reporte No Atendido" />
				            <center>ID: '.$rg_id.'</center>
				        </div>
				        <div class="col-xs-10 col-md-11">
				            <div class="col-md-7">
				                <h4>
				                    <span class="glyphicon glyphicon-map-marker"></span> '.$direccion.'
				                </h4>
				                <div class="mic-info">
				                    Cantidad de reportes: <big><b>'.$rg_contador.'</b></big>
				                </div>
				            </div>
				            <div class="col-md-5 action">
				                <!--button type="submit" name="btn-op-estate" value="'.$rg_id.'" id="btn-op-estate" class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Cambiar estado">
				                    <span class="glyphicon glyphicon-ok"></span>
				                </button-->
				            </div>
				        </div>
				    </div>
				</div>
			';
		}

	}
	if($countRows==0){
		$html_rg = $msg['no_reg'];
	}
}else{ // listar trabajadores en general

	$titulo_sec = 'Lista de trabajadores';

	$gnrlObj->setTabla('trabajador');
	$gnrlObj->setCampo_order('trabajador_id');
	$gnrlObj->setOrder('DESC');
	$gnrlObj->setCampo('trabajador_estado');
	$getTrabajador = $gnrlObj->getAllFieldInt(1);

	foreach ($getTrabajador as $col) {

		$tr_codigo = $col['trabajador_codigo'];
		$tr_nombre = $col['trabajador_nombre'];
		$tr_correo = $col['trabajador_correo'];
		$tr_id = $col['trabajador_id'];
		$tr_tipo = $col['tipotrabajador_id'];

		if($ses_idTrabajador==$tr_tipo){
			$class_view = HIDDEN;
		}else{
			$class_view = VISIBLE;
			$countRows = $countRows+1;
		}

		$html_rg = $html_rg.'
			<li class="list-group-item '.$class_view.'">
			    <div class="row">
			        <div class="col-xs-2 col-md-1">
			            <img src="app/img/group.png" class="img-circle img-responsive" alt=""  data-toggle="tooltip" data-placement="bottom" title="Trabajador Activo" />
			            <center>
			            	<div class="mic-info">
			                    Código: <big><b>'.$tr_codigo.'</b></big>
			                </div>
			            </center>
			        </div>
			        <div class="col-xs-10 col-md-11">
			            <div class="col-md-4">
			                <h5>
			                    <span class="glyphicon glyphicon-user"></span> Nombre
			                </h5>
			                <h4><i>'.$tr_nombre.'</i></h4>
			            </div>
			            <div class="col-md-4">
			                <h45>
			                    <span class="glyphicon glyphicon-envelope"></span> Correo
			                </h5>
			                <h4><i>'.$tr_correo.'</i></h4>
			            </div>
			            <div class="col-md-4 action">
			                <button type="submit" name="btn-list-repoAsig" value="'.$tr_id.'" id="btn-list-repoAsig" class="btn btn-primary btn-md" data-toggle="tooltip" data-placement="bottom" title="Ver Reportes Asignados de '.$tr_nombre.'">
			                    <span class="glyphicon glyphicon-list-alt"></span> Asignados
			                </button>
			                <button type="submit" name="btn-list-repoAten" value="'.$tr_id.'" id="btn-list-repoAten" class="btn btn-warning btn-md" data-toggle="tooltip" data-placement="bottom" title="Ver Reportes Atendidos de '.$tr_nombre.'">
			                    <span class="glyphicon glyphicon-list-alt"></span> Atendidos
			                </button>
			            </div>
			        </div>
			    </div>
			</li>
		';
	}

	if($countRows==0){
		$html_rg = $msg['no_reg'];
	}

	$btn_add = '
			<a href="personalnuevo.php" name="btn-op-add" id="btn-op-add" class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Agregar Trabajador">
			    <span class="glyphicon glyphicon-new-window"></span> <small>Agregar</small>
			</a>
	';
}

 ?>