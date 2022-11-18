<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="/WEB-INF/custom-tag.tld" %>

<section class="w-30">
	<div class="container bg-white">
		<h2>${viewTitle}</h2>
		
		<c:if test="${not empty exception}">
			<app:errorMessage message="${exception.getMessage()}"/>
		</c:if>
		
		<c:url var="saveUrl" value="/member/change-password"></c:url>
		<form class="col mt-4" action="${saveUrl}" method="post">
		
			<input type="hidden" name="id" value="${param.id}"/>
			
			<div class="col form-group">
				<label for="">Old Password</label>
				<input type="password" name="oldPassword" value="${param.oldPassword}"/>
			</div>
			
			<div class="col form-group mt-2">
				<label for="">New Password</label>
				<input type="password" name="newPassword" value="${param.newPassword}"/>
			</div>
			
			<div class="col form-group mt-2">
				<label for="">Confirm Password</label>
				<input type="password" />
			</div>
			
			<div class="col mt-4">
				<button class="btn btn-primary">Save Change</button>
				
				<c:url var="cancelUrl" value="/member/home"></c:url>
				<a class="btn btn-gray mt-1"  href="${cancelUrl}">Cancel</a>
			</div>
		</form>
	</div>
</section>