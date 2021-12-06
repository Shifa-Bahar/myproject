<?php
include 'connection.php';


$username=$_POST['username'];
$email=$_POST['email'];
$password=md5($_POST['password']);

$insertQuery="INSERT INTO user(username,email,password) VALUES('$username','$email','$password')";
$result=mysqli_query($con,$insertQuery);

if($result)
{
    // echo 'register sucess';
    $response['error']="200";
  	$response['message']="Register successful!";
}
else
{
    // echo 'register failed';
    $response['error']="400";
  	$response['message']="Registeration failed!";
}


echo json_encode($response);



?>