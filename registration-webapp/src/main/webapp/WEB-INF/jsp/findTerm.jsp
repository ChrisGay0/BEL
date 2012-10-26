<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<table class="listTable">
			<thead>
				<tr>
					<th>
						Name
					</th>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${terms}" var="term">
					<tr>
						<td onclick="document.location = 'editTerm.htm?termId=${term.id}'" style="cursor: pointer;">
							${term.termName}
						</td>
						<td onclick="document.location = 'editTerm.htm?termId=${term.id}'" style="cursor: pointer;">
							<fmt:formatDate value="${term.startDate}" pattern="dd MMM yyyy"/>
						</td>
						<td onclick="document.location = 'editTerm.htm?termId=${term.id}'" style="cursor: pointer;">
							<fmt:formatDate value="${term.endDate}" pattern="dd MMM yyyy"/>
						</td>
						<td style="text-align: center;">
							<c:if test="${term.lockTerm}">
								<button onclick="GB_show('Attedances', '/registration-webapp/viewAttendances.htm?termId=${term.id}&hideHeader=Y', 600, 800);return false;">Show Attendances</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>