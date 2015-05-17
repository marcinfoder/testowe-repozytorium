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
					<h2 class="section-title">Campaign steps for campaign: Campaign name</h2>
					
					<div class="grid-item-content">
						<c:choose>
						<c:when test="${!submited}">
						<spring:url value='/service/campaign-step-add' var="actionUrl" htmlEscape='true' />
						<form:form modelAttribute="campaignStepForm"
						 name="campaignStepForm" action="${actionUrl}" method="post" >
						
							<c:if test="${comboBox}">
							<div class="form-item">
								<label>Kampania: </label>
								<form:select path="campaignId" name="campaignId" items="${campaignList}" itemLabel="name" itemValue="campaignId" >
								</form:select>
							</div>
							</c:if>
							 
							<div class="form-item">
								<label>Nazwa kroku: </label>
								<form:input path="name" type="text" placeholder="Nazwa kroku" />
							</div>
							
							<div class="form-item">
								<label>Tag: </label>
								<form:input path="hashTag" type="text" name="Hashtag" />
							</div>
							
							<div class="form-item">
								<label>Opis kroku: </label>
								<form:input path="description" type="text" name="Description" placeholder="Krótki opis kroku" />
							</div>
							
							<div class="form-item">
								<label>Data rozpoczęcia: </label>
								<form:input path="startDate" type="date" name="StartDate" />
							</div>
							
							<div class="form-item">
								<label>Data zakończenia: </label>
								<form:input path="endDate" type="date" name="EndDate" />
							</div>
							
							<c:if test="${!comboBox}">
								<form:hidden path="campaignId" value="${campId}"/>	
							</c:if>
							
							<div class="form-item">
								<input type="submit" class="button" value="Dodaj krok">
							</div>	
						</form:form>
						</c:when>
						<c:otherwise>
							<div class="msg success-msg">
								Krok został poprawnie dodany!
							</div>
							<a class="button" href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />">Dodaj kolejny krok</a>
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