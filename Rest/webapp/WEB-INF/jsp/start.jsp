<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
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

	<div id="wrapper">
		<%@ include file="/WEB-INF/jsp/header.jsp"%>

		<section id="content-wrapper">
			<%@ include file="/WEB-INF/jsp/navigation.jsp"%>

			<div id="content" class="grid">

				<div class="grid-item grid-item-10 last">
					<h2 class="section-title">Statystyki Wyświetleń</h2>

					<div class="grid-item-content">
						<canvas id="wykres" height="220" width="600"></canvas>
					</div>
				</div>

				<div class="grid-item grid-item-5 grid-opinie-pozytywne">
					<h2 class="section-title">Opinie Pozytywne</h2>

					<div class="grid-item-content">
						<ul class="opinie opinie-pozytywne">
							<li>
								<p>
									Zdecydowanie <span>polecam</span> tę firmę, jestem wiernym
									klientem od wielu lat i są <span>niezawodni</span>! Jak
									najbardziej <span>polecam</span> jeszcze raz!
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
							<li>
								<p>
									Jak najbardziej <span>polecam</span> jeszcze raz! Stanowczo <span>polecam</span>
									tę markę z Wrocławia ponieważ sam jestem sosnowcowianem i
									jestem wiernym klientem od wielu lat i są <span>niezawodni</span>!
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
							<li>
								<p>
									Zdecydowanie <span>polecam</span> tę firmę, jestem wiernym
									klientem od wielu lat i są <span>niezawodni</span>! Jak
									najbardziej <span>polecam</span> jeszcze raz!
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
							<li>
								<p>
									Jak najbardziej <span>polecam</span> jeszcze raz! Stanowczo <span>polecam</span>
									tę markę z Wrocławia ponieważ sam jestem sosnowcowianem i
									jestem wiernym klientem od wielu lat i są <span>niezawodni</span>!
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="grid-item grid-item-5 last grid-opinie-negatywne">
					<h2 class="section-title">Opinie Negatywne</h2>

					<div class="grid-item-content">
						<ul class="opinie opinie-negatywne">
							<li>
								<p>
									Ta firma jest <span>okropna</span> i stanowczo <span>odradzam</span>
									jakąkolwiek współpracę z takimi niepoważnymi ludźmi.
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
							<li>
								<p>
									Stanowczo <span>odradzam</span> jakąkolwiek współpracę z takimi
									<span>niepoważnymi</span> ludźmi, ponieważ zostawią Was na
									lodzie już po tygodniu, a wszystko zostało opłacone <span>oszuści</span>
									jedni..
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
							<li>
								<p>
									Ta firma jest <span>okropna</span> i stanowczo <span>odradzam</span>
									jakąkolwiek współpracę z takimi niepoważnymi ludźmi.
								</p>
								<div class="opinia-meta">
									<div class="opinia-zrodlo">
										<span>Źródło:</span> Twitter
									</div>
									<div class="opinia-data">
										<span>Data:</span> 23.05.2015
									</div>
									<div class="opinia-staty">
										<span>Liczba słów:</span> 4
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!-- Load Javascripts -->

	<script type="text/javascript" src="/rest/static/js/Chart.min.js"></script>

	<script>
		var randomScalingFactor = function() {
			return Math.round(Math.random() * 100)
		};
		var lineChartData = {
			labels : [ "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj",
					"Czerwiec", "Lipiec" ],
			datasets : [
					{
						label : "Facebook",
						fillColor : "rgba(220,220,220,0.2)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(220,220,220,1)",
						data : [ randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor() ]
					},
					{
						label : "Twitter",
						fillColor : "rgba(151,187,205,0.2)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(151,187,205,1)",
						data : [ randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor(), randomScalingFactor(),
								randomScalingFactor() ]
					} ]

		}

		window.onload = function() {
			var ctx = document.getElementById("wykres").getContext("2d");
			window.myLine = new Chart(ctx).Line(lineChartData, {
				responsive : true
			});
		}
	</script>

</body>
</html>