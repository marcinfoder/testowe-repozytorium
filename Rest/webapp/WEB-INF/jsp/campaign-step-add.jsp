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
					<h2 class="section-title">Campaign steps for campaign: Campaign name</h2>
					
					<div class="grid-item-content">
						<form action="#" method="POST">
						
							<div class="form-item">
								<label>Kampania: </label>
								<select name="CampaignID">
									<option value="1">Kampania Jeden</option>
									<option value="2">Nazwa Kampanii 2</option>
									<option value="3">Nazwa Kampanii 3</option>
								</select>
							</div>
							
							<div class="form-item">
								<label>Nazwa kroku: </label>
								<input type="text" name="Name" placeholder="Krok nr 1">
							</div>
							
							<div class="form-item">
								<label>Opis kroku: </label>
								<input type="text" name="Description" placeholder="Krótki opis kroku">
							</div>
							
							<div class="form-item">
								<label>Data rozpoczęcia: </label>
								<input type="date" name="StartDate">
							</div>
							
							<div class="form-item">
								<label>Data zakończenia: </label>
								<input type="date" name="EndDate">
							</div>
							
							<div class="form-item">
								<label>Facebook: </label>
								<input type="text" name="Facebook" value="1">
							</div>
							
							<div class="form-item">
								<input type="submit" class="button" value="Dodaj krok">
							</div>	
						</form>
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