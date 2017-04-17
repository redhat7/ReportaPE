<?php 

	require_once dirname(__FILE__).'/conexionBD.class.php';
	include '../config/config.php';

	class Login extends ConexionBD

	{
		private $usuario, $password, $tipo_trabajador;
		public $sesionId="", $sesionPass="",$dataUser,$f_dataUser, $msgValidacion;
		
		function __construct($usuario="", $password="", $tipo_trabajador="")
		{
			$this->_usuario=$usuario;
			$this->_password=$password;
			$this->_tipo_trabajador=$tipo_trabajador;
		}
		public function getUsuario(){
			return $this->_usuario;
		}

		public function setUsuario($usuario){
			$this->_usuario=$usuario;
		}
		public function getPassword(){
			return $this->_password;
		}

		public function setPassword($password){
			$this->_password=$password;
		}
		public function getTipo_trabajador(){
			return $this->_tipo_trabajador;
		}

		public function setTipo_trabajador($tipo_trabajador){
			$this->_tipo_trabajador=$tipo_trabajador;
		}

		public function Validar(){

			// Obtenemos los valores que han sido cargados;
			$id_trabajador=$this->getTipo_trabajador();
			$usuario=$this->getUsuario();
			$password=$this->getPassword();

			if($usuario == SUPER_US and $password==md5(SUPER_PASS)){
				$tipo_Trabajador_id = 0;
				$tipo_Trabajador_nombre = 'Super-Admin';
				$tipo_Trabajador_codigo = 'admin';
			}else{
				$query='call sp_select_validacionLogin(1,"'.$usuario.'","'.$password.'")';
				$consulta=$this->conectBD()->query($query);

				//El resultado solo me votará 1 fila.
				foreach($consulta as $col);	
				$tipo_Trabajador_id = $col['tipotrabajador_id'];
				$tipo_Trabajador_nombre = $col['tipotrabajador_nombre'];
				$tipo_Trabajador_codigo = $col['tipotrabajador_codigo'];
			}
			
			if ($tipo_Trabajador_codigo!='') {
				session_start();
				$_SESSION['user_login']=array(
					"ses_usuario" => $usuario
					,"ses_password" => $password
					,"ses_tipoTrabajador_nombre" => $tipo_Trabajador_nombre
					,"ses_tipoTrabajador_codigo" => $tipo_Trabajador_codigo
					,"ses_tipoTrabajador_id" => $tipo_Trabajador_id
					);

				return $this->msgValidacion=1;

			}else{
				return $this->msgValidacion=2;
				//echo "El inicio de sesión no es válido.";
			}
		}

		public function getDataUser(){		
			session_start();
			$this->dataUser = $_SESSION['user_login'];
			return $this->dataUser;
		}

		public function validarSesion(){
			//session_start();
			$this->f_dataUser=$this->getDataUser();
			if($this->f_dataUser!=''){
				return 1;
			}else{
				//header('Location: ../');
				echo '<script>document.location=("../");</script>';
			}
		}

		public function cerrarSesion($op){
			if(isset($op) and $op !=''){
				session_destroy();
				//header('Location: ../');
				echo '<script>document.location=("../");</script>';
			}else{
				return 0;
			}


		}

		/*public function listarTipoTrabajador(){
			$query='call sp_select_listarTipotrabajador();';
			$consulta=$this->conectBD()->query($query);
			$listid='';
			foreach($consulta as $col){
				$listid = $listid.'<option value='.$col['tipotrabajador_id'].'>'.$col['tipotrabajador_nombre'].'</option>';
			}
			return $listid;
		}*/
	}
 ?>