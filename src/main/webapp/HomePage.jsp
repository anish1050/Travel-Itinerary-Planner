<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WanderCraft - Travel Itinerary Planner</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap')
	;

body {
	font-family: 'Poppins', sans-serif;
	min-height: 100vh;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.navbar {
	padding: 0;
	width: 100%;
	background-color: rgba(255, 255, 255, 0.95) !important;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.container-fluid {
	padding: 0;
	position: relative;
}

#logovideo {
	height: 200px;
	width: 250px;
}

.navbar-brand.logo-container {
	padding: 0;
	margin: 0;
}

.brand-text {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	font-size: 1.8rem;
	font-weight: 600;
	color: #2c3e50;
}

.hero-section {
	padding: 4rem 0;
	text-align: center;
	color: white;
}

.hero-section h1 {
	font-size: 3.5rem;
	font-weight: 700;
	margin-bottom: 1rem;
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.hero-section p {
	font-size: 1.3rem;
	margin-bottom: 3rem;
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}

.continent-card {
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	border: none;
	border-radius: 15px;
	overflow: hidden;
	box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
	background: white;
}

.continent-card:hover {
	transform: translateY(-10px);
	box-shadow: 0 15px 40px rgba(0, 0, 0, 0.25);
}

.card-img-container {
	height: 250px;
	overflow: hidden;
	position: relative;
}

.card-img-container img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.3s ease;
}

.continent-card:hover .card-img-container img {
	transform: scale(1.1);
}

.card-body {
	padding: 2rem;
}

.card-title {
	font-size: 1.8rem;
	font-weight: 600;
	color: #2c3e50;
	margin-bottom: 1rem;
}

.card-text {
	font-size: 1rem;
	color: #7f8c8d;
	line-height: 1.6;
}

.explore-btn {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	border: none;
	color: white;
	font-weight: 600;
	padding: 0.8rem 2rem;
	border-radius: 25px;
	transition: all 0.3s ease;
	text-transform: uppercase;
	letter-spacing: 1px;
}

.explore-btn:hover {
	background: linear-gradient(135deg, #764ba2 0%, #667eea 100%);
	transform: translateY(-2px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.auth-buttons {
	display: flex;
	gap: 1rem;
	justify-content: center;
	margin-top: 2rem;
}

.auth-btn {
	padding: 0.8rem 2rem;
	border: 2px solid white;
	background: transparent;
	color: white;
	border-radius: 25px;
	font-weight: 600;
	text-decoration: none;
	transition: all 0.3s ease;
}

.auth-btn:hover {
	background: white;
	color: #667eea;
	text-decoration: none;
}

.features-section {
	padding: 4rem 0;
	background: rgba(255, 255, 255, 0.1);
	backdrop-filter: blur(10px);
}

.logout-btn {
	background: linear-gradient(135deg, #e74c3c 0%, #c0392b 100%);
	border: none;
	color: white;
	font-weight: 600;
	padding: 0.5rem 1.5rem;
	border-radius: 20px;
	transition: all 0.3s ease;
}

.logout-btn:hover {
	background: linear-gradient(135deg, #c0392b 0%, #e74c3c 100%);
	transform: translateY(-1px);
}
</style>
</head>
<body>
	<%
    // Check if user is logged in
    String log_username = (String) session.getAttribute("s_log_u");
    boolean isLoggedIn = (log_username != null && !log_username.isEmpty());
    %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand logo-container" href="HomePage.jsp"> <video
					id="logovideo"
					src="Yellow and Green Island Flat Illustrative Hotels and Travel Service Animated Logo.mp4"
					autoplay muted loop></video>
			</a> <span class="brand-text">WanderCraft</span>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<div class="navbar-nav ms-auto">
					<% if (isLoggedIn) { %>
					<a class="nav-link" href="InitialUserLoginProfile.jsp">My
						Profile</a> <a class="nav-link" href="FinalUserLoginProfile.jsp">Create
						Itinerary</a>
					<form action="JWTLogout" method="post" class="d-inline">
						<button type="submit" class="btn logout-btn ms-2">Logout</button>
					</form>
					<% } else { %>
					<a class="nav-link" href="LoginPage.html">Login</a> <a
						class="nav-link" href="RegistrationPage.html">Register</a>
					<% } %>
				</div>
			</div>
		</div>
	</nav>

	<!-- Hero Section -->
	<div class="hero-section">
		<div class="container">
			<% if (isLoggedIn) { %>
			<h1>
				Welcome back,
				<%= log_username %>!
			</h1>
			<p>Ready to plan your next adventure?</p>
			<p class="lead">Explore amazing destinations and create
				personalized travel itineraries</p>
			<div class="auth-buttons">
				<a href="FinalUserLoginProfile.jsp" class="auth-btn">Create New
					Itinerary</a> <a href="InitialUserLoginProfile.jsp" class="auth-btn">View
					My Itineraries</a>
			</div>
			<% } else { %>
			<h1>Welcome to WanderCraft</h1>
			<p>Your Ultimate Travel Itinerary Planner</p>
			<p class="lead">Discover amazing destinations and create
				personalized travel itineraries for your perfect adventure</p>
			<div class="auth-buttons">
				<a href="LoginPage.html" class="auth-btn">Get Started</a> <a
					href="RegistrationPage.html" class="auth-btn">Join Now</a>
			</div>
			<% } %>
		</div>
	</div>

	<!-- Continents Section -->
	<div class="features-section">
		<div class="container">
			<div class="text-center mb-5">
				<h2 class="text-white mb-3">Explore By Continent</h2>
				<p class="text-white-50">Choose your destination and start
					planning your dream vacation</p>
			</div>

			<div class="row justify-content-center g-4">
				<!-- Asia Card -->
				<div class="col-md-5">
					<div class="continent-card card h-100">
						<div class="card-img-container">
							<img
								src="https://images.unsplash.com/photo-1480714378408-67cf0d13bc1f?w=600&h=400&fit=crop"
								class="card-img-top" alt="Asia">
						</div>
						<div class="card-body text-center">
							<h4 class="card-title">Explore Asia</h4>
							<p class="card-text">Discover the mystical temples, bustling
								cities, and rich cultures of Asia. From the serene landscapes of
								Japan to the vibrant markets of India, Asia offers unforgettable
								experiences.</p>
							<div class="d-grid gap-2 col-8 mx-auto">
								<a href="Asia.html" class="btn explore-btn">Explore Asia</a>
							</div>
						</div>
					</div>
				</div>

				<!-- Europe Card -->
				<div class="col-md-5">
					<div class="continent-card card h-100">
						<div class="card-img-container">
							<img
								src="https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=600&h=400&fit=crop"
								class="card-img-top" alt="Europe">
						</div>
						<div class="card-body text-center">
							<h4 class="card-title">Explore Europe</h4>
							<p class="card-text">Experience the charm of European cities,
								from romantic Paris to historic Rome. Discover art, culture,
								cuisine, and architecture that has shaped the world for
								centuries.</p>
							<div class="d-grid gap-2 col-8 mx-auto">
								<a href="Europe.html" class="btn explore-btn">Explore Europe</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>