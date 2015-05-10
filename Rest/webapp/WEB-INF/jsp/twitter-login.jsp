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
				<c:choose>
					<c:when test="${!isAccessToken}">
						<form:form modelAttribute="twitterLoginForm"
							name="twitter-connector" action="twitter-login" method="post">
							<a href="${twitterAuthUrl}" target="_blank"> <spring:message
									code="twitter.auth" text="twitter.authenticate" />
							</a>

							<div class="login-input-holder">
								<spring:message code="twitter.pinInput" text="pinInput" var="pinInput" />
								<form:input type="text" path="twitterPin" placeholder="${pinInput}"
									class="login-text login-username" />
							</div>
							<form:errors path="twitterPin" />
							<input type="submit"
								value="<spring:message code='twitter.connect' text='twitter.connect'/>" />
						</form:form>
					</c:when>
					<c:otherwise>
						You already connected your account.<br>
						Your twitter access token: ${accessToken}<br>	
						Your twitter access token secret: ${accessTokenSecret}<br>
						<p>${test}</p>
					</c:otherwise>
				</c:choose>
			</div>
		</section>
	</div>

	<!-- Load Javascripts -->
</body>
</html>