<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="search.books" text="books" /></title>
	<script src="/rest/static/js/jquery-1.8.3.js"></script>
	<script src="/rest/static/js/jquery-ui-1.9.2.custom.js"></script>
	<style>
       #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
</head>
<body>

		<p><spring:message code="book.title" text="title" />:${book.title}</p>
		<p><spring:message code="book.author" text="author" />:${book.author}</p>
		<p><spring:message code="search.authorAddress" text="address" />:${book.authorAddress}</p>
	
<br/>
<sec:authorize access="isAuthenticated()">
	<a href="<spring:url value="/service/showBooks" htmlEscape="true" />">Back</a>
</sec:authorize>
<br/>
<div id="map-canvas"></div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>

<script type="text/javascript">

function initialize() {
	var author = '${book.author}';
	var title = '${book.title}';
	var authorAddress = '${book.authorAddress}';
	
	var contentString = '<div id="content">'+
    	'<div id="siteNotice">'+
    	'</div>'+
    	'<h1 id="firstHeading" class="firstHeading">' + title + '</h1>'+
    	'<div id="bodyContent">'+
    	'<p><b>' + title + '</b>, ' + author + ', <b>' + authorAddress + '</b>,</p>'+
    	'</div>'+
    	'</div>';

    var myLatlng = new google.maps.LatLng(0, 0);
   	var mapOptions = {
	    zoom: 15,
	    center: myLatlng
	};

	var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	  	  
	var geocoder = new google.maps.Geocoder();

	geocoder.geocode( { 'address': authorAddress}, function(results, status) {
	if (status == google.maps.GeocoderStatus.OK) {
		map.setCenter(results[0].geometry.location);
		var infowindow = new google.maps.InfoWindow({
			content: contentString
		});

		var marker = new google.maps.Marker({
			position: results[0].geometry.location,
			map: map,
			title: authorAddress
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map,marker);
		});			
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
    }
  });
}
	  

	$().ready(function() {
		var script = document.createElement('script');
		  script.type = 'text/javascript';
		  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
		      'callback=initialize';
		  document.body.appendChild(script);
		  loaded = true;
	});

</script>

</body>
</html>