<?php

	$server = "localhost";
	$database = "app_online_practice";
	$table = "registration";
	$username = "root";
	$password = "";

	$connection = mysqli_connect($server,$username,$password,$database);

	if($connection){
		echo "Connected\n";
	}else{
		echo "Not Connected \n";
	}
?>