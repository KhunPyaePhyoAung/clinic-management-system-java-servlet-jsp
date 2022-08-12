<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-100">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		<div class="row tool-bar mt-4">
		
			<c:url var="searchUrl" value="/doctor/patients/search" />
		    <form class="row tool-bar" action="${searchUrl}" method="get">
		        <div>
		            <label for="">Search</label>
		            <input type="text" name="k" value="${param.k}" placeholder="keyword">
		        </div>
		        
		        <div class="ml-2">
		        	<label for="">Status</label>
                	<select name="s">
                		<option value="all" selected>All</option>
                		<option value="active"   ${param.s eq 'active' ? 'selected' : ''}>
                			Active
                		</option>
                		<option value="closed"  ${param.s eq 'closed' ? 'selected' : ''}>
                			Closed
                		</option>
                	</select>
                </div>
		        
		    	<button class="btn btn-primary ml-2" type="submit">
		    		<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
		    	</button>
		    </form>
		    
		    <c:url var="editPatientUrl" value="/doctor/patients/edit" />
		    <a href="${editPatientUrl}" class="btn btn-primary ml-2">
		    	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
		    </a>
		</div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th class="content-box">Blood Group</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Status</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-right content-box">1</td>
                    <td>Mg Thein Than</td>
                    <td>09456798431</td>
                    <td>theinthan@gmail.com</td>
                    <td class="text-center content-box">A+</td>
                    <td class="text-center content-box">Male</td>
                    <td class="text-center content-box">34</td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewPatientProfileUrl" value="/doctor/patients/profile"></c:url>
                    	<a href="${viewPatientProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editPatientProfileUrl" value="/doctor/patients/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPatientProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePatientUrl" value="/doctor/patients/delete"></c:url>
                    	<a href="${deletePatientUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">2</td>
                    <td>Ma Myo Thidar</td>
                    <td>09786543789</td>
                    <td>myothidar@gmail.com</td>
                    <td class="text-center content-box">O-</td>
                    <td class="text-center content-box">Female</td>
                    <td class="text-center content-box">44</td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewPatientProfileUrl" value="/doctor/patients/profile"></c:url>
                    	<a href="${viewPatientProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editPatientProfileUrl" value="/doctor/patients/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPatientProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePatientUrl" value="/doctor/patients/delete"></c:url>
                    	<a href="${deletePatientUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">3</td>
                    <td>U Win Tin</td>
                    <td>09976541234</td>
                    <td>wintin@gmail.com</td>
                    <td class="text-center content-box">B+</td>
                    <td class="text-center content-box">Other</td>
                    <td class="text-center content-box">46</td>
                    <td class="text-center content-box">
                    	<span class="status status-red">Closed</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewPatientProfileUrl" value="/doctor/patients/profile"></c:url>
                    	<a href="${viewPatientProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editPatientProfileUrl" value="/doctor/patients/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPatientProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePatientUrl" value="/doctor/patients/delete"></c:url>
                    	<a href="${deletePatientUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>