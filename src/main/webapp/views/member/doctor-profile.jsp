<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-50">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		
		<div class="profile d-flex justify-content-even mt-4">
			
			<div>
				<div class="font-size-l font-weight-xxl mt-1">${doctor.getName()}</div>
				
				<hr class="mt-2" />
				
				<div class="profile-details mt-2 w-80">
					<i class="fa-solid fa-briefcase-medical"></i>
					<span>${doctor.getSpecialistName()}</span>
					
					<i class="fa-solid fa-venus-mars"></i>
					<c:choose>
              			 <c:when test="${doctor.getGender() == 'MALE'}">
                 			<i class="fa-solid fa-mars"></i>
	                   	</c:when>
	                   	<c:when test="${doctor.getGender() == 'FEMALE'}">
	                   			<i class="fa-solid fa-venus"></i>
	                   	</c:when>
	                   	<c:when test="${doctor.getGender() == 'OTHER'}">
	                   			<i class="fa-solid fa-genderless"></i>
	                   	</c:when>
	                </c:choose>
					
					<i class="fa-solid fa-phone"></i>
					<span>${doctor.getPhone()}</span>
					
					<i class="fa-solid fa-envelope"></i>
					<span>${doctor.getEmail()}</span>
					
					<i class="fa-solid fa-cake-candles"></i>
					<span>${doctor.getDateOfBirth()}</span>
					
					<i class="fa-solid fa-location-dot"></i>
					<span>
						<c:if test="${not empty doctor.getStreet()}">
							doctor.getStreet(), 
						</c:if>
						<c:if test="${not empty doctor.getCity()}">
							${doctor.getCity()}
						</c:if>
						<c:if test="${not empty doctor.getState()}">
							, ${doctor.getState()}
						</c:if>
						<c:if test="${not empty doctor.getCountry()}">
							, ${doctor.getCountry()}
						</c:if>
					</span>
				</div>
			</div>
			
			<div class="w-30 text-center d-flex col justify-content-center ml-4">
				<c:url var="editUrl" value="/admin/doctors/edit">
					<c:param name="id">${doctor.getId()}</c:param>
				</c:url>
				<a class="btn btn-primary mt-2" href="${editUrl}">Edit</a>
				
				<c:url var="changePasswordUrl" value="/member/change-password">
					<c:param name="id">${doctor.getId()}</c:param>
				</c:url>
				<a class="btn btn-primary mt-1" href="${changePasswordUrl}">Change Password</a>
				
				<c:url var="goBackUrl" value="/member/doctors/search"></c:url>
				<a class="btn btn-gray mt-1" href="${goBackUrl}">Go Back</a>
				
			</div>
			
		</div>
	</div>
</section>