<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String reg_username = (String) session.getAttribute("s_reg_u");
	%>

	<h2>
		Welcome
		<%= reg_username %>
	</h2>
	<h3>Thank you for Registering</h3>
	<

</body>
</html>