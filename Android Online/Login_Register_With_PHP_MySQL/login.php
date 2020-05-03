<?php 
	
	require "connection.php";

	$sql_query = "SELECT * FROM registration where username = '123' && password = '123'; ";
	$result = mysqli_query($connection,$sql_query);
	if(mysqli_num_rows($result) > 0){
		echo "login success \n";
	}else{
		echo "login fail \n";
	}

	while($line = mysqli_fetch_assoc($result)){
		$id = $line['id'];
		$fullname = $line['fullname'];
		$email = $line['email'];
		$mobile = $line['mobile'];
		$username = $line['username'];
		$password = $line['password'];


		echo "<br> ID : ".$id."<br> 
			Full Name : ".$fullname."<br> 
			Email : ".$email."<br>
			Mobile : ".$mobile."<br>
			Username : ".$username."<br>
			Password : ".$password."<br><br><br>";
	}




?>