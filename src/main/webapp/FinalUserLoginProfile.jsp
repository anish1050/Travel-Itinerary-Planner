<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Travel Destinations</title>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap')
	;

body {
	font-family: 'Poppins', sans-serif;
	min-height: 100vh;
	background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
	margin: 0;
	padding: 0;
}

.navbar {
	width: 100%;
	background-color: #fff !important;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.navbar-brand {
	display: flex;
	align-items: center;
}

.brand-text {
	font-weight: 600;
	font-size: 1.5rem;
	margin-left: 10px;
	color: #333;
}

#logovideo {
	height: 80px;
	width: 100px;
}

#myprofilebtn {
	font-family: 'Poppins', sans-serif;
	padding: 10px 25px;
	border: 2px solid #333;
	background: beige;
	color: #333;
	border-radius: 10px;
	font-weight: 600;
	transition: all 0.3s ease;
	cursor: pointer;
}

#myprofilebtn:hover {
	background: #333;
	color: beige;
}

.container {
	max-width: 1200px;
	padding: 40px 20px;
}

.container h2 {
	text-align: center;
	font-weight: 600;
	color: #333;
	margin-bottom: 40px;
	font-size: 2rem;
}

.card {
	border: 2px solid #333;
	border-radius: 15px;
	overflow: hidden;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	background: #fff;
}

.card:hover {
	transform: translateY(-10px);
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}

.card-img-container {
	width: 100%;
	height: 250px;
	overflow: hidden;
}

.card-img-top {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.3s ease;
}

.card:hover .card-img-top {
	transform: scale(1.1);
}

.card-body {
	padding: 20px;
}

.card-title {
	color: #333;
	font-weight: 600;
	margin-bottom: 15px;
	font-size: 1.5rem;
}

.card-text {
	color: #555;
	line-height: 1.6;
	margin-bottom: 20px;
}

.btn {
	font-family: 'Poppins', sans-serif;
	padding: 12px 30px;
	border: 2px solid #333;
	background: beige;
	color: #333;
	border-radius: 10px;
	font-weight: 600;
	transition: all 0.3s ease;
	text-decoration: none;
}

.btn:hover {
	background: #333;
	color: beige;
}

.row {
	margin-bottom: 40px;
}

@media ( max-width : 768px) {
	.container h2 {
		font-size: 1.5rem;
		margin-bottom: 30px;
	}
	.brand-text {
		font-size: 1.2rem;
	}
	#logovideo {
		height: 60px;
		width: 80px;
	}
	.card-img-container {
		height: 200px;
	}
	.card-title {
		font-size: 1.3rem;
	}
	.card-text {
		font-size: 0.95rem;
	}
	#myprofilebtn {
		padding: 8px 20px;
		font-size: 0.9rem;
	}
}

@media ( max-width : 576px) {
	.container {
		padding: 20px 15px;
	}
	.container h2 {
		font-size: 1.3rem;
		margin-bottom: 25px;
	}
	.navbar-brand {
		flex-direction: column;
		align-items: flex-start;
	}
	.brand-text {
		margin-left: 0;
		font-size: 1rem;
	}
	.card-img-container {
		height: 180px;
	}
}
</style>
</head>
<body>

	<%
    String log_username = (String) session.getAttribute("s_log_u");
    %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand logo-container"
				href="<%= request.getContextPath() %>/FinalUserLoginProfile"> <video
					id="logovideo"
					src="Yellow and Green Island Flat Illustrative Hotels and Travel Service Animated Logo.mp4"
					autoplay muted loop></video> <span class="brand-text">WanderCraft</span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<div class="navbar-nav ms-auto">
					<a id="myprofile" href="InitialUserLoginProfile">
						<button id="myprofilebtn">My Profile</button>
					</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<h2><%= log_username %>, Which Continent would you like to visit?
		</h2>

		<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">

			<div class="col">
				<div id="card1" class="card h-100">
					<div class="card-img-container">
						<img id="firstcardimg" src="Asia.jpg" class="card-img-top"
							alt="Asia">
					</div>
					<div class="card-body">
						<h4 class="card-title">Explore Asia</h4>
						<p class="card-text">Asia, the largest and most populous
							continent, is renowned for its cultural diversity, historical
							landmarks, and economic powerhouses.</p>
					</div>
					<div class="d-grid gap-2 col-8 mx-auto mb-3">
						<a href="Asia.html"><button class="btn" type="button">Explore
								now</button></a>
					</div>
				</div>
			</div>


			<div class="col">
				<div id="card2" class="card h-100">
					<div class="card-img-container">
						<img id="secondcardimg" src="Europe.jpg" class="card-img-top"
							alt="Europe">
					</div>
					<div class="card-body">
						<h4 class="card-title">Explore Europe</h4>
						<p class="card-text">Europe is a culturally rich continent
							known for its historical landmarks, diverse nations, and economic
							and political influence globally.</p>
					</div>
					<div class="d-grid gap-2 col-8 mx-auto mb-3">
						<a href="Europe.html"><button class="btn" type="button">Explore
								now</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>

window.addEventListener("popstate", function (event) {
    const confirmLogout = confirm("Going back will log you out. Do you want to continue?");
    if (confirmLogout) {
        sessionStorage.clear();
        localStorage.clear();
        window.location.href = "JWTLogout";
    } else {
        history.pushState(null, null, location.href);
    }
});
</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>