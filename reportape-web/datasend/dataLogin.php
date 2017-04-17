<?php 

$usuario_post='';
$contrasena_post='';
$op_login = '';
$tipotrab_post = '';

//Datos POST logins
$usuario_post = htmlspecialchars($_POST['usuario']);
$contrasena_post = htmlspecialchars(md5($_POST['password']));
$tipotrab_post= htmlspecialchars($_POST['tipo_trab']);
$op_login =  htmlspecialchars($_POST['op-login']);
?>