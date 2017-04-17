<?php 
require_once dirname(__FILE__).'/conexionBD.class.php';

/**
* Clase general que contiene 
*/
class Generalquery extends ConexionBD
{

	private $tabla,$id,$order,$campo,$campo_order;
	
	function __construct($tabla='',$id_registro=0,$order='',$campo='',$campo_order='')
	{
		# code...
		$this->tabla = $tabla;//nombre de tabla
		$this->id_registro = $id_registro;
		$this->order = $order;
		$this->campo = $campo;
		$this->campo_order = $campo_order;
	}

	public function getTabla(){
		return $this->_tabla;
	}
	public function setTabla($tabla){
		$this->_tabla=$tabla;
	}
	public function getId(){
		return $this->_id;
	}
	public function setId($id){
		$this->_id=$id;
	}
	public function getOrder(){
		return $this->_order;
	}
	public function setOrder($order){
		$this->_order=$order;
	}
	public function getCampo(){
		return $this->_campo;
	}
	public function setCampo($campo){
		$this->_campo=$campo;
	}
	public function getCampo_order(){
		return $this->campo_order;
	}
	public function setCampo_order($campo_order){
		$this->campo_order=$campo_order;
	}

	private function resultSelect($query)
	{
		$result = $this->conectBD()->prepare($query);
		$result->execute();
		$call = $result->fetchAll();
		return $call;
	}

	private function result($query)
	{
		$result=$this->conectBD()->query($query);
		return $result;
	}



	//SELECT
	public function getAll(){
		$query='call sp_select_getAll("'.$this->getTabla().'","'.$this->getCampo().'","'.$this->getOrder().'");';	
		$rpta = $this->resultSelect($query);
		return $rpta;	
	}

	public function getAllFieldInt($valorCampo){
		$query='call sp_select_getAllFieldInteger("'.$this->getTabla().'","'.$this->getCampo_order().'","'.$this->getOrder().'","'.$this->getCampo().'",'.$valorCampo.');';
		$rpta = $this->resultSelect($query);
		return $rpta;	
	}

	public function getAllFieldStr($valorCampo){
		$query='call sp_select_getAllFieldString("'.$this->getTabla().'","'.$this->getCampo_order().'","'.$this->getOrder().'","'.$this->getCampo().'","'.$valorCampo.'");';
		$rpta = $this->resultSelect($query);
		return $rpta;	
	}

	public function getField(){
		$query='call sp_select_getField("'.$this->getTabla().'","'.$this->getCampo_order().'","'.$this->getOrder().'","'.$this->getCampo().');';
		$rpta = $this->resultSelect($query);
		return $rpta;
	}

	public function countRowsInt($valorCampo){
		$query='call sp_select_countRowsInteger("'.$this->getTabla().'","'.$this->getCampo().'",'.$valorCampo.');';
		$rpta = $this->resultSelect($query);
		return $rpta;
	}

	public function countRowsStr($valorCampo){
		$query='call sp_select_countRowsString("'.$this->getTabla().'","'.$this->getCampo().'",'.$valorCampo.');';
		$rpta = $this->resultSelect($query);
		return $rpta;
	}


	//UPDATE

	public function updateOneField($valorId,$valorCampo){
		$query='call sp_update_oneField("'.$this->getTabla().'","'.$this->getCampo().'",'.$valorCampo.',"'.$this->getId().'",'.$valorId.');';
		$call=$this->result($query);
		return $call;
	}

	//DELETE

	public function deleteFieldStr($valorCampo){
		$query='call sp_delete_fieldString("'.$this->getTabla().'","'.$this->getCampo().'","'.$valorCampo.'");';
		$call=$this->conectBD()->query($query);
	}

	public function deleteFieldInt($valorId){
		$query='call sp_delete_fieldInteger("'.$this->getTabla().'","'.$this->getCampo().'",'.$valorId.');';
		$call=$this->conectBD()->query($query);
	}



}


?>






