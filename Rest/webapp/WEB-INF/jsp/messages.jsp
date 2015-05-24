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
						<c:when test="${!submited}">
						<spring:url value='/service/messages' var="actionUrl" htmlEscape='true' />
						<form:form modelAttribute="MessageForm" name="MessageForm" action="${actionUrl}" method="POST" >
						
							<div class="form-item">
								<label>Kampania: </label>
								<form:select path="campaignId" id="CampaignIdSelect" name="campaignId" items="${campaignList}" itemLabel="name" itemValue="campaignId">
								</form:select>
							</div>
							
							<div class="form-item">
								<label>Krok: </label>
								<form:select path="stepId" id="StepIdSelect" name="stepId"  items="${campaignStepList}" itemLabel="name" itemValue="stepId" >
								</form:select>
							</div>
												
							<div class="form-item">
								<label>Data: </label>
								<form:input path="publishDate" type="text" name="publishDate" value="${currDate}" />
							</div>
							
							<div class="form-item">
								<label>Treść: </label>
								<input type="hidden" name="Hashtag" id="Hashtag" value="${hashtag}"/>
								<form:textarea path="text" name="TwitterContent" maxlength="140" id="TwitterContent" />
								<div id="pozostale-znaki"></div>
							</div>
														
							<div class="form-item">
															
							<input type="submit" class="button" value="Wyslij" name="button" >	
							<input type="hidden" value="${MessageForm.publishDate}" name="addDate">
							<input type="submit" class="button" value="Dodaj" name="button" >	
							
							</div>	
											
						</form:form>
						
						</c:when>
						<c:otherwise>
						<c:choose>
							<c:when test="${success}">
								<div class="msg success-msg">
									Dodano wiadomość!
															
								</div>
								<div class="form-item">
								<form action="<spring:url value='/service/messages' htmlEscape='true'/>" method="GET">
									<input type="submit" class="button" value="Powrót">
								</form>
								</div>
							</c:when>
							<c:otherwise>
								<div class="msg error-msg">
									Błędna data! Nie dodano wiadomości.
															
								</div>
								
								<div class="form-item">
								<form action="<spring:url value='/service/messages' htmlEscape='true'/>" method="GET">
									<input type="submit" class="button" value="Powrót">
								</form>
								</div>
								
							</c:otherwise>
					   </c:choose>
						
							
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</section>
	</div>	
	<div>
		<section>
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

	<script type="text/javascript" src="/rest/static/js/jquery.js"></script>
	<script type="text/javascript">
	jQuery(document).ready(function () {
		jQuery('#TwitterContent').keyup(function () {
			var max = 140 - jQuery("#Hashtag").val().length;
			jQuery(this).attr("maxlength", max);
			var len = jQuery(this).val().length;
			if (len >= max) {
			  jQuery('#pozostale-znaki').text('Wykorzystałeś limit!');
			} 
			else {
			  var char = max - len;
			  jQuery('#pozostale-znaki').text('Pozostało ' + char + ' znaków.');
			}
		});
		jQuery('#CampaignIdSelect').change(function() {
			<spring:url value="/service/campaign-hashtag" htmlEscape="true" var="getCampaignHashTag" />
		    jQuery.ajax({
		        type:"GET",
		        url : "${getCampaignHashTag}",
		        data : { campaignId: jQuery('#CampaignIdSelect').val()},
		        success : function(data) {
		        	jQuery('#StepIdSelect').empty();
					for(var i = 0; i < data.stepsList.length; i++){
                    	var newOption = jQuery('<option value="' + data.stepsList[i].stepId + '">' + data.stepsList[i].name + '</option>');
                        jQuery('#StepIdSelect').append(newOption);
					}   
		        	
		            jQuery('#Hashtag').val(data.hashtag);
		        },
		        error: function() {
		            alert('Error occured');
		        }
		    });
		});
	});

	</script>
	</body>

</html>
