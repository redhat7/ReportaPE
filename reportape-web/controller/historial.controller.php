<?php 
session_start();
error_reporting(E_ALL ^ E_NOTICE);
require_once '../datasend/dataGeneral.php';
include '../config/config.php';
include '../models/login.class.php';
include '../models/general.class.php';
include '../models/reportes.class.php';

//login
$log = new Login();
$log->validarSesion();
$log->cerrarSesion($sesion_close);
$datos_user = $log->getDataUser();
$ses_usuario = $datos_user['ses_usuario'];
$ses_tipoTrabajador = $datos_user['ses_tipoTrabajador_nombre'];
$ses_privilegios = $datos_user['ses_privilegio'];
$ses_idTrabajador = $datos_user['ses_tipoTrabajador_id'];
//fin login

//Armamos lista de reportes recientes:

$gnrlObj = new Generalquery();
$repoclass = new Reportes();


//Variables para Vista: 
$countRows = 0;
$html_rg = '';
$btn_return = '';
$titulo_sec = 'Reportes Atendidos';


if($op_detail!=''){//Detalle de Reporte Grupal

	$countRows = '';
	$titulo_sec = 'Detalle de Reporte - '.$id_1;

	$gnrlObj->setTabla('reporteusuario');
	$gnrlObj->setCampo_order('reporteusuario_id');
	$gnrlObj->setOrder('DESC');
	$gnrlObj->setCampo('reportegrupal_id');
	$getReportes = $gnrlObj->getAllFieldInt($id_1);

	foreach ($getReportes as $col) {
		$ru_id = $col['reporteusuario_id'];
		$ru_correo = $col['reporteusuario_correo'];
		$ru_direccion = $col['reporteusuario_descripcion'];
		$ru_fechaHora = $col['reporteusuario_fechahora'];
		$ru_usuario_id = $col['usuario_id'];
		$btn_return = '
			<a href="'.URL.'" class="btn btn-warning btn-lg" data-toggle="tooltip" data-placement="bottom" title="Volver">
			    <span class="glyphicon glyphicon-arrow-left"></span>
			</a>
		';

		$html_rg = $html_rg.'
				<li class="list-group-item">
				    <div class="row">
					    <div class="col-xs-9 col-md-10">
						    <div class="col-xs-12 col-md-4 det-ru">
								<span class="glyphicon glyphicon-envelope"></span> <b>Cuenta Correo:</b> <br><big>'.$ru_correo.'</big>
						    </div>
						    <div class="col-xs-12 col-md-4 det-ru">
								<span class="glyphicon glyphicon-map-marker"></span> <b>Ubicación:</b> <br><big>'.$ru_direccion.'</big>
						    </div>
						    <div class="col-xs-12 col-md-4 det-ru">
								<span class="glyphicon glyphicon-calendar"></span> <b>Fecha y hora de Reporte:</b> <br><big>'.$ru_fechaHora.'</big>
						    </div>        
					    </div>
					    <div class="col-xs-3 col-md-2 det-ru">
					        <button type="button" id="btn-op-user" name="user_id" value="'.$ru_usuario_id.'" class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Ver datos de Usuario">
					            <span class="glyphicon glyphicon-user"></span>
					        </button>
					    </div>
				    </div>
				</li>
		';

	}

}else if($op_delete!=''){// Elimina
	$gnrlObj->setTabla('reportegrupal');
	$gnrlObj->setCampo('reportegrupal_id');
	$gnrlObj->deleteFieldInt($id_3);
	
	$gnrlObj->setTabla('reporteusuario');
	$gnrlObj->setCampo('reportegrupal_id');
	$gnrlObj->deleteFieldInt($id_3);
	header('Location: '.URL);

}else if($op_estate!=''){// Cambio estado
		$gnrlObj->setTabla('reportegrupal');
		$gnrlObj->setId('reportegrupal_id');
		$gnrlObj->setCampo('reportegrupal_estado');
		$gnrlObj->updateOneField($id_2,1);

		$gnrlObj->setTabla('trabajador_reportegrupal');
		$gnrlObj->setId('reportegrupal_id');
		$gnrlObj->setCampo('trabajador_reportegrupal_estado');
		$gnrlObj->updateOneField($id_2,1);

		header('Location: '.URL);
		
}else{ //Lista Inicial

	$gnrlObj->setTabla('reportegrupal');
	$gnrlObj->setCampo_order('reportegrupal_id');
	$gnrlObj->setOrder('DESC');
	$gnrlObj->setCampo('reportegrupal_estado');
	$getReportes = $gnrlObj->getAllFieldInt(2);


	//rg_ = Reporte Grupal
	foreach ($getReportes as $col) {
		$rg_id = $col['reportegrupal_id'];
		$rg_direccion = $col['reportegrupal_direccion'];
		$rg_contador = $col['reportegrupal_contador'];

		if($rg_direccion!=''){
			$countRows = $countRows+1;
			$html_rg = $html_rg.'
				<li class="list-group-item">
				    <div class="row">
				        <div class="col-xs-2 col-md-1">
				            <img src="app/img/success.png" class="img-circle img-responsive" alt=""  data-toggle="tooltip" data-placement="bottom" title="Reporte No Atendido" />
				            <center>ID: '.$rg_id.'</center>
				        </div>
				        <div class="col-xs-10 col-md-11">
				            <div class="col-md-7">
				                <h4>
				                    <span class="glyphicon glyphicon-map-marker"></span> '.$rg_direccion.'
				                </h4>
				                <div class="mic-info">
				                    Cantidad de reportes: <big><b>'.$rg_contador.'</b></big>
				                </div>
				            </div>
				            <div class="col-md-5 action">
				                <button type="submit" name="btn-op-detail" value="'.$rg_id.'" id="btn-op-detail" class="btn btn-primary btn-lg" data-toggle="tooltip" data-placement="bottom" title="Ver Detalle">
				                    <span class="glyphicon glyphicon-th-list"></span>
				                </button>
				                <button type="submit" name="btn-op-estate" value="'.$rg_id.'" id="btn-op-estate" class="btn btn-warning btn-lg" data-toggle="tooltip" data-placement="bottom" title="Cambiar estado">
				                    <span class="glyphicon glyphicon-remove"></span>
				                </button>
				                <button type="submit" name="btn-op-delete" value="'.$rg_id.'" id="btn-op-delete" class="btn btn-danger btn-lg" data-toggle="tooltip" data-placement="bottom" title="Eliminar">
				                    <span class="glyphicon glyphicon-trash"></span>
				                </button>

				            </div>
				        </div>
				    </div>
				</li>
			';
		}
	}
	
	if($countRows==0){
		$html_rg='
		<div class="alert alert-warning" role="alert">
			<center>
				Actualmente no contiene registros.
			</center>
		</div>
		';
	}
}