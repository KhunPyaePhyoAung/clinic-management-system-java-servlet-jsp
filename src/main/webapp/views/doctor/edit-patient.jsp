<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-50">
	<div class="container bg-white">
        <h2>${viewTitle}</h2>
        
        <c:url var="saveUrl" value="/doctor/patients/save" />
        <form class="mt-2" action="${saveUrl}" method="post">
        
        	<div class="text-center">
        		<div class="profile-picture-select">
        			<c:url var="defaultProfilePictureUrl" value="/assets/images/man.png" />
        			<img class="profile-img dropdown-btn" src="${defaultProfilePictureUrl}" alt="Profile Picture" onclick="toggleShowProfilePictureMenu()" />
        			<div id="profile-picture-menu" class="profile-picture-menu dropdown-content">
        				<div class="menu-item">Select Picture</div>
        				<div class="menu-item">Remove Picture</div>
        			</div>
        		</div>
        	</div>
        	
        	<div class="row mt-2">
        		<div class="col form-group w-50">
        			<label for="">Full Name</label>
        			<input type="text" />
        		</div>
        		
        		<div class="row form-group w-50">
	        		<div class="col form-group w-50">
	        			<label for="">Gender</label>
	        			<select name="" id="">
	        				<option value="" selected disables hidden>-- select gender --</option>
	        				<option value="">Male</option>
	        				<option value="">Female</option>
	        				<option value="">Other</option>
	        			</select>
	        		</div>
	        		
	        		<div class="col form-group w-50">
	        			<label for="">Date Of Birth</label>
	        			<input type="date" />
	        		</div>
	        	</div>
        		
        		
        	</div>
        	
        	<div class="row mt-2">
        	
        		<div class="col form-group w-50">
        			<label for="">Email</label>
        			<input type="email" />
        		</div>
        			
        		<div class="row form-group w-50">
        			<div class="col form-group w-50">
	        			<label for="">Account Status</label>
	        			<select name="" id="">
	        				<option value="">Active</option>
	        				<option value="">Closed</option>
	        			</select>
	        		</div>
	        		
	        		<div class="col form-group w-50">
	        			<label for="">Blood Group</label>
	        			<select name="">
	        				<option value="">Unknown</option>
	        				<option value="">A+</option>
	        				<option value="">A-</option>
	        				<option value="">B+</option>
	        				<option value="">B-</option>
	        				<option value="">AB+</option>
	        				<option value="">AB-</option>
	        				<option value="">O+</option>
	        				<option value="">O-</option>
	        			</select>
	        		</div>
        		</div>
        	</div>
        	
        	<div class="row mt-2">
        		
        		<div class="col form-group w-50">
        			<label for="">Phone</label>
        			<input type="tel" />
        		</div>
        		
        		<div class="col form-group w-50">
        		</div>
        		
        	</div>
        	
        	<div class="mt-2">
        		<h3>Address</h3>
        		<div class="row mt-1">
	        		<div class="col form-group w-50">
	        			<label for="">Street</label>
	        			<input type="text" />
	        		</div>
	        		
	        		<div class="col form-group w-50">
        				<label for="">City</label>
        				<input type="text" />
        			</div>
        		</div>
        		
        		
        		<div class="row mt-2">
        			
        			<div class="col form-group w-50">
        				<label for="">State</label>
        				<input type="text" />
        			</div>
        			
        			<div class="col form-group w-50">
        				<label for="">Country</label>
        				<input type="text" />
        			</div>
        		</div>
        	</div>
        	
        	<div class="d-flex justify-content-end mt-2">
        		<c:url var="cancelUrl" value="/doctor/patients/search" />
        		<a href="${cancelUrl}" class="btn btn-gray mr-1">Cancel</a>
        		
        		<button class="btn btn-primary" type="submit">Save</button>
        	</div>
        </form>
    </div> 
</section>
