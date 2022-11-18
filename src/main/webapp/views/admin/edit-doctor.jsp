<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-50">
	<div class="container bg-white">
        <h2>${viewTitle}</h2>
        
        <c:url var="saveUrl" value="/admin/doctors/save" />
        <form class="mt-2" action="${saveUrl}" method="post">
        	<input type="hidden" name="id" value="${doctor.getId()}" />
        	<div class="row mt-2">
        		<div class="col form-group w-50">
        			<label for="">Full Name</label>
        			<input type="text" name="name" value="${doctor.getName()}" required />
        		</div>
        		
        		<div class="col form-group w-50">
        			<label>Specialist</label>
        			<select name="specialist" required>
        				
        				<c:forEach var="specialist" items="${requestScope.specialists}">
        					<option value="${specialist.getId()}" ${doctor.getSpecialistId() eq specialist.getId() ? 'selected' : ''}>
        						${specialist.getName()}
        					</option>
        				</c:forEach>
        			</select>
        		</div>
        		
        		
        	</div>
        	
        	<div class="row mt-2">
        	
        		<div class="col form-group w-50">
        			<label>Email</label>
        			<input type="email" name="email" value="${doctor.getEmail()}" />
        		</div>
        			
        		<div class="row form-group w-50">
	        		<div class="col form-group w-50">
	        			<label>Gender</label>
	        			<select name="gender" required>
	        				<option value="MALE" ${doctor.getGender() eq 'MALE' ? 'selected' : ''}>Male</option>
	        				<option value="FEMALE" ${doctor.getGender() eq 'FEMALE' ? 'selected' : ''}>Female</option>
	        				<option value="OTHER" ${doctor.getGender() eq 'OTHER' ? 'selected' : ''}>Other</option>
	        			</select>
	        		</div>
	        		
	        		<div class="col form-group w-50">
	        			<label>Date Of Birth</label>
	        			<input type="date" name="dateOfBirth" value="${doctor.getDateOfBirth()}" required />
	        		</div>
	        	</div>
        		
        		
        	</div>
        	
        	<div class="row mt-2">
        		
        		<div class="col form-group w-50">
        			<label>Phone</label>
        			<input type="tel" name="phone" value="${doctor.getPhone()}" required />
        		</div>
        		
        		<div class="row form-group w-50">
        			<div class="col form-group w-50">
	        			<label>Account Status</label>
	        			<select name="status" required>
	        				<option value="ACTIVE" ${doctor.getStatus() eq 'ACTIVE' ? 'selected' : ''}>Active</option>
	        				<option value="CLOSED" ${doctor.getStatus() eq 'CLOSED' ? 'selected' : ''}>Closed</option>
	        			</select>
	        		</div>
	        		
	        		<div class="col form-group w-50">
	        		</div>
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
        				<input type="text" name="city" value="${doctor.getCity()}" required />
        			</div>
        		</div>
        		
        		
        		<div class="row mt-2">
        			
        			<div class="col form-group w-50">
        				<label>State</label>
        				<input type="text" name="state" value="${doctor.getState()}" required />
        			</div>
        			
        			<div class="col form-group w-50">
        				<label>Country</label>
        				<input type="text" name="country" value="${doctor.getCountry()}"/>
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
