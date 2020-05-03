<?php 
	
	require "connection.php";
	$fullname = "Alex Doe";
	$email = "alexdoe23@gmail.com";
	$mobile = "1234568790";
	$username = "alex123";
	$password = "alex123";


	$sql_query = "INSERT INTO registration (id,fullname,email,mobile,username,password) VALUES (null,'$fullname','$email','$mobile','$username','$password')";

	if(mysqli_query($connection,$sql_query)){
		echo "Data Inserted Successfully";
	}else{
		echo "Error to insert data";
	}

?>