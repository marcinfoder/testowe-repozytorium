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

					<p>
			
			<spring:message code="send.Tweet" text="Tweet" />
	
		</p>
	
		<form:form modelAttribute="messagesForm" method="POST" action="send">

			<table>
				<tr>
					<td><form:input path="Tweet"/></td>
				</tr>
				<tr>	
					<td colspan="1"><input type="submit" value="send" /></td>
				</tr>

			</table>

		</form:form>
		<p/>
		<br/>
		

			</div>
			<div id="content" class="grid">
				
			<table>
		    <tr>
		        <th>TWEETY:<br></th>
		    </tr>
				    <c:forEach items="${Tweets}" var="tweet" varStatus="status">
				        <tr>
				            <td>${tweet}</td>
				        </tr>
				    </c:forEach>
			</table>
				
				
			</div>
		</section>
	</div>	
		
		
		 
	</body>
	



</html>