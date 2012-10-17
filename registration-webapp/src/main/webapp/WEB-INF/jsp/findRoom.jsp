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
						Name
					</th>
					<th>
						Active
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rooms}" var="room">
					<tr>
						<td>
							<button onclick="document.location = 'editRoom.htm?roomId=${room.id}'">View</button>
						</td>
						<td>
							${room.name}
						</td>
						<td>
							<c:choose>
								<c:when test="${room.active}">
									Yes
								</c:when>
								<c:otherwise>
									No
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>