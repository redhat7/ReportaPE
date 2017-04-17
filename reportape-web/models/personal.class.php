<?php 
require_once dirname(__FILE__).'/conexionBD.class.php';
/**
* 
*/
class Personal extends ConexionBD 
{

	private $p_nombre,$p_correo,$p_contrasena,$p_codigo,$p_tipo,$p_estado, $p_id , $repo_id, $rg_estado;
	
	function __construct($p_nombre='',$p_correo='',$p_contrasena='',$p_codigo='',$p_tipo='',$p_estado=1,$p_id='',$repo_id='',$rg_estado=0)
	{
		$this->p_nombre = $p_nombre;
		$this->p_correo = $p_correo;
		$this->p_contrasena = $p_contrasena;
		$this->p_codigo = $p_codigo;
		$this->p_tipo = $p_tipo;
		$this->p_estado = $p_estado;
		$this->p_id = $p_id;
		$this->repo_id = $repo_id;
		$this->rg_estado = $rg_estado;
	}

	public function getNombre(){
		return $this->p_nombre;
	}
	public function setNombre($p_nombre){
		$this->p_nombre = $p_nombre;
	}

	public function getCorreo(){
		return $this->p_correo;
	}

	public function setCorreo($p_correo){
		$this->p_correo = $p_correo;
	}

	public function getContrasena(){
		return $this->p_contrasena;
	}

	public function setContrasena($p_contrasena){
		$this->p_contrasena = $p_contrasena;
	}

	public function getCodigo(){
		return $this->p_codigo;
	}

	public function setCodigo($p_codigo){
		$this->p_codigo = $p_codigo;
	}

	public function getTipo(){
		return $this->p_tipo;
	}

	public function setTipo($p_tipo){
		$this->p_tipo = $p_tipo;
	}

	public function getId(){
		return $this->p_id;
	}

	public function setId($p_id){
		$this->p_id = $p_id;
	}

	public function getRepoId(){
		return $this->repo_id;
	}

	public function setRepoId($repo_id){
		$this->repo_id = $repo_id;
	}

	public function getRgEstado(){
		return $this->rg_estado;
	}

	public function setRgEstado($rg_estado){
		$this->rg_estado = $rg_estado;
	}
	//metodos para Personal:

	public function validaForm(){

		global $valForm;

		if($this->getNombre()!='' and $this->getCorreo()!='' and $this->getCodigo()!='' and $this->getTipo()!=''){

			if (!filter_var($this->getCorreo(), FILTER_VALIDATE_EMAIL)) {
				$valForm = 0;
			}else{
				$valForm=1;
			}
		}else{
			$valForm = 0;
		}

		return $valForm;

	}

	private function result($query)
	{
		$result=$this->conectBD()->query($query);
		return $result;
	}

	private function resultSelect($query)
	{
		$result = $this->conectBD()->prepare($query);
		$result->execute();
		$call = $result->fetchAll();
		return $call;
	}


	public function addPersonal(){
		$query='
			insert into 
				trabajador(
					trabajador_nombre
					,trabajador_correo
					,trabajador_contrasena
					,trabajador_estado
					,trabajador_codigo
					,tipotrabajador_id
				)values(
					"'.$this->getNombre().'"
					,"'.$this->getCorreo().'"
					,"'.$this->getContrasena().'"
					,'.$this->p_estado.'
					,"'.$this->getCodigo().'"
					,'.$this->getTipo().'
				)
		';	
		$rpta = $this->result($query);
		return $rpta;	
	}

	public function addAsignReporte(){
		$query='
			insert into 
				trabajador_reportegrupal(
					trabajador_id
					,reportegrupal_id
					,trabajador_reportegrupal_estado
				)values(
					'.$this->getId().'
					,'.$this->getRepoId().'
					,1
				)
		';	
		$rpta = $this->result($query);
		return $rpta;
	}

	public function countRepoPersonal(){
		$query='
		select count(*) as countReg 
		from 
			trabajador_reportegrupal 
		where 
			trabajador_reportegrupal_estado = 1 
		and 
			trabajador_id = '.$this->getId();	
		$rpta = $this->resultSelect($query);
		return $rpta;
	}

	public function validRepoAsign(){
		$query='
		select count(*) as countReg 
		from 
			trabajador_reportegrupal 
		where 
			trabajador_reportegrupal_estado = 1 
		and 
			trabajador_id = '.$this->getId().'
		and
			reportegrupal_id = '.$this->getRepoId();
		$rpta = $this->resultSelect($query);
		return $rpta;
	}

	public function reportesAsignados(){
		$query = '
			select * from 
				trabajador_reportegrupal tr
			inner join
				reportegrupal r
			on
				tr.reportegrupal_id = r.reportegrupal_id
			where 
				tr.trabajador_id = '.$this->getId().'
			and
				trabajador_reportegrupal_estado = '.$this->getRgEstado().'
			order by 
				trabajador_reportegrupal_id DESC;
		';
		$rpta = $this->resultSelect($query);
		return $rpta;
	}

}

 ?>