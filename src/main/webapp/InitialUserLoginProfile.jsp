<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <!-- ===== Analytics Integration (GA4 & Amplitude) ===== -->
  <!-- Defer Google Analytics for better performance -->
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag() { dataLayer.push(arguments); }
    gtag('js', new Date());
    gtag('config', 'G-M7XP8Z071T', {
      'version': 'real'
    });

    // Load GA script after page load
    window.addEventListener('load', function () {
      var script = document.createElement('script');
      script.async = true;
      script.src = 'https://www.googletagmanager.com/gtag/js?id=G-M7XP8Z071T';
      document.head.appendChild(script);
    });
  </script>
  <!-- ===== Amplitude Analytics ===== -->
  <script src="https://cdn.amplitude.com/script/d36d22f09d7d68cf54baa6df63454945.js"></script>
  <script>
    if (window.amplitude) {
      window.amplitude.init('d36d22f09d7d68cf54baa6df63454945', {
        fetchRemoteConfig: true,
        autocapture: {
          attribution: true,
          pageViews: true,
          sessions: true,
          elementInteractions: true
        }
      });
      window.amplitude.track('Page View', { project: 'WanderCraft Real', version: 'real' });
    }
  </script>
    <meta charset="UTF-8">
    <title>Travel Itinerary</title>
    <link rel="stylesheet" href="InitialUserLoginProfile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
    
    @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

    body {
     font-family: poppins;
          }

        .card {
            margin-bottom: 20px;
            
            
        }
       
        .activity-list {
            list-style: none;
            padding-left: 0;
        }
        
        .activity-list li {
            margin-bottom: 8px;
            padding: 5px;
            background-color: beige;
            border-radius: 4px;
        }
    </style>
</head>
<body>

<%
String log_username = (String) session.getAttribute("s_log_u");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand logo-container" href="HomePage.html">
            <video id="logovideo" src="Yellow and Green Island Flat Illustrative Hotels and Travel Service Animated Logo.mp4" autoplay muted></video>
        </a>
        <span class="brand-text">WanderCraft</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav"></div>
    </div>
</nav>

<div class="container mt-4">
    <div id="username">
        <h2 id="username2" class="mb-4">Welcome back <%=log_username%></h2>
    </div>

    <div id="plusicon" class="text-center mb-4">
        <a href="FinalUserLoginProfile.jsp" class="text-decoration-none">
            <i class="fa fa-plus fa-4x"></i>
            <h4 id="textbelowicon" class="mt-2">Start Creating a new Personalized Itinerary</h4>
        </a>
    </div>

    <h2 class="mb-4">Your Earlier Travel Itineraries</h2>

    <sql:setDataSource driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/java_project" 
        user="root"
        password="Anish@1050" 
        var="conn" />

    <sql:query var="rs" dataSource="${conn}">
        select * from users_itinerary WHERE username = ?;
        <sql:param value="<%=log_username%>" />
    </sql:query>

   <div class="row">
    <core:forEach var="row" items="${rs.rows}">
        <div class="col-md-6 col-lg-4">
            <div class="card">
                <div class="card-header bg-warning-subtle text-dark">
                    <h5 class="card-title mb-0">${row.city}, ${row.country}</h5>
                </div>
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">Continent: ${row.continent}</h6>
                    <div class="mt-3">
                        <h6 class="mb-2">Planned Activities:</h6>
                        <ul class="activity-list">
                            <core:if test="${not empty row.activity1}">
                                <li><i class="fas fa-check-circle text-success me-2"></i>${row.activity1}</li>
                            </core:if>
                            <core:if test="${not empty row.activity2}">
                                <li><i class="fas fa-check-circle text-success me-2"></i>${row.activity2}</li>
                            </core:if>
                            <core:if test="${not empty row.activity3}">
                                <li><i class="fas fa-check-circle text-success me-2"></i>${row.activity3}</li>
                            </core:if>
                            <core:if test="${not empty row.activity4}">
                                <li><i class="fas fa-check-circle text-success me-2"></i>${row.activity4}</li>
                            </core:if>
                        </ul>
                    </div>
                    <div class="mt-3 d-flex gap-2">
                       
                        <form action="UpdateForm.jsp" method="POST" class="flex-grow-1">
                            <input type="hidden" name="continent" value="${row.continent}">
                            <input type="hidden" name="country" value="${row.country}">
                            <input type="hidden" name="city" value="${row.city}">
                            <input type="hidden" name="activity1" value="${row.activity1}">
                            <input type="hidden" name="activity2" value="${row.activity2}">
                            <input type="hidden" name="activity3" value="${row.activity3}">
                            <input type="hidden" name="activity4" value="${row.activity4}">
                            <button type="submit" class="btn btn-warning w-100">
                                <i class="fas fa-edit me-2 "></i>Update
                            </button>
                        </form>
                        
                       
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                                data-bs-target="#deleteModal${row.city.replace(' ', '')}">
                            <i class="fas fa-trash-alt me-2"></i>Delete
                        </button>
                        
                       
                        <div class="modal fade" id="deleteModal${row.city.replace(' ', '')}" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Confirm Deletion</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to delete your itinerary for ${row.city}, ${row.country}?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <form action="DeleteLogic.jsp" method="POST">
                                            <input type="hidden" name="username" value="${sessionScope.s_log_u}">
                                            <input type="hidden" name="continent" value="${row.continent}">
                                            <input type="hidden" name="country" value="${row.country}">
                                            <input type="hidden" name="city" value="${row.city}">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </core:forEach>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/1610670336.js" crossorigin="anonymous"></script>
</body>
</html>