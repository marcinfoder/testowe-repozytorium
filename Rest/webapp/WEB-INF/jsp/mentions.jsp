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
					<h2 class="section-title">Komentarze na przestrzeni czasu</h2>
					
					<div class="grid-item-content">
						<canvas id="wykres_slupkowy" height="220" width="600"></canvas>
					</div>
					
				</div>
				
				<div class="grid-item grid-item-3 grid-opinie-pozytywne grid-equal-height">
					<h2 class="section-title">Opinie Pozytywne</h2>
					
					<div class="grid-item-content">
						<ul class="opinie opinie-pozytywne">
							<c:forEach items="${mentionsPositive}" var="mention">
							<li>
								<p>${mention.text}</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> ${mention.createdAt}
									</div>
									<div class="opinia-staty">
										<span>Ocena:</span> ${mention.sentencePolarity}
									</div>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="grid-item grid-item-4 grid-wykres-stosunek grid-equal-height">
					<h2 class="section-title">Stosunek opinii pozytywnych do negatywnych</h2>
					
					<div class="grid-item-content">
						<canvas id="wykres_kolowy" height="220" width="600"></canvas>
					</div>
					
				</div>
				
				<div class="grid-item grid-item-3 last grid-opinie-negatywne grid-equal-height">
					<h2 class="section-title">Opinie Negatywne</h2>
					
					<div class="grid-item-content">
						<ul class="opinie opinie-negatywne">
							<c:forEach items="${mentionsNegative}" var="mention">
							<li>
								<p>${mention.text}</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> ${mention.createdAt}
									</div>
									<div class="opinia-staty">
										<span>Ocena:</span> ${mention.sentencePolarity}
									</div>
								</div>
							</li>
							</c:forEach>

						</ul>
					</div>
				</div>

				
			</div>
			
		</section>
		
		
	</div>	
	
	<!-- Load Javascripts -->
	
	<script type="text/javascript" src="/rest/static/js/Chart.min.js"></script>
	<script type="text/javascript" src="/rest/static/js/jquery.js"></script>
	
	<script>
	
	// Równa wysokość
	
	jQuery(window).on('load', function() {
		
		var maxHeight = 0;
		
		jQuery('.grid-equal-height').each(function() {
			if(jQuery(this).height() > maxHeight) {
				maxHeight = jQuery(this).height();
			}
		});
		
		jQuery('.grid-equal-height').css('height',maxHeight+45);
		
	});
	
	jQuery(window).on('resize', function() {
		jQuery('.grid-equal-height').css('height','');
		var maxHeight = 0;
		
		jQuery('.grid-equal-height').each(function() {
			if(jQuery(this).height() > maxHeight) {
				maxHeight = jQuery(this).height();
			}
		});
		//alert(maxHeight);
		jQuery('.grid-equal-height').css('height',maxHeight+45);
	});	
	
	// Słupkowy

	//to mozna zrobić wydajniej w jednej petli
	var daneY = [
	<c:forEach items="${months}" var="month" varStatus="s">	
		${month.value.positive}${s.last ? "" : ","}
	</c:forEach>
	];
	
	var daneY2 = [
	<c:forEach items="${months}" var="month" varStatus="s">	
		${month.value.negative}${s.last ? "" : ","}

	</c:forEach>
	];
	
	var daneX = [
	<c:forEach items="${months}" var="month" varStatus="s">	
		"${month.key}"${s.last ? "" : ","}
	</c:forEach>
	];
	
	//var daneY = [5,12,19,31,45,55,67]; // Tablica z wartościami
	//var daneY2 = [3,7,8,5,3,2,1]; // Tablica z wartościami
	//var daneX = ["Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec"]; // Tablica z punktami na osi X	
	
	var data = {
	    labels: daneX,
	    datasets: [
	        {
	            label: "Pozytywne",
	            fillColor: "#a0d94e",
	            strokeColor: "#a0d94e",
	            highlightFill: "#ade360",
	            highlightStroke: "#ade360",
	            data: daneY
	        },
	        {
	            label: "Negatywne",
	            fillColor: "#ef1818",
	            strokeColor: "#ef1818",
	            highlightFill: "#fa5656",
	            highlightStroke: "#fa5656",
	            data: daneY2
	        }
	    ]
	};	
	
	
	// KOŁOWY	
	
	var dataKolowy = [
		{
			value: ${totalNegative},
	        color:"#ef1818",
	        highlight: "#fa5656",
	        label: "Opinie negatywne"
		    
		},
	    {
			value: ${totalPositive},
		    color: "#a0d94e",
		    highlight: "#ade360",
		    label: "Opinie pozytywne"
	    },
	    
	]
		
	// Inicjalizacja przy załadowaniu całej strony
	
	window.onload = function(){	
	
		// SŁUPKOWY
		var ctx = document.getElementById("wykres_slupkowy").getContext("2d");
		var wykresSlupkowy = new Chart(ctx).Bar(data, {
			responsive: true
		});
		
		
		// WYKRES KOŁOWY
		var ctx2 = document.getElementById("wykres_kolowy").getContext("2d");
		var wykresKolowy = new Chart(ctx2).Pie(dataKolowy, {
			responsive: true
		});
		
		
	}	
	
	</script>	
	
	</body>
</html>