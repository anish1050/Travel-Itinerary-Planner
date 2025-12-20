<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Itinerary</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://kit.fontawesome.com/1610670336.css" rel="stylesheet">
<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap');

html {
	overflow-y: scroll;
}

body {
	font-family: 'Poppins', sans-serif;
	background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
	min-height: 100vh;
	padding: 0;
	margin: 0;
}

.navbar {
	width: 100%;
	background-color: #fff !important;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	padding: 0.5rem 1rem;
}

.logo-container {
	display: flex;
	align-items: center;
}

#logovideo {
	height: clamp(50px, 10vw, 80px);
	width: clamp(60px, 12vw, 100px);
}

.brand-text {
	font-weight: 600;
	font-size: clamp(1.2rem, 3vw, 1.5rem);
	margin-left: 10px;
	color: #333;
}

.form-container {
	max-width: 600px;
	margin: 40px auto;
	background: white;
	padding: 40px;
	border-radius: 15px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.form-title {
	text-align: center;
	margin-bottom: 30px;
	font-size: 28px;
	font-weight: 600;
	color: #333;
}

.location-info {
	background-color: #f8f9fa;
	padding: 15px;
	border-radius: 8px;
	margin-bottom: 25px;
	border-left: 4px solid #DCBFA6;
}

.location-info h6 {
	color: #666;
	margin: 5px 0;
	font-size: 14px;
}

.location-info strong {
	color: #333;
}

.form-group {
	margin-bottom: 20px;
}

.form-label {
	font-weight: 600;
	color: #333;
	margin-bottom: 8px;
	display: block;
}

.form-control, .form-select {
	border: 2px solid #e9ecef;
	border-radius: 8px;
	padding: 10px 15px;
	font-size: 15px;
	transition: all 0.3s ease;
	width: 100%;
	font-family: 'Poppins', sans-serif;
	resize: none;
}

.form-control:focus, .form-select:focus {
	border-color: #DCBFA6;
	box-shadow: 0 0 0 3px rgba(220, 191, 166, 0.1);
	outline: none;
}

.btn-container {
	display: flex;
	gap: 10px;
	margin-top: 30px;
}

.btn {
	flex: 1;
	padding: 12px;
	font-size: 16px;
	font-weight: 600;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: all 0.3s ease;
}

.btn-submit {
	background-color: #DCBFA6;
	color: white;
}

.btn-submit:hover {
	background-color: #c9a689;
	transform: translateY(-2px);
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.btn-cancel {
	background-color: #e9ecef;
	color: #333;
	text-decoration: none;
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn-cancel:hover {
	background-color: #dee2e6;
	transform: translateY(-2px);
	color: #333;
	text-decoration: none;
}

@media (max-width: 576px) {
	.form-container {
		margin: 20px;
		padding: 25px;
	}
	
	.btn-container {
		flex-direction: column;
	}
}
</style>
</head>
<body>

<%
String city = request.getParameter("city");
String country = request.getParameter("country");
String continent = request.getParameter("continent");
String activity1 = request.getParameter("activity1");
String activity2 = request.getParameter("activity2");
String activity3 = request.getParameter("activity3");
String activity4 = request.getParameter("activity4");

city = (city != null) ? city : "";
country = (country != null) ? country : "";
continent = (continent != null) ? continent : "";
activity1 = (activity1 != null) ? activity1 : "";
activity2 = (activity2 != null) ? activity2 : "";
activity3 = (activity3 != null) ? activity3 : "";
activity4 = (activity4 != null) ? activity4 : "";
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand logo-container"
			href="<%=request.getContextPath()%>/InitialUserLoginProfile">
			<video id="logovideo"
				src="Yellow and Green Island Flat Illustrative Hotels and Travel Service Animated Logo.mp4"
				autoplay muted></video>
			<span class="brand-text">WanderCraft</span>
		</a>
	</div>
</nav>

<div class="form-container">
	<h1 class="form-title">Update Itinerary</h1>

	<div class="location-info">
		<h6><strong>Location:</strong> <%=city%>, <%=country%></h6>
		<h6><strong>Continent:</strong> <%=continent%></h6>
	</div>

	<form action="<%=request.getContextPath()%>/UpdateItinerary" method="POST">
		<input type="hidden" name="continent" value="<%=continent%>">
		<input type="hidden" name="country" value="<%=country%>">
		<input type="hidden" name="city" value="<%=city%>">

		<div class="form-group">
			<label class="form-label">Activity 1</label>
			<textarea class="form-control" name="activity1" rows="2" placeholder="Enter activity" style="resize: none;"><%=activity1%></textarea>
		</div>

		<div class="form-group">
			<label class="form-label">Activity 2</label>
			<textarea class="form-control" name="activity2" rows="2" placeholder="Enter activity" style="resize: none;"><%=activity2%></textarea>
		</div>

		<div class="form-group">
			<label class="form-label">Activity 3</label>
			<textarea class="form-control" name="activity3" rows="2" placeholder="Enter activity" style="resize: none;"><%=activity3%></textarea>
		</div>

		<div class="form-group">
			<label class="form-label">Activity 4</label>
			<textarea class="form-control" name="activity4" rows="2" placeholder="Enter activity" style="resize: none;"><%=activity4%></textarea>
		</div>

		<div class="btn-container">
			<button type="submit" class="btn btn-submit">
				<i class="fas fa-save me-2"></i>Update
			</button>
			<a href="<%=request.getContextPath()%>/InitialUserLoginProfile" class="btn btn-cancel">
				<i class="fas fa-times me-2"></i>Cancel
			</a>
		</div>
	</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/1610670336.js" crossorigin="anonymous"></script>

</body>
</html>