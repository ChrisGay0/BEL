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
						View
					</th>
					<th>
						Username
					</th>
					<th>
						Name
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>
							<button onclick="document.location = 'editUser.htm?userId=${user.userId}'">View</button>
						</td>
						<td>
							${user.userId}
						</td>
						<td>
							${user.firstName} ${user.surname}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>