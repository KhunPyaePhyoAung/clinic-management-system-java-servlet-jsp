<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-40">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		
		<div class="profile d-flex justify-content-even mt-4">
			<div class="text-center d-flex col justify-content-center">
				<c:url var="profilePictureUrl" value="/assets/images/man.png" />
				<img class="profile-img" src="${profilePictureUrl}" alt="Profile Picture" />
				
				<c:url var="editUrl" value="/doctor/patients/edit"></c:url>
				<a class="btn btn-primary mt-2" href="${editUrl}">Edit</a>
				
				<c:url var="changePasswordUrl" value="/member/change-password"></c:url>
				<a class="btn btn-primary mt-1" href="${changePasswordUrl}">Change Password</a>
				
				<c:url var="goBackUrl" value="/doctor/patients/search"></c:url>
				<a class="btn btn-gray mt-1" href="${goBackUrl}">Go Back</a>
				
			</div>
			
			<div class="ml-4">
				<div class="font-size-xl font-weight-xxl mt-1">Mg Thein Than</div>
				
				<hr class="mt-2" />
				
				<div class="profile-details mt-2 w-80">
				<span><i class="fa-solid fa-venus-mars"></i></span>
				<span>Male</span>
				<span><i class="fa-solid fa-droplet"></i></span>
				<span>A+</span>
				<span><i class="fa-solid fa-phone"></i></span>
				<span>09456798431</span>
				<span><i class="fa-solid fa-envelope"></i></span>
				<span>theinthan@gmail.com</span>
				<span><i class="fa-solid fa-cake-candles"></i></span>
				<span>28-9-1988</span>
				<span><i class="fa-solid fa-location-dot"></i></span>
				<span>Toungoo, Bago, Myanmar</span>
			</div>
			</div>
			
			
			
		</div>
	</div>
</section>