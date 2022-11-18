<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="/WEB-INF/custom-tag.tld" %>

<section class="w-50">
	<div class="container bg-white">
        <h2>${viewTitle}</h2>
        
       	<c:if test="${not empty doctorFormException}">
       		<div id="form-error-message" class="mt-3">
       			<app:errorMessage message="${doctorFormException.getMessage()}"/>
       		</div>
       	</c:if>
        
        <c:url var="saveUrl" value="/admin/doctors/save" />
        <form class="mt-2" action="${saveUrl}" method="post">
        	
        	<div class="row mt-2">
        		<div class="col form-group w-50">
        			<label>Full Name</label>
        			<input type="text" name="name" value="${doctor.getName()}" />
        		</div>
        		
        		<div class="col form-group w-50">
        			<label>Username</label>
        			<input type="text" name="username" value="${doctor.getUsername()}"  />
        		</div>
        		
        	</div>
        	
        	<div class="row mt-2">
        	
        		<div class="col form-group w-50">
        			<label>Email</label>
        			<input type="email" name="email" value="${doctor.getEmail()}" />
        		</div>
        			
        		<div class="col form-group w-50">
        			<label>Password</label>
        			<input type="password" name="password" value="${doctor.getPassword()}" />
        		</div>
        		
        	</div>
        	
        	<div class="row mt-2">
        		
        		<div class="col form-group w-50">
        			<label>Phone</label>
        			<input type="tel" name="phone" value="${doctor.getPhone()}" />
        		</div>
        		
        		<div class="col form-group w-50">
        			<label>Confirm Password</label>
        			<input type="password" name="comfirmPassword" />
        		</div>
        		
        	</div>
        	
        	<div class="row mt-2">
        		
        		<div class="row form-group w-50">
	        		<div class="col form-group w-50">
	        			<label>Gender</label>
	        			<select name="gender">
	        				<option value="MALE" ${doctor.getGender() eq 'MALE' ? 'selected' : ''}>Male</option>
	        				<option value="FEMALE" ${doctor.getGender() eq 'FEMALE' ? 'selected' : ''}>Female</option>
	        				<option value="OTHER" ${doctor.getGender() eq 'OTHER' ? 'selected' : ''}>Other</option>
	        			</select>
	        		</div>
	        		
	        		<div class="col form-group w-50">
	        			<label>Date Of Birth</label>
	        			<input type="date" name="dateOfBirth" value="${doctor.getDateOfBirth()}" />
	        		</div>
	        	</div>
	        	
        		<div class="col form-group w-50">
        			<label>Specialist</label>
        			<select name="specialist">
        				<c:forEach var="specialist" items="${requestScope.specialists}">
        					<option value="${specialist.getId()}" ${doctor.getSpecialistId() eq specialist.getId() ? 'selected' : ''}>
        						${specialist.getName()}
        					</option>
        				</c:forEach>
        			</select>
        		</div>
        	</div>
        	
        	<div class="mt-2">
        		<h3>Address</h3>
        		<div class="row mt-1">
	        		<div class="col form-group w-50">
	        			<label>Street</label>
	        			<input type="text" name="street" value="${doctor.getStreet()}" />
	        		</div>
	        		
	        		<div class="col form-group w-50">
        				<label>City</label>
        				<input type="text" name="city" value="${doctor.getCity()}" />
        			</div>
        		</div>
        		
        		
        		<div class="row mt-2">
        			
        			<div class="col form-group w-50">
        				<label>State</label>
        				<input type="text" name="state" value="${doctor.getState()}" />
        			</div>
        			
        			<div class="col form-group w-50">
        				<label>Country</label>
        				<input type="text" name="country" value="${doctor.getCountry()}" />
        			</div>
        		</div>
        	</div>
        	
        	<div class="d-flex justify-content-end mt-2">
        		<c:url var="cancelUrl" value="/member/doctors/search" />
        		<a href="${cancelUrl}" class="btn btn-gray mr-1">Cancel</a>
        		
        		<button class="btn btn-primary" type="submit">Save</button>
        	</div>
        </form>
    </div> 
</section>
