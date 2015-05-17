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
						<table id="table_privileges">
							<thead>
								<tr>
									<td class="table_cell_username">Data</td>
									<td>Treść</td>
									<td>Usuń</td>	

								</tr>	
							</thead>
							<tbody>
								<c:forEach var="message" items="${messageList}">
								<tr>
									<td>${message.twitterPublishAt}</td>
									<td>${message.twitterContent}</td>
									<td>
										<form action="<spring:url value='/service/campaign-step-message-delete' htmlEscape='true' />" method="POST">
											<input type="hidden" value="${message.messageId}" name="messageId">
											<input type="hidden" value="${message.stepId}" name="stepId">
											<input type="hidden" value="${message.campaignId}" name="campId">
											<c:choose>
												<c:when test="${!message.alreadySent}">
													<input type="submit" class="button button-red" value="Usuń">
												</c:when>
												<c:otherwise>
												
												</c:otherwise>
											</c:choose>
										</form>
									</td>
								</tr>
								</c:forEach>	
							</tbody>
						</table>
						
						<a class="button" href="<spring:url value='/service/messages/${campId}' htmlEscape='true' />">Dodaj nową wiadomość</a>
						<a class="button" href="<spring:url value='/service/campaign-steps/${campId}' htmlEscape='true' />">Powrót</a>
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