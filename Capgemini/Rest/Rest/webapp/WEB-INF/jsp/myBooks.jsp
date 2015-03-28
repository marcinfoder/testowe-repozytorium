<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="search.books" text="books" /></title>
</head>
<body>
<p><spring:message code="search.author" text="author" /> : ${searchForm.author}</p>
<p><spring:message code="search.title" text="title" /> : ${searchForm.title}</p>
<p>${books.owner}</p>
<table border=1>	
	<thead><tr>
		<th>ID</th>
		<th><spring:message code="search.title" text="title" /></th>
		<th><spring:message code="search.author" text="author" /></th>
		<th><spring:message code="search.address" text="address" /></th>
		<th><spring:message code="search.info" text="info" /></th>
	</tr></thead>
	<c:forEach var="book" items="${books.books}">
	<tr>
		<td>
			<spring:url value="/service/showBook/{bookId}" var="bookUrl">
              <spring:param name="bookId" value="${book.id}"/>
          	</spring:url>
          	<a href="${fn:escapeXml(bookUrl)}">${book.id}</a>
		</td>
		<td>
			<a href='#' onclick="showBook(${book.id}, '${book.title}');">${book.title}</a>
		</td>
		<td>${book.author}</td>
		<td>
			<c:if test="${book.author == 'Zygmunt Kowalski'}">
				:-)
			</c:if>
			&nbsp;
		</td>
		<td>${book.authorAddress}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5">With constraint</td>
	</tr>
	<c:forEach var="book" items="${constrainedBooks}">
	<tr>
		<td>${book.id}</td>
		<td>${book.title}</td>
		<td>${book.author}</td>		
		<td>&nbsp;</td>
		<td>${book.authorAddress}</td>
	</tr>
	</c:forEach>
</table>
<p>
<input id = "selectedBookId" type="text" value=""/>
</p>
<p/>
<br/>
<sec:authorize access="isAuthenticated()">
	<a href="<spring:url value="/service/search" htmlEscape="true" />">Back</a>
</sec:authorize>
<br/>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script type="text/javascript">

function showBook(id, title) {
	document.getElementById('selectedBookId').value = title;	
}

</script>

</body>
</html>