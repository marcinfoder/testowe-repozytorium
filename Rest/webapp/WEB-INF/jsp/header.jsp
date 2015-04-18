<header id="header">
	<div id="site-logo">
		<h1>Get<span>Social</span></h1>
	</div>
			
	<div id="header-nav">
		<ul>
			<li class="user-panel">
				<a href="#">
					<div class="user-avatar-wrap">
						<img class="user-avatar" src="/rest/static/img/avatar.jpg">
					</div>
					<div class="user-title">
						<spring:message code="welcome" text="welcom" /> <sec:authentication property="principal.username"/>
					</div>
				</a>
			</li>
			<li class="logout">
				<a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />">
					<i class="fa fa-sign-out"></i>
				</a>
			</li>
		</ul>
	</div>	
</header>