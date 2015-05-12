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
						<c:when test="${!success}">
						<form:form modelAttribute="campaignForm"
						 name="campaignForm" action="/service/campaign-add" method="post" >
						
							<div class="form-item">
								<label>Nazwa kampanii: </label>
								<form:input path="name" type="text" placeholder="Moja kampania" />
							</div>
							
							<div class="form-item">
								<label>Opis kampanii: </label>
								<form:input path="description" type="text" name="Description" value="Kowalski" />
							</div>
							
							<div class="form-item">
								<label>Twitter: </label>
								<form:checkbox path="twitterConnection" name="twitterChecked" />
							</div>
							
							<div class="form-item">
								<label>Facebook: </label>
								<form:checkbox path="facebookConnection" name="facebookChecked" />
							</div>
							
							<div class="form-item">
								<label>Start date: </label>
								<form:input path="startDate" type="date" name="startDate" />
							</div>
							
							<div class="form-item">
								<label>End date: </label>
								<form:input path="endDate" type="date" name="endDate" />
							</div>
							
							<div class="form-item">
								<input type="submit" class="button" value="Dodaj kampanię">
							</div>	
						</form:form>
						</c:when>
						<c:otherwise>
							<div class="msg success-msg">
								Kampania została poprawnie dodana!
							</div>
							<a class="button" href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />">Dodaj step</a>
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