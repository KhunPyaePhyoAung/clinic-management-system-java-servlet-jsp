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
                    <td>U Tun Naing</td>
                    <td>Internal medicine</td>
                    <td>09262683726</td>
                    <td>tunnaing@gmail.com</td>
                    <td class="text-center content-box">Male</td>
                    <td class="text-center content-box">34</td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewDoctorProfileUrl" value="/member/doctors/profile"></c:url>
                    	<a href="${viewDoctorProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editDoctorProfileUrl" value="/admin/doctors/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editDoctorProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deleteDoctorUrl" value="/admin/doctors/delete"></c:url>
                    	<a href="${deleteDoctorUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">2</td>
                    <td>Daw San Thidar</td>
                    <td>Internal medicine</td>
                    <td>09456789362</td>
                    <td>santhidar@gmail.com</td>
                    <td class="text-center content-box">Female</td>
                    <td class="text-center content-box">44</td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewDoctorProfileUrl" value="/member/doctors/profile"></c:url>
                    	<a href="${viewDoctorProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editDoctorProfileUrl" value="/admin/doctors/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editDoctorProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deleteDoctorUrl" value="/admin/doctors/delete"></c:url>
                    	<a href="${deleteDoctorUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">3</td>
                    <td>U Zay Ya Thu Ta</td>
                    <td>Allergy and immunology</td>
                    <td>09765687651</td>
                    <td>zayyathuta@gmail.com</td>
                    <td class="text-center content-box">Male</td>
                    <td class="text-center content-box">46</td>
                    <td class="text-center content-box">
                    	<span class="status status-red">Closed</span>
                    </td>
                    <td>Toungoo</td>
                    <td>Bago</td>
                    <td class="text-center content-box">
                    	<c:url var="viewDoctorProfileUrl" value="/member/doctors/profile"></c:url>
                    	<a href="${viewDoctorProfileUrl}"><i class="fa-solid fa-user action-btn"></i></a>
                    	
                    	<c:url var="editDoctorProfileUrl" value="/admin/doctors/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editDoctorProfileUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deleteDoctorUrl" value="/admin/doctors/delete"></c:url>
                    	<a href="${deleteDoctorUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>