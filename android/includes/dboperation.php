<?php

class dboperation 
{
	
	function __construct()
	{
		require_once dirname(__FILE__).'/dbconnect.php';
		$db=new dbconnect();
		$this->con=$db->connect();
	}
	function createUser($username,$pass,$email)
	{
		$password=md5($pass);
		$stmt=$this->con->prepare("INSERT INTO `users`(`id`, `username`, `password`, `email`) VALUES (null,?,?,?);");
		$stmt->bind_param("sss",$username,$password,$email);

		if ($stmt->execute())
		{
		  return true;	
		}
		else
		{
		  return false;
		}	
	}
}
?>