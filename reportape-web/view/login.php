<?php 
require_once '../controller/login.controller.php';

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
	<link rel="stylesheet" type="text/css" href="app/css/login.style.css">
	<script src="app/js/jquery.js"></script>
	<script src="app/js/bootstrap.js"></script>
	<script src="app/js/login.jquery.js"></script>
</head>
<body>
    <div class="container sec-login">
        <div class="row">        
            <div class="pen-title col-xs-12">
              <br>
              <?= NOMBRE_EMPRESA_IMG; ?>
              <br>
              <center>
                  <big id="load"></big>
              </center>
            </div>
            <div class="container" id="logo">
                <?= $msg_view; ?>
            </div>
        <div class="container" id="form-id">
            <div class="card"></div>
            <div class="card">
              <h1 class="title">Login</h1>
              <form action="login.php" method="post">
                <div class="input-container">
                  <input type="text" id="Username" name="usuario" required="required"/>
                  <label for="Username">Username</label>
                  <div class="bar"></div>
                </div>
                <div class="input-container">
                  <input type="password" id="Password" name="password" required="required"/>
                  <label for="Password">Password</label>
                  <div class="bar"></div>
                </div>
                <br>
                <!--div class="input-container">
                    <select class="form-control" name="tipo_trab">
                        <?= $lista; ?>
                    </select>     
                    <div class="bar"></div>           
                </div-->
                <input type="hidden" name="op-login" value="1">
                <br>
                <div class="button-container">
                    <button type="submit" id="btn-send-log"><span>Ingresar</span></button>
                </div>
              </form>
            </div>
        </div>
        <!-- Portfolio-->
        <a id="portfolio" href="<?= LINK_WEB ?>" title="Ir a página web" target="_blank">
            <i class="fa fa-link">Web</i>
        </a>
        </div>
    </div>
</body>
</html>