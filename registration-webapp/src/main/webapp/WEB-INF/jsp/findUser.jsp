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
						Username
					</th>
					<th>
						Name
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr onclick="document.location = 'editUser.htm?userId=${user.userId}'" style="cursor: pointer;">
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