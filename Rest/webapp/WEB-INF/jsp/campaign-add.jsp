<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>GetSocial</title>

<link rel="stylesheet" href="/rest/static/css/reset.css">
<link rel="stylesheet"
	href="/rest/static/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/rest/static/css/style.css">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600'
	rel='stylesheet' type='text/css'>

</head>

<body>

	<div id="wrapper">
		<%@ include file="/WEB-INF/jsp/header.jsp"%>

		<section id="content-wrapper">
			<%@ include file="/WEB-INF/jsp/navigation.jsp"%>

			<div id="content" class="grid">

				<div class="grid-item grid-item-10 last">
					<h2 class="section-title"><spring:message code="name.newcampaign" text="NewCamp" /></h2>

					<div class="grid-item-content">

						<c:choose>
							<c:when test="${!submited}">
								<spring:url value='/service/campaign-add' var='actionUrl'
									htmlEscape='true' />
								<form:form modelAttribute="campaignForm" name="campaignForm"
									action="${actionUrl}" method="post">

									<c:if test="${!added}">
									<div class="msg error-msg">
									<spring:message code="message.wrongdates" text="Tag" />
										
									</div>
									</c:if>
									
									<c:if test="${rename}">
									<div class="msg error-msg">
									<spring:message code="message.wrongname" text="Tag" />
										
									</div>
									</c:if>

									<div class="form-item">
										<label><spring:message code="name.campaignname" text="CampName" />: </label>
										<form:input path="name" type="text"
											placeholder="Moja kampania" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.tag" text="Tag" />: </label>
										<form:input path="hashTag" type="text" name="Hashtag" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.description" text="Description" />: </label>
										<form:input path="description" type="text" name="Description"
											value="Description" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.twitter" text="Twitter" />: </label>
										<form:checkbox path="twitterConnection" name="twitterChecked" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.facebook" text="Facebook" />: </label>
										<form:checkbox path="facebookConnection"
											name="facebookChecked" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.startdate" text="StartDate" />: </label>
										<form:input path="startDate" type="date" name="startDate" min="${minDate}"/>
									</div>

									<div class="form-item">
										<label><spring:message code="name.enddate" text="EndDate" />: </label>
										<form:input path="endDate" type="date" name="endDate" min="${minDate}"/>
									</div>

									<div class="form-item">
										<input type="submit" class="button" value="<spring:message code="name.addnewcampaign" text="AddNewCamp" />">
									</div>

								</form:form>
							</c:when>
							<c:otherwise>
								<div class="msg success-msg"><spring:message code="message.campaignadd" text="Add" />:</div>
								<a class="button"
									href="<spring:url value='/service/campaign-step-add/${campId}' htmlEscape='true' />"><spring:message code="name.addstep" text="AddStep" />
								</a>
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