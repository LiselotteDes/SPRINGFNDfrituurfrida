<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 session="false"%>
<!DOCTYPE html>
<html lang='nl'>
<head>
<meta charset="UTF-8">
<title>Frituur Frida</title>
<link rel="icon" href="images/frida.ico" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/frituurfrida.css">
</head>
<body>
	<h1>Frituur Frida</h1>
	<h2>Vandaag zijn we ${sluitingsdag}</h2>
	<img src="images/${sluitingsdag}.png" alt="${sluitingsdag}">
</body>
</html>