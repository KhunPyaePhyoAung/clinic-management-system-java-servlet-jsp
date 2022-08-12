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
				
				<c:url var="editUrl" value="/admin/doctors/edit">
					<c:param name="id">1</c:param>
				</c:url>
				<a class="btn btn-primary mt-2" href="${editUrl}">Edit</a>
				
				<c:url var="changePasswordUrl" value="/member/change-password"></c:url>
				<a class="btn btn-primary mt-1" href="${changePasswordUrl}">Change Password</a>
				
				<c:url var="goBackUrl" value="/member/doctors/search"></c:url>
				<a class="btn btn-gray mt-1" href="${goBackUrl}">Go Back</a>
				
			</div>
			
			<div class="ml-4">
				<div class="font-size-xl font-weight-xxl mt-1">U Tun Naing</div>
				
				
				<hr class="mt-2" />
				
				<div class="profile-details mt-2 w-80">
					<i class="fa-solid fa-briefcase-medical"></i>
					<span>Internal medicine</span>
					
					<i class="fa-solid fa-venus-mars"></i>
					<span>Male</span>
					
					<i class="fa-solid fa-phone"></i>
					<span>09262683726</span>
					
					<i class="fa-solid fa-envelope"></i>
					<span>tunnaing@gmail.com</span>
					
					<i class="fa-solid fa-cake-candles"></i>
					<span>28-9-1988</span>
					
					<i class="fa-solid fa-location-dot"></i>
					<span>Toungoo, Bago, Myanmar</span>
				</div>
			</div>
			
			
			
		</div>
	</div>
</section>