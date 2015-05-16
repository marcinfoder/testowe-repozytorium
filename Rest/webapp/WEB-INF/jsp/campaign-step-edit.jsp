<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>GetSocial</title>
		
		<link rel="stylesheet" href="/rest/static/css/reset.css">		
		<link rel="stylesheet" href="/rest/static/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="/rest/static/css/style.css">

		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600' rel='stylesheet' type='text/css'>

	</head>
	
	<body>
	
	<div id="wrapper">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		
		<section id="content-wrapper">
			<%@ include file="/WEB-INF/jsp/navigation.jsp" %>
			
			<div id="content" class="grid">
				
				<div class="grid-item grid-item-10 last">
					<h2 class="section-title">Step edit</h2>
					
					<div class="grid-item-content">
						
                        <c:choose>
						<c:when test="${!success}">
						<spring:url value='/service/campaign-step-edit' var='actionUrl' htmlEscape='true' />
						<form:form modelAttribute="campaignStepForm"
						 name="campaignForm" action="${actionUrl}" method="post" >
						
							<div class="form-item">
								<label>Nazwa kroku: </label>
								<form:input path="name" type="text" value="${name}" />
							</div>
							
							<div class="form-item">
								<label>Opis: </label>
								<form:input path="description" type="text" name="Description" value="${description}" />
							</div>
							
							<div class="form-item">
								<label>Start date: </label>
								<form:input path="startDate" type="date" name="startDate" value="${startDate}" />
							</div>
							
							<div class="form-item">
								<label>End date: </label>
								<form:input path="endDate" type="date" name="endDate" value="${endDate}" />
							</div>
							
							<div class="form-item">
								<input type="hidden" value="${stepId}" name="stepId">
								<input type="submit" class="button" value="Aktualizuj" name="button">
								<input type="submit" class="button button-red" value="Anuluj" name="button">
							</div>	
						</form:form>
						</c:when>
						<c:otherwise>
							<div class="msg success-msg">
								Krok został zaktualizowany!
							</div>
							<a class="button" href="<spring:url value='/service/campaign-steps/${campId}' htmlEscape='true' />">Powrót</a>
						</c:otherwise>
                        </c:choose>
					</div>
				</div>
			</div>
		</section>
	</div>	
	
	<!-- Load Javascripts -->
	<script>
	
	</script>
	
	</body>
</html>