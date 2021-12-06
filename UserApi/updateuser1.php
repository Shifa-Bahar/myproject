<?php
 include 'connection.php';

    $id=$_REQUEST['id'];
    $username=$_POST['username'];
    $email=$_POST['email'];
    

    $update_query="UPDATE user SET username='$username', email='$email' WHERE id='$id'";
    $result=mysqli_query($con,$update_query);

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