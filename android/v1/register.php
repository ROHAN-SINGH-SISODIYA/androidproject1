<?php
require_once '../includes/dboperation.php';

$response=array();

if($_SERVER['REQUEST_METHOD']=='POST') 
{

	if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email']))
	{
		$db=new dboperation();
		if($db->createUser($_POST['username'],$_POST['password'],$_POST['email']))
		{
			$response['error']=false;
			$response['message']="user register suceessfully";
		}
		else
		{
			$response['error']=true;
			$response['message']="required feilds";
		}
	}
	else
	{
		$response['error']=true;
		$response['message']="Some Technical Problem ";
	}
	
}
else
	{
		$response['error']=true;
		$response['message']="Invalid request";
	}
echo json_encode($response);
?>