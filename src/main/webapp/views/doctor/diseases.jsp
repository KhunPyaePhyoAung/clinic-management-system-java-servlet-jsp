<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="/WEB-INF/custom-tag.tld" %>

<c:url var="diseasesJsUrl" value="/assets/js/diseases.js" />
<script src="${diseasesJsUrl}" defer></script>

<section class="w-100">
    <div id="disease-form-wrapper" class="container bg-white mb-3 ${empty disease ? 'd-none' : ''}">
        <h2>${empty disease.getId() ? 'Add New' : 'Edit'} Disease</h2>
        
        <c:if test="${not empty diseaseFormException}">
        	<div class="mt-3">
        		<app:errorMessage message="${diseaseFormException.getMessage()}"/>
        	</div>
        </c:if>
        
        <c:url var="saveUrl" value="/doctor/diseases/save" />
        <form class="col w-100 mt-3" action="${saveUrl}" method="post">
        	
        	<input type="hidden" name="id" value="${disease.getId()}" />
        	
            <div class="row">
                <div class="col w-40 form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" value="${disease.getName()}">
                </div>
                <div class="col w-60 form-group">
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" value="${disease.getDescription()}">
                </div>
            </div>
            <div class="d-flex justify-content-end mt-2">
                <button class="btn btn-gray mr-1" type="button" onclick="hideDiseaseForm()">Cancel</button>
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
    </div>

    <div class="container bg-white">
        <h2>${viewTitle}</h2>
        <div class="row tool-bar mt-4">
        
        	<c:url var="searchUrl" value="/doctor/diseases/search"></c:url>
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
            
            <c:url var="addNewDiseaseUrl" value="/doctor/diseases/edit"></c:url>
            <a class="btn btn-primary ml-2" href="${addNewDiseaseUrl}">
            	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
            </a>
        </div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="disease" items="${diseases}" varStatus="status">
            		<tr>
	                    <td class="text-right content-box">${status.getCount()}</td>
	                    <td>${disease.getName()}</td>
	                    <td>${disease.getDescription()}</td>
	                    <td class="text-center content-box">
	                    
	                    	<c:url var="editDiseaseUrl" value="/doctor/diseases/edit">
	                    		<c:param name="id">${disease.getId()}</c:param>
	                    	</c:url>
	                    	<a href="${editDiseaseUrl}" class="text-green"><i class="fa-solid fa-pen-to-square action-btn"></i></a>
	                    	
	                    	<c:url var="deleteDiseaseUrl" value="/doctor/diseases/delete">
	                    		<c:param name="id">${disease.getId()}</c:param>
	                    	</c:url>
	                    	<a href="${deleteDiseaseUrl}" class="text-red"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
	                    </td>
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
    </div>
</section>