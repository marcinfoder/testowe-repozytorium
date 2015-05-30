<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<div>
		<spring:url value="/service/register" var="actionUrl" htmlEscape="true" />
		<form:form modelAttribute="registerForm" name="registerForm" action="${actionUrl}" method="post">
		<div class="login-window-inner">
            
            <div class="login-window-error">
                <form:errors path="username" element="p" />
                <form:errors path="password" element="p" />
                <form:errors path="firstname" element="p" />
                <form:errors path="lastname" element="p" />
                <form:errors path="email" element="p" />
                <form:errors path="phonenumber" element="p" />
                <form:errors path="groupname" element="p" />
                <form:errors path="description" element="p" />

                <c:if test="exist">
                    <spring:message code="register.exist" text="exist" htmlEscape="true"/>
           		</c:if>
			</div>
                                
            <div class="login-submit-holder">
                    <spring:message code="register.username" text="username" var="usernameVar" />
                    <form:input path="username" name="username" placeholder="${usernameVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.password" text="password" var="passwordVar" />
                    <form:input path="password" name="password" placeholder="${passwordVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.firstname" text="firstname" var="firstnameVar" />
                    <form:input path="firstname" name="firstname" placeholder="${firstnameVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.lastname" text="lastname" var="lastnameVar" />
                    <form:input path="lastname" name="lastname" placeholder="${lastnameVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.email" text="email" var="emailVar" />
                    <form:input path="email" name="email" placeholder="${emailVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.phonenumber" text="phonenumber" var="phonenumberVar" />
                    <form:input path="phonenumber" name="phonenumber" placeholder="${phonenumberVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.groupname" text="groupname" var="groupnameVar" />
                    <form:input path="groupname" name="groupname" placeholder="${groupnameVar}" class="login-text login-username" />
            </div>
            <div class="login-submit-holder">
                    <spring:message code="register.description" text="description" var="descriptionVar" />
                    <form:input path="description" name="description" placeholder="${descriptionVar}" class="login-text login-username" />
            </div>

            <div class="login-submit-holder">
                    <input type="submit" class="button button-login"
                            value="<spring:message code='login.submit' text='submit'/>">
            </div>
		</div>
		</form:form>
	</div>

</body>
</html>