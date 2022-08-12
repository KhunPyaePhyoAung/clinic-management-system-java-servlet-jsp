<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Specialists</title>
    
    <c:url var="styleLink" value="/assets/css/style.css"></c:url>
    <link rel="stylesheet" href="${styleLink}">
    
    <c:url var="fontAwesomeLink" value="/assets/font-6/css/all.css"></c:url>
    <link rel="stylesheet" href="${fontAwesomeLink}">
</head>
<body>
    <header>
        <h1 class="logo">
            Clinic Management</h1>
        <nav>
            <ul class="menu-list">
                <li class="menu-item">
                    <a class="menu active" href="">Home</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Visits</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Prescriptions</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Doctors</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Specialists</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Reports</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Patients</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Medicines</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Diseases</a>
                </li>
                <li class="menu-item">
                    <a class="menu" href="">Logout</a>
                </li>

                <li class="menu-item">
                    <a href="" class="menu">
                        <img class="profile-img" src="img/man.png" alt="man" title="User">
                    </a>
                </li>
            </ul>
        </nav>
    </header>
    <main class="main-content">
        
        <c:url var="specialListsPage" value="../admin/specialists.jsp"></c:url>
        <jsp:include page="${specialListsPage}"></jsp:include>
        
    </main>
</body>
</html>