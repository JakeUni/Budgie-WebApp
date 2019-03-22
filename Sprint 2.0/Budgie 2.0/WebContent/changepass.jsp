<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<form action="changepass" method="post">
		Enter username : <input type="text" name="user"><br>
		Enter current password : <input type="password" name="oldpass"><br>
		Enter new password : <input type="password" name="newpass1"><br>
		Enter new password again : <input type="password" name="newpass2"><br>
		<input type="submit" value="Change password">
	</form>

</body>
</html>