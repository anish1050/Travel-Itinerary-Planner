<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Travel Destinations</title>
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="FinalUserLoginProfile.css">
</head>
<body>

    <%
    String log_username = (String) session.getAttribute("s_log_u");
    %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand logo-container" href="FinalUserLoginProfile.jsp">
                <video id="logovideo" src="Yellow and Green Island Flat Illustrative Hotels and Travel Service Animated Logo.mp4" autoplay muted></video>
            </a>
            <span class="brand-text">WanderCraft</span>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                 <a id="myprofile" href="InitialUserLoginProfile.jsp"> <button id="myprofilebtn"> My Profile</button></a>
            </div>
        </div>
    </nav>
    
    <div class="container mt-4">
        <h2 class="mb-4"><%= log_username %>, Which Continent would you like to visit?</h2>

        <div class="row row-cols-1 row-cols-md-3 g-4">
           
            <div class="col">
                <div id="card1" class="card h-100">
                    <div class="card-img-container">
                        <img id="firstcardimg" src="Asia.jpg" class="card-img-top" alt="Asia">
                    </div>   
                    <div class="card-body">
                        <h4 class="card-title">Explore Asia</h4>
                        <p class="card-text">Asia, the largest and most populous continent, is renowned for its cultural diversity, historical landmarks, and economic powerhouses.</p>
                    </div>
                    <div class="d-grid gap-2 col-8 mx-auto mb-3">
                        <a href="Asia.html"><button class="btn bg-warning-subtle border-dark" type="button">Explore now</button></a>
                    </div>
                </div>
            </div>
            
            
            <div class="col">
                <div id="card2" class="card h-100">
                    <div class="card-img-container">
                        <img id="secondcardimg" src="Europe.jpg" class="card-img-top" alt="Europe">
                    </div> 
                    <div class="card-body">
                        <h4 class="card-title">Explore Europe</h4>
                        <p class="card-text">Europe is a culturally rich continent known for its historical landmarks, diverse nations, and economic and political influence globally.</p>
                    </div>
                    <div class="d-grid gap-2 col-8 mx-auto mb-3">
                        <a href="Europe.html"><button class="btn bg-warning-subtle border-dark" type="button">Explore now</button></a>
                    </div>
                </div>
            </div>
            
            
            <!-- <div class="col">
                <div id="card3" class="card h-100">
                    <div class="card-img-container">
                        <img id="thirdcardimg" src="/api/placeholder/400/300" class="card-img-top" alt="Placeholder">
                    </div>     
                    <div class="card-body">
                        <h4 class="card-title">Card Title</h4>
                        <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
                    </div>
                    <div class="d-grid gap-2 col-8 mx-auto mb-3">
                        <a><button class="btn bs-light-border-subtle" type="button">Explore now</button></a>
                    </div>
                </div>
            </div>
            
            
            <div class="col">
                <div id="card4" class="card h-100">
                    <div class="card-img-container">
                        <img id="fourthcardimg" src="/api/placeholder/400/300" class="card-img-top" alt="Placeholder">
                    </div> 
                    <div class="card-body">
                        <h4 class="card-title">Card Title</h4>
                        <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
                    </div>
                    <div class="d-grid gap-2 col-8 mx-auto mb-3">
                        <button class="btn bs-light-border-subtle" type="button">Explore now</button>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
    
    <script>
    // Store login status when page loads
    window.onload = function () {
        if (!localStorage.getItem("isLoggedIn")) {
            localStorage.setItem("isLoggedIn", "true");
        }

        // Push state to enable back-button detection
        history.pushState(null, null, location.href);
    };

    // Handle back-button using popstate
    window.addEventListener("popstate", function (event) {
        const confirmLogout = confirm("Do you want to log out?");
        if (confirmLogout) {
            localStorage.removeItem("isLoggedIn");
            // Optional: Invalidate session on server side
            window.location.href = "UserLogout.jsp"; // Create this JSP to handle logout
        } else {
            // Re-push the current state so that user stays on the page
            history.pushState(null, null, location.href);
        }
    });
</script>
    

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
