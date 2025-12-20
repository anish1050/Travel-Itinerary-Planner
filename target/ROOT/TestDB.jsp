<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database Test - WanderCraft</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

table {
	border-collapse: collapse;
	width: 100%;
	margin: 20px 0;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.success {
	color: green;
}

.error {
	color: red;
}

.test-form {
	background: #f9f9f9;
	padding: 20px;
	margin: 20px 0;
	border-radius: 5px;
}
</style>
</head>
<body>
	<h1>🔍 Database Connectivity Test</h1>

	<sql:setDataSource driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://mysql-anish.alwaysdata.net:3306/anish_travel_itinerary_db"
		user="anish" password="Anish@1050" var="conn" />

	<div class="success">✅ Database Connection Established
		Successfully!</div>

	<h2>📋 All Users in Database</h2>

	<sql:query var="users" dataSource="${conn}">
        SELECT username, firstname, lastname, email, password FROM registered_users
    </sql:query>

	<table>
		<tr>
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Password</th>
			<th>Username Length</th>
			<th>Password Length</th>
		</tr>
		<c:forEach var="user" items="${users.rows}">
			<tr>
				<td>${user.username}</td>
				<td>${user.firstname}</td>
				<td>${user.lastname}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.username.length()}</td>
				<td>${user.password.length()}</td>
			</tr>
		</c:forEach>
	</table>

	<h2>🧪 Login Test Form</h2>
	<div class="test-form">
		<p>
			<strong>Test your login credentials below:</strong>
		</p>
		<form action="User_Authentication" method="post">
			<p>
				<label>Username: </label> <input type="text" name="log_username"
					value="anish" style="padding: 5px;">
			</p>
			<p>
				<label>Password: </label> <input type="password" name="log_password"
					value="123" style="padding: 5px;">
			</p>
			<p>
				<input type="submit" value="Test Login"
					style="padding: 10px 20px; background: beige; border: 2px solid black; cursor: pointer;">
			</p>
		</form>
	</div>

	<h2>📝 Instructions</h2>
	<ol>
		<li><strong>Check the table above</strong> - These are the exact
			usernames and passwords from your database</li>
		<li><strong>Use exact values</strong> - Copy the username and
			password exactly as shown</li>
		<li><strong>Test the form</strong> - Try logging in with the
			pre-filled values</li>
		<li><strong>Watch for spaces</strong> - Make sure there are no
			extra spaces in usernames or passwords</li>
	</ol>

	<p>
		<a href="LoginPage.html">← Back to Login Page</a>
	</p>
</body>
</html>