<?php

class dbconnect
{
	private $con;
	function __construct()
	{
	     
	}

	function connect()
	{
		include_once dirname(__FILE__).'/constants.php';
		$this->con=new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);

		if (mysqli_connect_errno())
		{
			echo "faild to connect".mysqli_connect_err();
		}
		return $this->con;
	}
}

?>