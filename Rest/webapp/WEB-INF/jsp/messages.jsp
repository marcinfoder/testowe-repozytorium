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
					<h2 class="section-title">Messages</h2>

					<div class="grid-item-content">

						<c:choose>
							<c:when test="${!submited}">
							
								<spring:url value='/service/messages' var="actionUrl"
									htmlEscape='true' />
								<form:form modelAttribute="MessageForm" name="MessageForm"
									action="${actionUrl}" method="POST">

									<form:errors element="div" path="campaignId" class="msg error-msg" />
									<form:errors element="div" path="stepId" class="msg error-msg" />
									<form:errors element="div" path="publishDate" class="msg error-msg" />
									<form:errors element="div" path="text" class="msg error-msg" />
									
									<c:if test="${isNull}">
										<div class="msg error-msg"><spring:message code="message.null" text="twitter error" /></div>
									</c:if>
									
									<c:if test="${isTwitterError}">
										<div class="msg error-msg"><spring:message code="message.twitterError" text="twitter error" /></div>
									</c:if>
									
									<c:if test="${isPast}">
										<div class="msg error-msg"><spring:message code="message.pastDate" text="should be past" /></div>
									</c:if>
										
									<c:if test="${isFuture}">
										<div class="msg error-msg"><spring:message code="message.futureDate" text="should be future" /></div>
									</c:if>
									<div class="form-item">
										<label><spring:message code="name.campaign" text="Campaign" />: </label>
										<form:select path="campaignId" id="CampaignIdSelect"
											name="campaignId"> 
											<form:option value="-1">Select campaign</form:option>
   											<form:options items="${campaignList}" itemLabel="name" itemValue="campaignId"/>
										</form:select>
									</div>

									<div class="form-item">
										<label><spring:message code="name.step" text="Step" />: </label>
										<form:select path="stepId" id="StepIdSelect" name="stepId">
											<form:option value="-1" label="Select step" />
   											<form:options items="${campaignStepList}" itemLabel="name" itemValue="stepId" />
										</form:select>
									</div>

									<div class="form-item">
										<label><spring:message code="name.date" text="Date1" />: </label>
										<form:input path="publishDate" type="text" name="publishDate" id="dataTwitter" />
									</div>

									<div class="form-item">
										<label><spring:message code="name.content" text="Content" />: </label> <input type="hidden" name="Hashtag"
											id="Hashtag" value="${hashtag}" />
										<form:textarea path="text" name="TwitterContent"
											maxlength="140" id="TwitterContent" />
										<div id="pozostale-znaki"></div>
									</div>

									<div class="form-item">
										<input type="submit" class="button" value="<spring:message code="name.send" text="Send" />" name="send" />
										<input type="submit" class="button" value="<spring:message code="name.add" text="Add" />" name="add" />
									</div>
								</form:form>
							</c:when>
							<c:otherwise>
								<div class="msg success-msg"><spring:message code="message.add" text="MsgAdd" /></div>
								<a class="button"
									href="<spring:url value='/service/messages' htmlEscape='true' />">
									<spring:message code="message.addnext" text="MsgAddNext" /></a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</section>
	</div>
<<<<<<< HEAD
=======
	<div>
		<section>
			<div id="content" class="grid">

				<table>
					
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
>>>>>>> ecb34bc9f6b746b2d4481c2e4b538fa6b50bd13f

	<script type="text/javascript" src="/rest/static/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function() {		
							
							jQuery('#TwitterContent').keyup(
									function() {
										var max = 140 - jQuery("#Hashtag")
												.val().length;
										jQuery(this).attr("maxlength", max);
										var len = jQuery(this).val().length;
										if (len >= max) {
											jQuery('#pozostale-znaki').text(
													'Wykorzystałeś limit!');
										} else {
											var char = max - len;
											jQuery('#pozostale-znaki').text(
													  char + ' characters left.');
										}
									});
							jQuery('#CampaignIdSelect')
									.change(
											function() {
												<spring:url value="/service/campaign-hashtag" htmlEscape="true" var="getCampaignHashTag" />
												jQuery
														.ajax({
															type : "GET",
															url : "${getCampaignHashTag}",
															data : {
																campaignId : jQuery(
																		'#CampaignIdSelect')
																		.val()
															},
															success : function(
																	data) {
																//settingup campaign steps
																jQuery(
																		'#StepIdSelect')
																		.empty();
																for (var i = 0; i < data.stepsList.length; i++) {
																	var newOption = jQuery('<option value="' + data.stepsList[i].stepId + '">'
																			+ data.stepsList[i].name
																			+ '</option>');
																	jQuery(
																			'#StepIdSelect')
																			.append(
																					newOption);
																}

																//setting up hashtag
																jQuery(
																		'#Hashtag')
																		.val(
																				data.hashtag);
															},
															error : function() {
																alert('Error occured');
															}
														});
											});
						});
		
						jQuery("#dataTwitter").datepicker();
	</script>
	
	
	
</body>

</html>
