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
					<h2 class="section-title">Statystyki ulubionych</h2>
					
					<div class="grid-item-content">
						<canvas id="wykres1" height="220" width="600"></canvas>
					</div>
					
				</div>
				
				<div class="grid-item grid-item-10 last">
					<h2 class="section-title">Statystyki Retweetów</h2>
					
					<div class="grid-item-content">
						<canvas id="wykres2" height="220" width="600"></canvas>
					</div>
				</div>
				
				<div class="grid-item grid-item-10 last">
					<h2 class="section-title">Statystyki tweetów</h2>
					
					<div class="grid-item-content">
						<canvas id="wykres3" height="220" width="600"></canvas>
					</div>
				</div>

			</div>
		</section>
	
	</div>	
	<!-- Load Javascripts -->
	<script type="text/javascript" src="/rest/static/js/Chart.min.js"></script>
	
	<script>
	
	
	var daneY =	[
		<c:forEach items="${twitterCountList}" var="twitterCount" > 
			${twitterCount.favorites},
        </c:forEach>
	];
	
	var daneX = [
	<c:forEach items="${twitterCountList}" var="twitterCount" > 
		"${twitterCount.date}",
	</c:forEach>
	];
	
	var lineChartData = {
		labels : daneX,
		datasets : [
			{
				label: "Facebook",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : daneY
			}
		]

	}
	
	// Drugi wykres
	
	var daneY2 =	[
		<c:forEach items="${twitterCountList}" var="twitterCount" > 
			${twitterCount.retweets},
        </c:forEach>
	];
	
	var daneX2 = [
	<c:forEach items="${twitterCountList}" var="twitterCount" > 
		"${twitterCount.date}",
	</c:forEach>
	];
	
	var lineChartData2 = {
		labels : daneX2,
		datasets : [
			{
				label: "Facebook",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : daneY2
			}
		]

	}
	// Drugi wykres
	
	var daneY3 =	[
		<c:forEach items="${twitterCountList}" var="twitterCount" > 
			${twitterCount.tweets},
        </c:forEach>
	];
	
	var daneX3 = [
	<c:forEach items="${twitterCountList}" var="twitterCount" > 
		"${twitterCount.date}",
	</c:forEach>
	];
	
	var lineChartData3 = {
		labels : daneX3,
		datasets : [
			{
				label: "Facebook",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : daneY3
			}
		]

	}
		
	// Inicjalizacja przy załadowaniu całej strony
	
	window.onload = function(){
		var ctx = document.getElementById("wykres1").getContext("2d");
		window.myLine = new Chart(ctx).Line(lineChartData, {
			responsive: true
		});
		var ctx2 = document.getElementById("wykres2").getContext("2d");
		window.myLine = new Chart(ctx2).Line(lineChartData2, {
			responsive: true
		});
		var ctx3 = document.getElementById("wykres3").getContext("2d");
		window.myLine = new Chart(ctx3).Line(lineChartData3, {
			responsive: true
		});
	}	
	
	</script>
	
	</body>
</html>