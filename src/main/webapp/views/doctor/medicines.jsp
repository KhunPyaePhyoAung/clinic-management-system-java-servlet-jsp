<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-100">
    <div id="medicine-form" class="container bg-white mb-3 d-none">
        <h2>Add New Medicine</h2>
        
        <c:url var="saveUrl" value="/doctor/medicines/save" />
        <form class="col w-100 mt-3" action="${saveUrl}" method="post">
            <div class="row">
                <div class="col w-40 form-group">
                    <label for="">Name</label>
                    <input type="text">
                </div>
                <div class="col w-60 form-group">
                    <label for="">Description</label>
                    <input type="text">
                </div>
            </div>
            <div class="d-flex justify-content-end mt-2">
                <button class="btn btn-gray mr-1" type="button" onclick="hideMedicineForm()">Cancel</button>
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
    </div>

    <div class="container bg-white">
        <h2>${viewTitle}</h2>
        <div class="row tool-bar mt-4">
        	
        	<c:url var="searchUrl" value="/doctor/medicines/search" />
            <form class="row tool-bar" action="${searchUrl}" method="get">
                <div>
                    <label for="">Search</label>
                    <input type="text" name="k" value="${param.k}" placeholder="keyword">
                </div>
                
                <div class="ml-2">
                	<label for="">Status</label>
                	<select name="s">
                		<option value="all" selected>All</option>
                		<option value="active"  ${param.s eq 'active' ? 'selected' : ''}>
                			Active
                		</option>
                		<option value="deleted"  ${param.s eq 'deleted' ? 'selected' : ''}>
                			Deleted
                		</option>
                	</select>
                </div>
                
                <button class="btn btn-primary ml-2" type="submit">
                	<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
                </button>
	            
            </form>
            
            <button class="btn btn-primary ml-2" onclick="show('medicine-form')">
            	<i class="fa-solid fa-plus mr-0"></i>
		    	Add New
            </button>
            
        </div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-right content-box">1</td>
                    <td>Amlodipine</td>
                    <td></td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td class="text-center content-box">
                    
                    	<c:url var="editMedicineUrl" value="/doctor/medicines/edit"></c:url>
                    	<a href="${editMedicineUrl}"><i class="fa-solid fa-pen-to-square action-btn"></i></a>
                    	
                    	<c:url var="deleteMedicineUrl" value="/doctor/medicines/delete"></c:url>
                    	<a href="${deleteMedicineUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    	<c:url var="restoreMedicineUrl" value="/doctor/medicines/restore"></c:url>
                    	<a href="${restoreMedicineUrl}"><i class="fa-solid fa-arrows-rotate action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">2</td>
                    <td>Metformin</td>
                    <td></td>
                    <td class="text-center content-box">
                    	<span class="status status-green">Active</span>
                    </td>
                    <td class="text-center content-box">
                    
                    	<c:url var="editMedicineUrl" value="/doctor/medicines/edit"></c:url>
                    	<a href="${editMedicineUrl}"><i class="fa-solid fa-pen-to-square action-btn"></i></a>
                    	
                    	<c:url var="deleteMedicineUrl" value="/doctor/medicines/delete"></c:url>
                    	<a href="${deleteMedicineUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    	<c:url var="restoreMedicineUrl" value="/doctor/medicines/restore"></c:url>
                    	<a href="${restoreMedicineUrl}"><i class="fa-solid fa-arrows-rotate action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
                <tr>
                    <td class="text-right content-box">3</td>
                    <td>Levothyroxine</td>
                    <td></td>
                    <td class="text-center content-box">
                    	<span class="status status-red">Deleted</span>
                    </td>
                    <td class="text-center content-box">
                    
                    	<c:url var="editMedicineUrl" value="/doctor/medicines/edit"></c:url>
                    	<a href="${editMedicineUrl}"><i class="fa-solid fa-pen-to-square action-btn"></i></a>
                    	
                    	<c:url var="deleteMedicineUrl" value="/doctor/medicines/delete"></c:url>
                    	<a href="${deleteMedicineUrl}"><i class="fa-solid fa-trash-can action-btn ml-1"></i></a>
                    	
                    	<c:url var="restoreMedicineUrl" value="/doctor/medicines/restore"></c:url>
                    	<a href="${restoreMedicineUrl}"><i class="fa-solid fa-arrows-rotate action-btn ml-1"></i></a>
                    	
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>