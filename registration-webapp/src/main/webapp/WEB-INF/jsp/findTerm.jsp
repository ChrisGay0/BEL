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
						Attendances
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${terms}" var="term">
					<tr onclick="document.location = 'editTerm.htm?termId=${term.id}'" style="cursor: pointer;">
						<td>
							${term.termName}
						</td>
						<td>
							<fmt:formatDate value="${term.startDate}" pattern="dd MMM yyyy"/>
						</td>
						<td>
							<fmt:formatDate value="${term.endDate}" pattern="dd MMM yyyy"/>
						</td>
						<td style="text-align: center;">
							<button onclick="document.location = 'viewAttendances.htm?termId=${term.id}';return false;">Show</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>