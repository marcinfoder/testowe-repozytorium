<div id="navigation">
	<ul id="main-navigation">
		<li class="current-page">
			<a href="#"><i class="fa fa-home"></i>
				<spring:message code="nav.home" text="Home" />
			</a>
		</li>
		<li class="has-submenu">
			<a href="#"><i class="fa fa-bar-chart"></i>
				<spring:message code="nav.statistics" text="Statistics" /></a>
			<ul class="submenu">
				<li><a href="#"><i class="fa fa-eye"></i>
						<spring:message code="nav.displays" text="Displays" /></a>
				</li>
				<li><a href="#"><i class="fa fa-comments-o"></i>
					<spring:message code="nav.opinions" text="Opinions" /></a>
				</li>
				<li><a href="#"><i class="fa fa-globe"></i>
					<spring:message code="nav.localization" text="Localization" /></a>
				</li>
			</ul>
		</li>
		<li>
			<a href="#"><i class="fa fa-flag"></i>
           		<spring:message code="nav.campaigns" text="Campaigns" /></a>
			</a>
			<ul class="submenu">
            	<li>
                	<a href="<spring:url value="/service/campaigns" htmlEscape='true' />">
                		<i class="fa fa-eye"></i>
                		<spring:message code="nav.preview" text="Preview" />
                	</a>
				</li>
                <li class="current-page">
                	<a href="<spring:url value='/service/campaign-add' htmlEscape='true' />">
                		<i class="fa fa-plus-circle"></i>
                		<spring:message code="nav.addCampaig" text="Add campaign" />
                	</a>
                </li>
                <li>
                	<a href="<spring:url value='/service/campaign-step-add' htmlEscape='true' />">
                		<i class="fa fa-forward"></i>
                		<spring:message code="nav.addSteps" text="Add steps" />
                	</a>
                </li>
                <li>
                	<a href="<spring:url value='/service/messages' htmlEscape='true' />"><i class="fa fa-comments-o"></i>
                		<spring:message code="nav.messages" text="Messages" />
                	</a>
                </li>
                <li>
                	<a href="gallery.html">
                		<i class="fa fa-photo"></i>
                		<spring:message code="nav.gallery" text="Gallery" />
                    </a>
				</li>
			</ul>
       	</li>
		<li><a href="<spring:url value='/service/twitter-login' htmlEscape='true' />"><i class="fa fa-cloud-upload"></i>
			<spring:message code="nav.connect" text="Connect" /></a>
		</li>
		<li><a href="<spring:url value='/service/myaccount' htmlEscape='true' />"><i class="fa fa-user"></i>
			<spring:message code="nav.account" text="Account" /></a>
		</li>
		<li><a href="<spring:url value='/j_spring_security_logout' htmlEscape='true' />">
			<i class="fa fa-sign-out"></i>
			<spring:message code="nav.logout" text="Logout" /></a>
		</li>
	</ul>

	<footer id="footer"> © 2015 GetSocial </footer>
</div>