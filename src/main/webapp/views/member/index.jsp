<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    
    <c:url var="styleLink" value="/assets/css/style.css" />
    <link rel="stylesheet" href="${styleLink}">
    
    <c:url var="jsLink" value="/assets/js/application.js" />
    <script src="${jsLink}" defer></script>
    
    <c:url var="fontAwesomeLink" value="/assets/font-6/css/all.css" />
    <link rel="stylesheet" href="${fontAwesomeLink}">
    
    <c:url var="specialistsJsLink" value="/assets/js/specialists.js" />
	<script src="${specialistsJsLink}" defer></script>
</head>
<body>
    <header>
        <h1 class="logo">
            Clinic Management
        </h1>
        <nav>
            <ul class="menu-list">
                <li class="menu-item">
                	<c:url var="homeUrl" value="/member/home" />
                    <a class="menu ${activeMenu eq 'home' ? 'active' : ''}" href="${homeUrl}">
                    	<i class="fa-solid fa-house nav-icon"></i>
                    	Home
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="prescriptionsUrl" value="/doctor/prescriptions/search" />
                    <a class="menu ${activeMenu eq 'prescriptions' ? 'active' : ''}" href="${prescriptionsUrl}">
                    	<i class="fa-solid fa-book-medical nav-icon"></i>
                    	Prescriptions
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="doctorsUrl" value="/member/doctors/search" />
                    <a class="menu ${activeMenu eq 'doctors' ? 'active' : ''}" href="${doctorsUrl}">
                    	<i class="fa-solid fa-user-doctor nav-icon"></i>
                    	Doctors
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="specialistsUrl" value="/admin/specialists/search" />
                    <a class="menu ${activeMenu eq 'specialists' ? 'active' : ''}" href="${specialistsUrl}">
                    	<i class="fa-solid fa-briefcase-medical nav-icon"></i>
                    	Specialists
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="reportsUrl" value="/employee/reports/search" />
                    <a class="menu ${activeMenu eq 'reports' ? 'active' : ''}" href="${reportsUrl}">
                    	<i class="fa-solid fa-chart-line nav-icon"></i>
                    	Reports
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="patientsUrl" value="/doctor/patients/search" />
                    <a class="menu ${activeMenu eq 'patients' ? 'active' : ''}" href="${patientsUrl}">
                    	<i class="fa-solid fa-bed nav-icon"></i>
                    	Patients
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="medicinesUrl" value="/doctor/medicines/search" />
                    <a class="menu ${activeMenu eq 'medicines' ? 'active' : ''}" href="${medicinesUrl}">
                    	<i class="fa-solid fa-capsules nav-icon"></i>
                    	Medicines
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="diseasesUrl" value="/doctor/diseases/search" />
                    <a class="menu ${activeMenu eq 'diseases' ? 'active' : ''}" href="${diseasesUrl}">
                    	<i class="fa-solid fa-virus-covid nav-icon"></i>
                    	Diseases
                    </a>
                </li>
                
                <li class="menu-item">
                	<c:url var="logoutUrl" value="/logout" />
                    <a class="menu" href="${logoutUrl}">
                    	<i class="fa-solid fa-right-from-bracket nav-icon"></i>
                    	Logout
                    </a>
                </li>

                <li class="menu-item">
                	<c:url var="profileUrl" value="/member/profile" />
                    <a class="menu ${activeMenu eq 'user' ? 'active' : ''}" href="${profileUrl}">
                    	<c:url var="userImageUrl" value="/assets/images/man.png"></c:url>
                        <img class="profile-img" src="${userImageUrl}" alt="man" title="User">
                    </a>
                </li>
            </ul>
        </nav>
    </header>
    <main class="main-content">
        
        <jsp:include page="${content}"></jsp:include>
        
    </main>
</body>
</html>