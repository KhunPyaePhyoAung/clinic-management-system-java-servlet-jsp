<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-100">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		<div class="row tool-bar mt-4">
			
			<c:url var="searchUrl" value="/doctor/prescriptions/search"></c:url>
		    <form class="row tool-bar" action="${searchUrl}" method="get">
		        <div>
		            <label for="">Patient</label>
		            <select name="p">
		            	<option value="">All</option>
		            	<option value="1" ${param.p eq '1' ? 'selected' : ''}>Mg Thein Than</option>
		            	<option value="2" ${param.p eq '2' ? 'selected' : ''}>Ma Myo Thidar</option>
		            	<option value="3" ${param.p eq '3' ? 'selected' : ''}>U Win Tin</option>
		            </select>
		        </div>
		        
		        <div class="ml-2">
		            <label for="">Doctor</label>
		            <select name="d">
		            	<option value="">All</option>
		            	<option value="1" ${param.d eq '1' ? 'selected' : ''}>U Tun Naing</option>
		            	<option value="2" ${param.d eq '2' ? 'selected' : ''}>Daw San Thidar</option>
		            	<option value="3" ${param.d eq '3' ? 'selected' : ''}>U Zay Ya Thu Ta</option>
		            </select>
		        </div>
		        
		        <div class="ml-2">
		        	<label for="">From</label>
		        	<input type="date" name="f" value="${param.f}" />
		        </div>
		        
		        <div class="ml-2">
		        	<label for="">To</label>
		        	<input type="date" name="t" value="${param.t}" />
		        </div>
		        
		        <button class="btn btn-primary ml-2" type="submit">
		        	<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
		        </button>
		    </form>
		    
		    <c:url var="addNewPrescriptionUrl" value="/doctor/prescriptions/edit" />
		    <a href="${addNewPrescriptionUrl}" class="btn btn-primary ml-2">
		    	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
		    </a>
		    
		</div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Disease</th>
                    <th>Visite Date</th>
                    <th class="content-box">Visite Time</th>
                    <th>Medicine</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-right content-box">1</td>
                    <td>Mg Thein Than</td>
                    <td>U Tun Naing</td>
                    <td>Allergies</td>
                    <td class="text-center content-box">20-6-2-2022</td>
                    <td class="text-center content-box">04:00 PM</td>
                    <td class="text-center content-box">2</td>
                    <td class="content-box">
                    	<c:url var="viewPrescriptionDetailsUrl" value="/clinic/prescriptions/details"></c:url>
                    	<a href="${viewPrescriptionDetailsUrl}"><i class="fa-solid fa-book-medical action-btn"></i></a>
                    	
                    	<c:url var="editPrescriptionUrl" value="/doctor/prescriptions/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPrescriptionUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePrescriptionUrl" value="/doctor/prescriptions/delete"></c:url>
                    	<a href="${deletePrescriptionUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">2</td>
                    <td>Ma Myo Thidar</td>
                    <td>Daw San Thidar</td>
                    <td>Allergies</td>
                    <td class="text-cente content-boxr">20-6-2-2022</td>
                    <td class="text-center content-box">04:00 PM</td>
                    <td class="text-center content-box">2</td>
                    <td>
                    	<c:url var="viewPrescriptionDetailsUrl" value="/clinic/prescriptions/details"></c:url>
                    	<a href="${viewPrescriptionDetailsUrl}"><i class="fa-solid fa-book-medical action-btn"></i></a>
                    	
                    	<c:url var="editPrescriptionUrl" value="/doctor/prescriptions/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPrescriptionUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePrescriptionUrl" value="/doctor/prescriptions/delete"></c:url>
                    	<a href="${deletePrescriptionUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">3</td>
                    <td>U Win Tin</td>
                    <td>U Zay Ya Thu Ta</td>
                    <td>Allergies</td>
                    <td class="text-center content-box">20-6-2-2022</td>
                    <td class="text-center content-box">04:00 PM</td>
                    <td class="text-center content-box">2</td>
                    <td>
                    	<c:url var="viewPrescriptionDetailsUrl" value="/clinic/prescriptions/details"></c:url>
                    	<a href="${viewPrescriptionDetailsUrl}"><i class="fa-solid fa-book-medical action-btn"></i></a>
                    	
                    	<c:url var="editPrescriptionUrl" value="/doctor/prescriptions/edit">
                    		<c:param name="id">1</c:param>
                    	</c:url>
                    	<a href="${editPrescriptionUrl}"><i class="fa-solid fa-pen-to-square action-btn ml-1"></i></a>
                    	
                    	<c:url var="deletePrescriptionUrl" value="/doctor/prescriptions/delete"></c:url>
                    	<a href="${deletePrescriptionUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>
