<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <c:url var="styleLink" value="/assets/css/style.css"></c:url>
    <link rel="stylesheet" href="${styleLink}">
    
    <c:url var="fontAwesomeLink" value="/assets/font-6/css/all.css"></c:url>
    <link rel="stylesheet" href="${fontAwesomeLink}">
</head>
<body class="bg-primary">
    
    <div class="login-form">
        <h2 class="title text-center">
            <i class="fa-solid fa-circle-user"></i>
        </h2>
        <form action="" method="post">
            <div class="form-group">
                <label for="">Username</label>
                <input type="text">
            </div>
    
            <div class="form-group">
                <label for="">Password</label>
                <input type="password">
            </div>
    
            <div>
                <button  class="btn btn-primary w-100 mt-3" type="submit">Login</button>
            </div>
        </form>
    </div>

</body>
</html>