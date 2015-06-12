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
					<h2 class="section-title"><spring:message code="name.newstep" text="Content" /></h2>
					
					<div class="grid-item-content">
						<c:choose>
						<c:when test="${!submited}">
						<spring:url value='/service/campaign-step-add' var="actionUrl" htmlEscape='true' />
						<form:form modelAttribute="campaignStepForm"
						 name="campaignStepForm" action="${actionUrl}" method="post" >
						
							<c:if test="${!added}">
									<div class="msg error-msg">
									<spring:message code="message.stepwrongdates" text="StepWrongDates" />
										Step has not been added! Wrong dates!
									</div>
							</c:if>
						
							<c:if test="${comboBox}">
							<div class="form-item">
								<label><spring:message code="name.campaign" text="Campaign" />: </label>
								<form:select path="campaignId" name="campaignId" items="${campaignList}" itemLabel="name" itemValue="campaignId" >
								</form:select>
							</div>
							</c:if>
							 
							<div class="form-item">
								<label><spring:message code="name.stepname" text="StepName" />: </label>
								<form:input path="name" type="text" placeholder="New step" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.tag" text="Tag" />: </label>
								<form:input path="hashTag" type="text" name="Hashtag" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.stepdescription" text="StepDeescritpion" />: </label>
								<form:input path="description" type="text" name="Description" placeholder="Short step description" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.startdate" text="StartDate" />: </label>
								<form:input path="startDate" type="date" name="StartDate" value="${minDate}" min="${minDate}" max="${maxDate}"/>
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.enddate" text="EndDate" />: </label>
								<form:input path="endDate" type="date" name="EndDate" value="${maxDate}" min="${minDate}" max="${maxDate}" />
							</div>
							
							<c:if test="${!comboBox}">
								<form:hidden path="campaignId" value="${campId}"/>	
							</c:if>
							
							<div class="form-item">
								<input type="submit" class="button" value="<spring:message code="name.addstep" text="AddStep" />">
							</div>	
						</form:form>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${added}">
									<div class="msg success-msg">
											Step has been added!
									</div>
									<a class="button" href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />">Add a new step</a>
								</c:when>
								<c:otherwise>
									<div class="msg error-msg">
										Step has not been added! 
									</div>
									<a class="button" href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />">Try again </a>
								</c:otherwise>
							</c:choose>	
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