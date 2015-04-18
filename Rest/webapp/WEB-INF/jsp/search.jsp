<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Search Books</title>

<script src="/rest/static/js/jquery-1.8.3.js"></script>
<script src="/rest/static/js/jquery-ui-1.9.2.custom.js"></script>
<link rel="stylesheet" href="/rest/static/css/style.css">

	</head>	
	<body>
		<p>
			<fmt:message key="welcome"/>
			<sec:authentication property="principal.username"/>		
		</p>
		<p>
		<spring:message code="search.language" text="my language" /> : <a href="?language=en">English</a>|<a href="?language=de">Deutsch</a>
		</p>
		<form:form modelAttribute="searchForm" method="POST" action="search">
			<table>
				<tr>
					<td><spring:message code="search.author" text="author" /></td>
					<td><form:input path="author" /></td>
					<td><form:errors path="author" cssClass="error"/></td>
				</tr>
				<tr>
					<td><spring:message code="search.title" text="title" /></td>
					<td><form:input path="title"/></td>
					<td><form:errors path="title" cssClass="error"/></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="search" /></td>
				</tr>
			</table>
		</form:form>
		<p/>
		<br/>
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		<sec:authorize access="hasRole('guest')">
			<h4>You are guest!</h4>
		</sec:authorize>
		
	</body>
	<script>
	$().ready(function() {
		$(function() {
			$( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
			});
	});
</script>
</html>