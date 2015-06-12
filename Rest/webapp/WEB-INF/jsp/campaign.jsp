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
					<h2 class="section-title">Campaigns preview</h2>
					
					<div class="grid-item-content">
						<table id="table_privileges">
							<thead>
								<tr>
									<td class="table_cell_username"><spring:message code="name.campaignname" text="Campaign" /></td>
									<td><spring:message code="name.tag" text="Tag" /></td>
									<td><spring:message code="name.description" text="Description" /></td>
									<td><spring:message code="name.twitter" text="Twitter" /></td>	
									<td><spring:message code="name.Facebook" text="Facebook" /></td>										
									<td><spring:message code="name.createdon" text="CreatedOn" /></td>
									<td><spring:message code="name.startdate" text="StartDate" /></td>	
									<td><spring:message code="name.enddate" text="EndDate" /></td>
									<td><spring:message code="name.gallery" text="Gallery" /></td>
									<td><spring:message code="name.stepnumber" text="Stepnumber" /></td>	
									<td><spring:message code="name.addsteps" text="AddSteps" /></td>
									<td><spring:message code="name.edit" text="Edit" /></td>			
									<td><spring:message code="name.deletecampaign" text="DelCampaign" /></td>	
								</tr>	
							</thead>
							<tbody>
								<c:forEach var="camp" items="${campaignList}">
								<tr>
									<td>${camp.name}</td>
									<td>${camp.hashTag}</td>
									<td>${camp.description}</td>
									<td>${camp.twitterConnection}</td>
									<td>${camp.facebookConnection}</td>
									<td>${camp.createdAt}</td>
									<td>${camp.startDate}</td>
									<td>${camp.endDate}</td>
									<td><a href="#" class="button"><i class="fa fa-picture-o"></i></a></td>
									<td><a href="<spring:url value='campaign-steps/${camp.campaignId}' htmlEscape='true' />" class="button">${camp.numberOfSteps}</a></td>
									<td>
									<c:if test="${!camp.expired}">
										<a class="button" href="<spring:url value='/service/campaign-step-add/${camp.campaignId}' htmlEscape='true'/>"><spring:message code="name.addsteps" text="Add" /></a>
									</c:if>
									</td>
									<td>
										
											<form action="<spring:url value='/service/campaign-edit/${camp.campaignId}' htmlEscape='true'/>" method="GET">
											<c:if test="${!camp.expired}">
												<input type="submit" class="button" value="<spring:message code="name.edit" text="Edit" />">
											</c:if>	
											</form>
										
										
									</td>
									<td>
										<form action="<spring:url value='/service/campaign-delete' htmlEscape='true'/>" method="POST">
											<input type="hidden" value="${camp.campaignId}" name="campaignId"> <!-- Tutaj przechowujemy ID kampanii -->
											<c:if test="${!camp.expired}">
											<input type="submit" class="button button-red" value="<spring:message code="name.deletecampaign" text="DelCampaign" />">
											</c:if>
										</form>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<a class="button" href="<spring:url value='/service/campaign-add' htmlEscape='true' />"><spring:message code="name.addnewcampaign" text="AddNewCamp" /></a>
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