<?php 
require_once '../controller/inicio.controller.php';
?>
<!DOCTYPE html>
<html>
<head>
  	<meta charset="UTF-8">
  	<meta lang="ES">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<meta name="robots" content="noindex">
	<title></title>
	<link rel="stylesheet" type="text/css" href="app/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="app/css/style.css">
	<script src="app/js/jquery.js"></script>
	<script src="app/js/bootstrap.js"></script>
	<script src="app/js/bootbox.js"></script>
</head>
	<body>
		<?php include 'tpl/header.php'; ?>
		<div class="section-general">
			<div class="container">
			    <div class="row">
			    	<div class="text-right sidebar-outer">
			        	<?= $btn_return; ?>
			        </div>
			        	<br>
			        <div class="panel panel-default widget">
			            <div class="panel-heading">
			                <span class="glyphicon glyphicon-th"></span>
			                <h3 class="panel-title">
			                    <?= $titulo_sec; ?>
			                </h3>
			                <span class="label label-info">
			                    <?= $countRows; ?>
			                </span>
			            </div>
			            <div class="panel-body">
				            <form action="<?= $url_form; ?>" id="form-g" method="POST">
				                <div class="list-group">
				                    <?= $html_rg; ?>
				                </div>		            	
				            </form>
			            </div>
			        </div>
			        <div class="text-right sidebar-outer">
			        	<?= $btn_return; ?>
			        </div>   
			    </div>
			</div>	
		</div>
		<?php include 'tpl/footer.php'; ?>
	<script src="app/js/app.js"></script>
	</body>
</html>