<?php 

/**
* 
*/
class Reportes
{
	
	/*function __construct(argument)
	{
		# code...
	}
*/

	public function generaDireccion($latitud, $longitud) {

       $url  = "http://maps.googleapis.com/maps/api/geocode/json?latlng=".$latitud.",".$longitud."&sensor=false";
       $json = @file_get_contents($url);
       $data = json_decode($json);
       $status = $data->status;
       $address = '';
       if($status == "OK") {
           $address = $data->results[0]->formatted_address;
           return $address;
       } else {
       }
   }


}

 ?>