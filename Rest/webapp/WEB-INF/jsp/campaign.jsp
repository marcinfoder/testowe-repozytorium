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
					<h2 class="section-title">Campaigns preview</h2>
					
					<div class="grid-item-content">
						<table id="table_privileges">
							<thead>
								<tr>
									<td class="table_cell_username">Nazwa Kampanii</td>
									<td>Opis</td>
									<td>Twitter</td>	
									<td>Facebook</td>										
									<td>Data utworzenia</td>
									<td>Data rozpoczęcia</td>	
									<td>Data ukończenia</td>
									<td>Galeria</td>
									<td>Liczba kroków</td>	
									<td>Dodaj kroki</td>
									<td>Edytuj</td>			
									<td>Usuń kampanię</td>	
								</tr>	
							</thead>
							<tbody>
								<c:forEach var="camp" items="${campaignList}">
								<tr>
									<td>${camp.name}</td>
									<td>${camp.description}</td>
									<td>${camp.twitterConnection}</td>
									<td>${camp.facebookConnection}</td>
									<td>${camp.createdAt}</td>
									<td>${camp.startDate}</td>
									<td>${camp.endDate}</td>
									<td><a href="#" class="button"><i class="fa fa-picture-o"></i></a></td>
									<td><a href="<spring:url value='campaign-steps/${camp.campaignId}' htmlEscape='true' />" class="button">${camp.numberOfSteps}</a></td>
									<td>
										<form action="<spring:url value='/campaign-step-add' htmlEscape='true'/>" method="POST">
											<input type="hidden" value="${camp.campaignId}" name="id_kampanii"> <!-- Tutaj przechowujemy ID kampanii -->
											<input type="submit" class="button" value="Dodaj kroki">
										</form>
									</td>
									<td>
										<form action="<spring:url value='/campaign-step-edit' htmlEscape='true'/>" method="POST">
											<input type="hidden" value="${camp.campaignId}" name="id_kampanii"> <!-- Tutaj przechowujemy ID kampanii -->
											<input type="submit" class="button" value="Edytuj">
										</form>
									</td>
									<td>
										<form action="<spring:url value='/campaign-step-delete' htmlEscape='true'/>" method="POST">
											<input type="hidden" value="${camp.campaignId}" name="id_kampanii"> <!-- Tutaj przechowujemy ID kampanii -->
											<input type="submit" class="button button-red" value="Usuń">
										</form>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<a class="button" href="<spring:url value='/campaign-add' htmlEscape='true' />">Dodaj nową kampanię</a>
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