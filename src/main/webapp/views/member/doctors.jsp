<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<section class="w-100">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		<div class="row tool-bar mt-4">
		    <form class="row tool-bar" action="">
		        <div>
		            <label for="">Search</label>
		            <input type="text" name="k" value="${param.k}" placeholder="keyword">
		        </div>
		        
		        <div class="ml-2">
		        	<label for="">Status</label>
                	<select name="s">
                		<option value="all" selected>All</option>
                		<option value="active" ${param.s eq 'active' ? 'selected' : ''}>
                			Active
                		</option>
                		<option value="closed" ${param.s eq 'closed' ? 'selected' : ''}>
                			Closed
                		</option>
                	</select>
                </div>
                
		        <button class="btn btn-primary ml-2" type="submit">
		        	<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
		        </button>
		    </form>
		    
		    <c:url var="editDoctorUrl" value="/admin/doctors/edit"></c:url>
		    <a class="btn btn-primary ml-2" href="${editDoctorUrl}">
		    	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
		    </a>
		</div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Specialist</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Status</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="doctor" items="${requestScope.doctors}" varStatus="doctorCount">
            		<tr>
	                    <td class="text-right content-box">${doctorCount.count}</td>
	                    <td>${doctor.getName()}</td>
	                    <td>${doctor.getSpecialistName()}</td>
	                    <td>${doctor.getPhone()}</td>
	                    <td>${doctor.getEmail()}</td>
	                    <td class="text-center content-box">
                   			 <c:choose>
                   			 	<c:when test="${doctor.getGender() == 'MALE'}">
		                    			<i class="fa-solid fa-mars text-green"></i>
		                    	</c:when>
		                    	<c:when test="${doctor.getGender() == 'FEMALE'}">
		                    			<i class="fa-solid fa-venus text-pink"></i>
		                    	</c:when>
		                    	<c:when test="${doctor.getGender() == 'OTHER'}">
		                    			<i class="fa-solid fa-genderless text-blue"></i>
		                    	</c:when>
                   			 </c:choose>
                   		</td>
	                    
	                    <td class="text-center content-box">
	                    	<c:choose>
		                    	<c:when test="${doctor.getStatus() eq 'ACTIVE'}">
				                    	<span class="status status-green">Active</span>
		                    	</c:when>
		                    		
		                    	<c:when test="${doctor.getStatus() eq 'CLOSED'}">
				                    	<span class="status status-red">Closed</span>
		                    	</c:when>
		                    </c:choose>
	                    </td>
	                    
	                    <td>${doctor.getCity()}</td>
	                    
	                    <td>${doctor.getState()}</td>
	                    
	                    <td class="text-center content-box">
	                    	<c:url var="viewDoctorProfileUrl" value="/member/doctors/profile">
	                    		<c:param name="id">${doctor.getId()}</c:param>
	                    	</c:url>
	                    	<a href="${viewDoctorProfileUrl}" class="text-blue">
	                    		<i class="fa-solid fa-user action-btn"></i>
	                    	</a>
	                    	
	                    	<c:url var="editDoctorProfileUrl" value="/admin/doctors/edit">
	                    		<c:param name="id">${doctor.getId()}</c:param>
	                    	</c:url>
	                    	<a href="${editDoctorProfileUrl}" class="text-green">
	                    		<i class="fa-solid fa-pen-to-square action-btn ml-1"></i>
	                    	</a>
	                    	
	                    	<c:url var="deleteDoctorUrl" value="/admin/doctors/delete">
	                    		<c:param name="id">${doctor.getId()}</c:param>
	                    	</c:url>
	                    	<a href="${deleteDoctorUrl}" class="text-red" onclick="return confirm('Are you sure to delete this doctor?')">
	                    		<i class="fa-solid fa-trash-can action-btn ml-1"></i>
	                    	</a>
	                    	
	                    </td>
	                </tr>
            	</c:forEach>
	                
            </tbody>
        </table>
    </div>
</section>