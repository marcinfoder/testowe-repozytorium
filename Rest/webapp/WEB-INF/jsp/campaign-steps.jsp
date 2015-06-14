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
					<h2 class="section-title"><spring:message code="name.campstepsfor" text="CampStepsFor" /> ${campName}</h2>
					
					<div class="grid-item-content">
						<table id="table_privileges">
							<thead>
								<tr>
									<td class="table_cell_username"><spring:message code="name.stepname" text="StepName" />:</td>
									<td><spring:message code="name.tag" text="Tag" /></td>
									<td><spring:message code="name.description" text="Description" /></td>
									<td><spring:message code="name.startdate" text="StartDate" /></td>	
									<td><spring:message code="name.enddate" text="EndDate" /></td>
									<td><spring:message code="name.order" text="Order" /></td>	
									<td><spring:message code="name.showcontent" text="ShowContent" /></td>	
									<td><spring:message code="name.editstep" text="EditStep" /></td>			
									<td><spring:message code="name.deletestep" text="DeleteStep" /></td>	
								</tr>	
							</thead>
							<tbody>
								<c:forEach var="step" items="${stepList}">
								<tr>
									<td>${step.name}</td>
									<td>${step.hashTag}</td>
									<td>${step.description}</td>
									<td>${step.startDate}</td>
									<td>${step.endDate}</td>
									<td>${step.stepOrder}</td>
									<td><a class="button" href="<spring:url value='/service/campaign-step-messages/${step.campaignId}/${step.stepId}' htmlEscape='true' />"><spring:message code="name.showcontent" text="ShowContent" /></a></td>
									<td>
										
										<form action="<spring:url value='/service/campaign-step-edit/${step.stepId}' />" method="GET">
											<c:if test="${!step.expired}">
												<input type="submit" class="button" value="<spring:message code="name.edit" text="edit" />">
											</c:if>	
										</form>
										
									</td>
									<td>
										<form action="<spring:url value='/service/campaign-step-delete' htmlEscape='true' />" method="POST">
											<input type="hidden" value="${step.campaignId}" name="campaignId"> <!-- Tutaj przechowujemy ID step -->
											<input type="hidden" value="${step.stepId}" name="stepId"> <!-- Tutaj przechowujemy ID step -->
											<c:if test="${!step.expired}">
											<input type="submit" class="button button-red" value="<spring:message code="name.delete" text="Delete" />">
											</c:if>
										</form>
									</td>
								</tr>
								</c:forEach>	
							</tbody>
						</table>
						
						<a class="button" href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />"><spring:message code="name.addstep" text="Addstep" /></a>
						<a class="button" href="<spring:url value='/service/campaigns' htmlEscape='true' />"><spring:message code="name.campaignreturn" text="CampReturn" /></a>
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