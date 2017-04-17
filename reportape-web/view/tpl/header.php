<header>
	
	<link rel="stylesheet" href="https://opensource.keycdn.com/fontawesome/4.6.3/font-awesome.min.css" crossorigin="anonymous">


	<section class="header-sec">
		<nav id="sticker" class="navbar navbar-inverse">
			<section class="header-sec sec-user">
				<div>
					<form id="form-user-head" method="POST">
						<ul class="list-user">
							<li>
								<span>Usuario:</span> <b><?= $ses_usuario; ?></b>	
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg" title="Ver Perfil">
									<span class="glyphicon glyphicon-user"></span>
								</button>			
							</li>
							<li>
								<button id="btn-ses-close" name="op-ses-close" value="ses_close">
									Cerrar Sesión 
									<span class="glyphicon glyphicon-off"></span>
								</button>				
							</li>
						</ul>	
					</form>
				</div>
			</section>
			<div class="container">
				<div class="navbar-header">
					<a class="menu"><i class="fa fa-bars" id="menu_icon"></i></a>
					<a class="navbar-brand" href="#">
						<?= NOMBRE_EMPRESA_IMG; ?>
				  	</a>
				</div><!--navbar-header close-->
				<div class="collapse navbar-collapse drop_menu" id="content_details">
				 	<ul class="nav navbar-nav navbar-right">
						<li><a href="inicio.php">Reportes</a></li>
						<li><a href="historial.php">Historial de reportes</a></li>
			        	<li><a href="personal.php">Trabajadores</a></li>
				        <li><a href="personalnuevo.php">Cuentas de usuario</a></li>    
				  	</ul>
				</div>
			</div>
		</nav>	
	</section>

	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	    
	      <div class="modal-body">
	     
	      <h2>Datos de Usuario</h2>
	      <br>
	      <h4><small>Usuario: </small><?= $ses_usuario; ?></h4>
	      <h4><small>Privilegio: </small><?= $ses_tipoTrabajador; ?></h4>
	      <h4><small>ID-Trabajador: </small><?= $ses_idTrabajador; ?></h4>
	      </div>
	    </div>
	  </div>
	</div>

</header>
