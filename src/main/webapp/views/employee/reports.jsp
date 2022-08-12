<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-100">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		<div class="row tool-bar mt-4">
			
			<c:url var="searchUrl" value="/employee/reports/search" />
		    <form class="row tool-bar" action="${searchUrl}" method="get">
		    
		    	<div class="form-group">
		            <label>Patient</label>
		            <select name="patient">
		            	<option value="">All</option>
		            	<option value="1">Mg Thein Than</option>
		            	<option value="2">Ma Myo Thidar</option>
		            	<option value="3">U Win Tin</option>
		            </select>
		        </div>
		        
		    	<div class="form-group">
		            <label>Doctor</label>
		            <select name="doctor">
		            	<option value="">All</option>
		            	<option value="1">U Tun Naing</option>
		            	<option value="2">Daw San Thidar</option>
		            	<option value="3">U Zay Ya Thu Ta</option>
		            </select>
		        </div>
		        
		        
		        
		        <div class="form-group">
		            <label>Disease</label>
		            <select name="disease">
		            	<option value="">All</option>
		            	<option value="1">Headache</option>
		            	<option value="2">Allergies</option>
		            	<option value="3">Stomach Aches</option>
		            </select>
		        </div>
		        
		        <div class="form-group">
		        	<label>From</label>
		        	<input type="date" />
		        </div>
		        
		        <div class="form-group">
		        	<label>To</label>
		        	<input type="date" />
		        </div>
		        
		        <button class="btn btn-primary ml-1" type="submit">
		        	<i class="fa-solid fa-magnifying-glass mr-0"></i>
		        	Search
		        </button>
		    </form>
		    
		</div>
        
        <table class="w-100 mt-3">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Disease</th>
                    <th>Visite Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-right">1</td>
                    <td>Mg Thein Than</td>
                    <td>U Tun Naing</td>
                    <td>Allergies</td>
                    <td class="text-center">20-6-2-2022 16:40</td>
                    <td></td>
                </tr>
                <tr>
                    <td class="text-right">2</td>
                    <td>Ma Myo Thidar</td>
                    <td>Daw San Thidar</td>
                    <td>Allergies</td>
                    <td class="text-center">20-6-2-2022 16:40</td>
                    <td></td>
                </tr>
                <tr>
                    <td class="text-right">3</td>
                    <td>U Win Tin</td>
                    <td>U Zay Ya Thu Ta</td>
                    <td>Allergies</td>
                    <td class="text-center">20-6-2-2022 16:40</td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>
</section>