<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Travel Itinerary</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap');

html {
	overflow-y: scroll;
}

body {
	font-family: 'Poppins', sans-serif;
	min-height: 100vh;
	background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
	margin: 0;
	padding: 0;
}

/* ================= NAVBAR ================= */
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

/* ================= CONTENT ================= */
.container {
	text-align: center;
	padding: clamp(20px, 5vw, 40px);
}

#username2 {
	font-size: clamp(1.5rem, 4vw, 2rem);
	font-weight: 600;
	color: #333;
}

/* ================= ADD ITINERARY CARD ================= */
#plusicon {
	background: linear-gradient(135deg, #DCBFA6 50%);
	border-radius: 15px;
	padding: clamp(20px, 5vw, 40px);
	margin-bottom: clamp(30px, 6vw, 60px);
	transition: all 0.3s ease;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

#plusicon:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

#plusicon {
	cursor: pointer;
}

#plusicon a {
	color: white;
	text-decoration: none;
	display: block;
	width: 100%;
	height: 100%;
}

#plusicon i, #plusicon h4 {
	pointer-events: none;
}

/* ================= CARDS ================= */
.card {
	border-radius: 15px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	transition: all 0.3s ease;
	height: 100%;
	border: none;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.card-header {
	border-radius: 15px 15px 0 0 !important;
}

.activity-list li {
	background-color: #fff3cd;
	border-radius: 8px;
	padding: 10px;
	margin-bottom: 8px;
}

.btn {
	transition: all 0.3s ease;
}

.btn:hover {
	transform: translateY(-2px);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* ================= MODAL ================= */
.modal-dialog {
	margin: 0 auto;
}

/* ================= RESPONSIVE ================= */
@media (max-width: 576px) {
	.col-lg-4 {
		flex: 0 0 100%;
		max-width: 100%;
	}
	.d-flex {
		flex-direction: column;
	}
	.btn {
		width: 100% !important;
	}
}
</style>
</head>

<body>

<%
	String log_username = (String) session.getAttribute("s_log_u");
%>

<!-- ================= NAVBAR ================= -->
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

<!-- ================= MAIN CONTENT ================= -->
<div class="container mt-4">

	<h2 id="username2">Welcome back <%=log_username%></h2>

	<div id="plusicon" class="mt-4">
		<a href="FinalUserLoginProfile">
			<i class="fa fa-plus fa-3x"></i>
			<h4 class="mt-3">Start Creating a New Personalized Itinerary</h4>
		</a>
	</div>

	<h2 class="mb-4">Your Earlier Travel Itineraries</h2>

	<div class="row">
		<core:forEach var="row" items="${itineraries}">
			<div class="col-md-6 col-lg-4 mb-4">
				<div class="card" data-city="${row.city}" data-country="${row.country}">
					<div class="card-header bg-warning-subtle">
						<h5>${row.city}, ${row.country}</h5>
					</div>

					<div class="card-body">
						<h6 class="text-muted">Continent: ${row.continent}</h6>

						<ul class="activity-list list-unstyled mt-3">
							<core:if test="${not empty row.activity1}">
								<li>${row.activity1}</li>
							</core:if>
							<core:if test="${not empty row.activity2}">
								<li>${row.activity2}</li>
							</core:if>
							<core:if test="${not empty row.activity3}">
								<li>${row.activity3}</li>
							</core:if>
							<core:if test="${not empty row.activity4}">
								<li>${row.activity4}</li>
							</core:if>
						</ul>

						<div class="d-flex gap-2 mt-3">
							<form action="UpdateForm.jsp" method="POST" class="w-100">
								<input type="hidden" name="continent" value="${row.continent}">
								<input type="hidden" name="country" value="${row.country}">
								<input type="hidden" name="city" value="${row.city}">
								<input type="hidden" name="activity1" value="${row.activity1}">
								<input type="hidden" name="activity2" value="${row.activity2}">
								<input type="hidden" name="activity3" value="${row.activity3}">
								<input type="hidden" name="activity4" value="${row.activity4}">
								<button class="btn btn-warning w-100">
									<i class="fas fa-edit"></i> Update
								</button>
							</form>

							<button class="btn btn-danger delete-btn"
								data-continent="${row.continent}"
								data-country="${row.country}"
								data-city="${row.city}">
								<i class="fas fa-trash"></i> Delete
							</button>
						</div>
					</div>
				</div>
			</div>
		</core:forEach>
	</div>
</div>

<!-- ================= DELETE MODAL ================= -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Confirm Deletion</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<div class="modal-body">
				Are you sure you want to delete itinerary for
				<strong id="deleteLocationText"></strong>?
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				<button class="btn btn-danger" id="confirmDeleteBtn">Delete</button>
			</div>
		</div>
	</div>
</div>

<!-- ================= SCRIPTS ================= -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/1610670336.js" crossorigin="anonymous"></script>

<script>
let deleteModal;
let currentDeleteData = null;

document.addEventListener('DOMContentLoaded', () => {
	deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));

	// Handle delete button clicks
	document.querySelectorAll('.delete-btn').forEach(btn => {
		btn.addEventListener('click', () => {
			currentDeleteData = {
				continent: btn.dataset.continent,
				country: btn.dataset.country,
				city: btn.dataset.city,
				cardElement: btn.closest('.card').parentElement
			};
			
			document.getElementById('deleteLocationText').textContent =
				btn.dataset.city + ', ' + btn.dataset.country;
			deleteModal.show();
		});
	});

	// Handle confirm delete button
	document.getElementById('confirmDeleteBtn').addEventListener('click', performDelete);
});

function performDelete() {
	if (!currentDeleteData) return;

	const btn = document.getElementById('confirmDeleteBtn');
	btn.disabled = true;
	btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Deleting...';

	const params = new URLSearchParams();
	params.append('username', '<%=log_username%>');
	params.append('continent', currentDeleteData.continent);
	params.append('country', currentDeleteData.country);
	params.append('city', currentDeleteData.city);

	fetch('<%=request.getContextPath()%>/DeleteItinerary', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		body: params.toString()
	})
	.then(response => {
		if (response.status === 200) {
			deleteModal.hide();

			// Fade out card
			const card = currentDeleteData.cardElement;
			card.style.transition = 'opacity 0.3s ease';
			card.style.opacity = '0';

			setTimeout(() => {
				card.remove();

				// Check if any cards left
				if (document.querySelectorAll('.card').length === 0) {
					setTimeout(() => location.reload(), 300);
				}
			}, 300);

			currentDeleteData = null;
		} else {
			throw new Error('Server error: ' + response.status);
		}
	})
	.catch(error => {
		console.error('Error:', error);
		alert('Error deleting itinerary. Please try again.');
		deleteModal.hide();
		btn.disabled = false;
		btn.innerHTML = 'Delete';
		currentDeleteData = null;
	});
}
</script>

</body>
</html>