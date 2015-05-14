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
					<h2 class="section-title">Messages</h2>
					
					<div class="grid-item-content">
						<c:choose>
						<c:when test="${!success}">
						<spring:url value='/service/messages' var="actionUrl" htmlEscape='true' />
						<form:form modelAttribute="MessageForm"
						 name="MessageForm" action="${actionUrl}" method="POST" >
						
							<c:if test="${comboBox1}">
							<div class="form-item">
								<label>Kampania: </label>
								<form:select path="campaignId" name="campaignId" items="${campaignList}" itemLabel="name" itemValue="campaignId" >
								
								</form:select>													
								
							</div>
							</c:if>
							
							<c:if test="${comboBox2}">
							<div class="form-item">
								<label>Krok: </label>
								<form:select path="stepId" name="stepId"  items="${campaignStepList}" itemLabel="name" itemValue="stepId" >
								</form:select>
							</div>
							</c:if>
							
						
							<div class="form-item">
								<label>Data: </label>
								<form:input path="publishDate" type="text" name="publishDate"  />
							</div>
							
							<div class="form-item">
								<label>Treść: </label>
								<form:input path="text" type="text" name="text"  />
							</div>
														
							<div class="form-item">
															
							<input type="submit" class="button" value="Wyslij" name="button" >	
							
							<input type="submit" class="button" value="Dodaj" name="button" >	
							
							</div>	
											
						</form:form>
						
						</c:when>
						<c:otherwise>
							<div class="msg success-msg">
								Krok został poprawnie dodany!
								
								<form action="<spring:url value='/service/messages-add' htmlEscape='true'/>" method="POST">
								<input type="submit" class="button" value="Dodaj2">
							</form>
							</div>
							
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</section>
	</div>	
	

			<div id="content" class="grid">
				
			<table>
		    <tr>
		        <th>TWEETY:<br></th>
		    </tr>
				    <c:forEach items="${Tweets}" var="tweet" varStatus="status">
				        <tr>
				        	<td>${tweet.twitterPublishAt}</td>
				            <td>${tweet.twitterContent}</td> 
				        </tr>
				    </c:forEach>
			</table>
				
				
			</div>
		</section>
	</div>	
	

		
		
		 
	</body>
	



</html>