<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<style type="text/css">
			p{
				width: 30%;
				text-align: center;
				font-size: 20px;
				padding-top: 10px;
				border: 1px solid #008B00; 
				margin: auto;
				padding-bottom: 10px;
			}
		</style>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<table class="listTable">
			<thead>
				<tr>
					<th>
						Child
					</th>
					<th>
						Room
					</th>
					<th>
						Date
					</th>
					<th>
						Type of Attendance
					</th>
					<th>
						Term
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attendances}" var="attendance">
					<tr>
						<td>
							${attendance.child.firstName} ${attendance.child.surname}
						</td>
						<td>
							${attendance.room.name}
						</td>
						<td>
							<fmt:formatDate value="${attendance.attendanceDate}" pattern="dd MMM yyyy"/>
						</td>
						<td>
							${attendance.typeOfAttendance.description}
						</td>
						<td>
							${attendance.term.termName}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>