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
					<h2 class="section-title"><spring:message code="name.campaignedit" text="CampaignEdit" /></h2>
					
					<div class="grid-item-content">
						
                        <c:choose>
						<c:when test="${!submited}">
						<spring:url value='/service/campaign-edit' var='actionUrl' htmlEscape='true' />
						<form:form modelAttribute="campaignForm"
						 name="campaignForm" action="${actionUrl}" method="post" >
						
							<c:if test="${!updated}">
									<div class="msg error-msg">
										<spring:message code="message.wrongdates" text="WrongDates" />:
									</div>
							</c:if>
							
							<c:if test="${rename}">
									<div class="msg error-msg">
										<spring:message code="name.wrongname" text="WrongName" />:
									</div>
									</c:if>
							
							<div class="form-item">
								<label><spring:message code="name.campaignname" text="CampaignName" />: </label>
								<form:input path="name" type="text" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.tag" text="Tag" />: </label>
								<form:input path="hashTag" type="text" name="Hashtag" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.description" text="Description" />: </label>
								<form:input path="description" type="text" name="Description" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.twitter" text="Twitter" />: </label>
								<form:checkbox path="twitterConnection" name="twitterChecked" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.facebook" text="Facebook" />: </label>
								<form:checkbox path="facebookConnection" name="facebookChecked" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.startdate" text="StartDate" />: </label>
								<form:input path="startDate" type="date" name="startDate" value="${startDate}" min="${currentDate}" max="${maxDateStart}" disabled="${disableStartDate}" />
							</div>
							
							<div class="form-item">
								<label><spring:message code="name.enddate" text="EndDate" />: </label>
								<form:input path="endDate" type="date" name="endDate" value="${endDate}" min="${minDateEnd}" />
							</div>
							
							<div class="form-item">
							    <input type="hidden" value="${campId}" name="campId">
								<input type="submit" class="button" value="Update" name="update">
								<input type="submit" class="button button-red" value="Cancel" name="cancel">
							</div>	
						</form:form>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${updated}">
									<div class="msg success-msg">
									<spring:message code="message.updated" text="Updated" />

									</div>
									<a class="button" href="<spring:url value='/service/campaigns' htmlEscape='true' />">Back to campaigns</a>
								</c:when>
								<c:otherwise>
									<div class="msg error-msg">
									<spring:message code="message.notupdated" text="NotUpdated" />
										
									</div>
									<a class="button" href="<spring:url value='/service/campaigns' htmlEscape='true' />">Back to campaigns</a>
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