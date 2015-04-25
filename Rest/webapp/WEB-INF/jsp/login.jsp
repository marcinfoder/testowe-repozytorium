<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	<body onload='document.f.j_username.focus();'>
	
		<div id="login-window">
			<h2 class="login-heading"><spring:message code="login.message" text="message" /></h2>
			<c:if test="${not empty error}">
				<spring:message code="login.error" text="error"/>
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</c:if>
			<div class="login-window-inner">
				
				<form name='f' action="<spring:url value='rest/j_spring_security_check' />" method='POST'>
					
					<div class="login-input-holder">
						<input type="text" name="j_username" placeholder="<spring:message code='login.username' text='username' />" class="login-text login-username">
					</div>
					<div class="login-input-holder">
						<input type="password" name="j_password" placeholder="<spring:message code='login.password' text='password' />" class="login-text login-username">
					</div>
					
					<div class="login-submit-holder">
						<input type="submit" class="button button-login" value="<spring:message code='login.submit' text='submit'/>">
					</div>
				
				</form>
			</div>
			<div class="login-window-copyright">
			<a href="#"><spring:message code="login.lostpassword" text="lostpassword"/></a> 
			</div>
		</div>
	
	</body>
</html>