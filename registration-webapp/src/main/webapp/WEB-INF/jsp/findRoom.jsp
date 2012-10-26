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
						Active
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rooms}" var="room">
					<tr onclick="document.location = 'editRoom.htm?roomId=${room.id}'" style="cursor: pointer;">
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