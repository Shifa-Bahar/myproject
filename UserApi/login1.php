<?php
 include 'connection.php';


 $email=$_POST['email'];
 $password=md5($_POST['password']);
 $checkUser = "SELECT * FROM user WHERE email='$email'and password = '$password'";
 $result=mysqli_query($con,$checkUser);

 if(mysqli_num_rows($result)>0)
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




