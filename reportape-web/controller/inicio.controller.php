<?php
session_start(); 
error_reporting(E_ALL ^ E_NOTICE);
require_once '../datasend/dataGeneral.php';
include '../config/config.php';
include '../models/login.class.php';
include '../models/general.class.php';
include '../models/reportes.class.php';
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


//Armamos lista de reportes recientes:

$gnrlObj = new Generalquery();
$repoclass = new Reportes();


//Variables para Vista: 
$countRows = 0;
$html_rg = '';
$btn_return = '';
$titulo_sec = 'Reportes Recientes';

$url_form1 = URL;
$url_form2 = 'asignareporte.php';
$class_view = '';//toma el valor de una clase-name que muestra o oculta una seccion


if($op_detail != ''){//Detalle de Reporte Grupal

	$countRows = '';
	$titulo_sec = 'Detalle de Reporte - <b>'.$direccion_reportegrupal.'</b>';

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
}else if($op_asign != ''){// Asigna Reporte

	$pers = new Personal();

	$titulo_sec = 'Asignar Reporte a Trabajador || Reporte: <b>'.$direccion_reportegrupal.'</b>';
	$id_3;// Reporte Id

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
		$tr_estado = $col['trabajador_estado'];
		$tr_tipo = $col['tipotrabajador_id'];

		$pers->setId($tr_id);
		$getCountAsign = $pers->countRepoPersonal();

		foreach($getCountAsign as $colc);
		$cantRegAsig = $colc['countReg'];

		$pers->setId($tr_id);
		$pers->setRepoId($id_3);
		$validRepoAsign = $pers->validRepoAsign();

		foreach($validRepoAsign as $colv);
		$validAsig = $colv['countReg'];

		if($ses_idTrabajador==$tr_tipo or $validAsig>0){
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
			            <div class="col-md-5">
			                <h5>
			                    <span class="glyphicon glyphicon-envelope"></span> Correo
			                </h5>
			                <h4><i>'.$tr_correo.'</i></h4>
			            </div>
			            <div class="col-md-3 action" id="sec-btn-asign-'.$tr_id.'">
			                <button type="button" name="btn-add-asign" value="'.$tr_id.'/'.$id_3.'" id="btn-list-repoAsig" onclick="asignReport(this.value)" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="bottom" title="Asignar este reporte a '.$tr_nombre.'">
			                    <span class="glyphicon glyphicon-plus"></span> Asignar
			                </button>
			            </div>
			            <div class="col-xs-12">
			                <h5>
			                    <span class="glyphicon glyphicon-pushpin"></span> Cantidad de reportes no atendidos : 
			                	<big>
			                		<b>
			                			<i>
			                				<button type="button" disabled class="btn-cantRepo" id="cantRepo'.$tr_id.'" value="'.$cantRegAsig.'">
			                					'.$cantRegAsig.'
			                				</button>
			                				<!--a href="personal.php">
			                				 ver
			                				</a-->
			                			</i>
			                		</b>
			                	</big>
			                </h5>
			            </div>
			        </div>
			    </div>
			</li>
		';

	}

	if($countRows==0){
		$html_rg = $msg['trab_disp'];
	}
}else if($op_estate != ''){// Cambio estado
		$gnrlObj->setTabla('reportegrupal');
		$gnrlObj->setId('reportegrupal_id');
		$gnrlObj->setCampo('reportegrupal_estado');
		$gnrlObj->updateOneField($id_2,2);

		$gnrlObj->setTabla('trabajador_reportegrupal');
		$gnrlObj->setId('reportegrupal_id');
		$gnrlObj->setCampo('trabajador_reportegrupal_estado');
		$gnrlObj->updateOneField($id_2,2);
		
		header('Location: '.URL);		
}else{ //Lista Inicial

	$gnrlObj->setTabla('reportegrupal');
	$gnrlObj->setCampo_order('reportegrupal_id');
	$gnrlObj->setOrder('DESC');
	$gnrlObj->setCampo('reportegrupal_estado');
	$getReportes = $gnrlObj->getAllFieldInt(1);

	//rg_ = Reporte Grupal
	foreach ($getReportes as $col) {
		$rg_id = $col['reportegrupal_id'];
		$rg_direccion = $col['reportegrupal_direccion'];
		$rg_contador = $col['reportegrupal_contador'];
		$direccionSend = base64_encode($rg_direccion);

		if($rg_direccion!=''){
			$countRows = $countRows+1;
			$html_rg = $html_rg.'
				<div class="list-group-item">
				    <div class="row">
				        <div class="col-xs-2 col-md-1">
				            <img src="app/img/warning.png" class="img-circle img-responsive" alt=""  data-toggle="tooltip" data-placement="bottom" title="Reporte No Atendido" />
				            <center>ID: '.$rg_id.'</center>
				        </div>
				        <div class="col-xs-10 col-md-11">
				            <div class="col-md-9">
				                <h4>
				                    <span class="glyphicon glyphicon-map-marker"></span> '.$rg_direccion.'
				                </h4>
				                <div class="mic-info">
				                    Cantidad de reportes: <big><b>'.$rg_contador.'</b></big>
				                </div>
				            </div>
				            <div class="col-md-3 action">
				                <button type="button" onclick="msgConfirm(this.value)" name="btn-op-estate" value="'.$rg_id.'" id="btn-op-estate-'.$rg_id.'" class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Cambiar estado">
				                    <span class="glyphicon glyphicon-ok"></span>
				                </button>
				                <button type="submit" name="btn-op-detail" value="'.$rg_id.'/'.$direccionSend.'" id="btn-op-detail" class="btn btn-primary btn-lg" data-toggle="tooltip" data-placement="bottom" title="Ver Detalle">
				                    <span class="glyphicon glyphicon-th-list"></span>
				                </button>
				                <button type="submit" name="btn-op-asign" value="'.$rg_id.'/'.$direccionSend.'" id="btn-op-asign" class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Asignar Reporte">
				                    <span class="glyphicon glyphicon-list-alt"></span>
				                </button>
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
}

?>