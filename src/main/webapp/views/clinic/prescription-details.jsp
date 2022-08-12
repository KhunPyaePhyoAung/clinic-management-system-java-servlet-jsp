<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="w-100">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		
		<div>
			<div class="row mt-4">
				
				<div class="row form-group w-50">
					<div class="col form-group w-50">
						<label for="">Doctor</label>
						<input type="text" value="U Tun Naing" readonly />
					</div>
					<div class="col form-group w-50">
						<label for="">Patient</label>
						<input type="text" value="Mg Thein Than" readonly />
					</div>
				</div>
				
				
				<div class="row form-group w-50">
					<div class="col form-group w-100">
						<label for="">Visit Date</label>
						<input type="date" readonly />
						
					</div>
					
					<div class="col form-group w-100">
						<label for="">Visit Time</label>
						<input type="time" readonly />
					</div>
					
					<div class="col form-group w-100">
						<label for="">Next Visit Date</label>
						<input type="date" readonly />
					</div>
				</div>
				
			</div>
			
			<div class="row mt-4">
	
				<div class="row form-group w-50">
					<div class="col form-group w-100">
						<label for="">Blood Pressure (mm Hg)</label>
						<input type="text" value="120/80" readonly/>
						
					</div>
					<div class="col form-group w-100">
						<label for="">Temperature (°F)</label>
						<input type="number" value="100.6" readonly />
					</div>
					<div class="col form-group w-100">
						<label for="">Pulse Rate (BPM)</label>
						<input type="number" value="70" readonly />
					</div>
				</div>			
				
				<div class="col form-group w-50">
					<label for="">Disease</label>
					<input type="text" value="Headache" readonly />
				</div>
			</div>
		</div>
		
		<div class="d-flex justify-content-end mt-2">
		
			<c:url var="cancelUrl" value="/doctor/prescriptions/search" />
			<a class="btn btn-gray mr-1" href="${cancelUrl}">Back</a>
				
			<c:url var="editPrescriptionUrl" value="/doctor/prescriptions/edit">
           		<c:param name="id">1</c:param>
           	</c:url>
			<a class="btn btn-primary" href="${editPrescriptionUrl}">Edit</a>
		</div>
		
		<h3 class="mt-2">Medicines</h3>
			
		<table class="w-100 mt-2">
			<thead>
				<tr>
					<th class="w-10ch" rowspan="2">#</th>
					<th rowspan="2">Medicine</th>
					<th colspan="4">Dosage</th>
					<th class="w-10ch" rowspan="2">Qty</th>
					<th class="w-10ch" rowspan="2">Duration</th>
					<th rowspan="2">Comment</th>
				</tr>
				
				<tr>
					<th class="w-8ch"><i class="fa-solid fa-circle"></i></th>
					<th class="w-8ch"><i class="fa-solid fa-sun"></i></th>
					<th class="w-8ch"><i class="fa-solid fa-cloud-sun"></i></th>
					<th class="w-8ch"><i class="fa-solid fa-moon"></i></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-right content-box">1</td>
					<td>Amlodipine</td>
					<td class="text-center">2</td>
					<td class="text-center">1</td>
					<td class="text-center">0</td>
					<td class="text-center">0.5</td>
					<td class="text-center content-box">12</td>
					<td class="text-center content-box">3</td>
					<td>After food</td>
				</tr>
				<tr>
					<td class="text-right content-box">2</td>
					<td>Metformin</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center content-box">6</td>
					<td class="text-center content-box">3</td>
					<td>After Food</td>
				</tr>
				<tr>
					<td class="text-right content-box">3</td>
					<td>Levothyroxine</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center">1</td>
					<td class="text-center">10</td>
					<td class="text-center">3</td>
					<td>After Food</td>
				</tr>
			</tbody>
		</table>
	
		<div class="mt-4">
			<h3>Note</h3>
			<textarea class="w-100 mt-2" rows="5" readonly></textarea>                                                   
		</div>
	</div>
	
	
</section>