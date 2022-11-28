<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="/WEB-INF/custom-tag.tld" %>

<c:url var="specialistsJsLink" value="/assets/js/specialists.js" />
<script src="${specialistsJsLink}" defer></script>
	
<section class="w-100">
	
	<div id="specialist-form-wrapper" class="container bg-white mb-3 ${empty doctorSpecialist ? 'd-none' : '' }">
        <h2 class="header">${empty doctorSpecialist.getId() ? 'Add New Specialist' : 'Edit Specialist'}</h2>
        
        <div id="form-error-message" class="mt-3">
        	<c:if test="${not empty doctorSpecialistFormException}">
        		<app:errorMessage message="${doctorSpecialistFormException.getMessage()}"/>
        	</c:if>
        </div>
        
        <c:url var="saveUrl" value="/admin/specialists/save" />
        <form id="specialist-form" class="col w-100 mt-2" action="${saveUrl}" method="post">
        	
        	<input type="hidden" name="id" value="${doctorSpecialist.getId()}" />
        
            <div class="row">
                <div class="col w-40 form-group">
                    <label for="">Specialist</label>
                    <input type="text" name="name" value="${doctorSpecialist.getName()}">
                </div>
                <div class="col w-60 form-group">
                    <label for="">Description</label>
                    <input type="text" name="description" value="${doctorSpecialist.getDescription()}">
                </div>
            </div>
            <div class="d-flex justify-content-end mt-2">
                <button class="btn btn-gray mr-1" type="button" onclick="hideDoctorSpecialistForm()">Cancel</button>
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
    </div>

    <div class="container bg-white">
        <h2>${viewTitle}</h2>
        
        <c:if test="${not empty alertMessage}">
	        <div class="alertMessage mt-4">
        		<app:alertMessage type="${alertMessage.getType()}" message="${alertMessage.getMessage()}"/>
	        </div>
        </c:if>
        
        <div class="row  tool-bar mt-4">
        
        	<c:url var="searchUrl" value="/admin/specialists/search" />
            <form class="row tool-bar" action="${searchUrl}" method="get">
                <div>
                    <label for="">Search</label>
                    <input type="text" name="k" value="${param.k}" placeholder="keyword">
                </div>
                
                <button class="btn btn-primary ml-2" type="submit">
                	<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
                </button>
            </form>
            
            <c:url var="addSpecialistUrl" value="/admin/specialists/edit"></c:url>
            <a href="${addSpecialistUrl}" class="btn btn-primary ml-2">
            	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
            </a>
        </div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th class="content-box">#</th>
                    <th>Specialist</th>
                    <th>Description</th>
                    <th class="content-box">Action</th>
                </tr>
            </thead>
            <tbody>
            
            	<c:forEach var="specialist" items="${requestScope.specialists}" varStatus="i">
            		<tr>
	                    <td class="text-right content-box">${i.count}</td>
	                    
	                    <td>${specialist.getName()}</td>
	                    
	                    <td>${specialist.getDescription()}</td>
	                    
	                    <td class="text-center content-box">
	                    
	                    	<c:set var="id" value="${specialist.getId()}"></c:set>
	                    
	                    	<c:url var="editSpecialistUrl" value="/admin/specialists/edit">
	                    		<c:param name="id">${id}</c:param>
	                    	</c:url>
	                    	<a href="${editSpecialistUrl}" class="text-green">
	                    		<i class="fa-solid fa-pen-to-square action-btn"></i>
	                    	</a>
	                    	
	                    	<c:url var="deleteSpecialistUrl" value="/admin/specialists/delete">
	                    		<c:param name="id">${id}</c:param>
	                    	</c:url>
	                    	<a href="${deleteSpecialistUrl}" class="text-red" onclick="return confirm('Are you sure to delete this specialist?')">
	                    		<i class="fa-solid fa-trash-can action-btn ml-1"></i>
	                    	</a>
	                    	
	                    </td>
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
    </div>
</section>
