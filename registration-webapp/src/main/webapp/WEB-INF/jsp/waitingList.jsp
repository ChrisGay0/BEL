<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Waiting List</h3>	
		<div class="tabber">
			<c:forEach items="${rooms}" var="room" varStatus="listIndex">
				<div class="tabbertab" title="${room.name}">
					<button style="float: right;" onclick="window.open('/registration-webapp/waitingList.pdf?roomId=${room.id}');">Print ${room.name}</button>
					<table class="listTable" style="width: 99%;">
						<thead>
							<tr>
								<th>
									Child
								</th>
								<th>
									Start Date
								</th>
								<th>
									Registration Date
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roomMap[room.name]}" var="child">
								<tr onclick="document.location = 'editChild.htm?childId=${child.id}'" style="cursor: pointer;">
									<td>
										${child.firstName}
									</td>	
									<td>
										<fmt:formatDate value="${child.registeredDate}" pattern="dd MMM yyyy"/>
									</td>
									<td>
										<fmt:formatDate value="${child.requestedStartDate}" pattern="dd MMM yyyy"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
	</body>
</html>
