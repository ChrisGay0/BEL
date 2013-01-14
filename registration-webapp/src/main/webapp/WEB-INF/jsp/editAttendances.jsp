<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body style="width: 100%;">
		<h3>Edit Attendances</h3>		
		<form:form name="pageForm" commandName="form" method="post" action="/registration-webapp/editAttendances.htm">
			<table class="formTable">
				<tr>
					<td>
						Term
					</td>
					<td>
						${form.term.termName}
					</td>
				</tr>
				<tr>
					<td>
						Child
					</td>
					<td>
						${form.child.firstName} ${form.child.surname} 
					</td>
				</tr>
			</table>
			<table class="listTable">
				<thead>
					<tr>
						<th>
							Select
						</th>
						<th>
							Day
						</th>
						<th>
							Date
						</th>
						<th>
							Type of Attendance
						</th>
						<th>
							Room
						</th>
					</tr>
				</thead>
				<c:forEach items="${form.attendances}" var="attendance" varStatus="listIndex">
					<spring:nestedPath path="attendances[${listIndex.index}]">
						<tr>
							<td>
								<form:checkbox path="selected"/>
								<form:hidden path="id"/>
							</td>
							<td>
								<fmt:formatDate value="${attendance.attendanceDate}" pattern="EEEE"/>
							</td>
							<td>
								<fmt:formatDate value="${attendance.attendanceDate}" pattern="dd MMM yyyy"/>
							</td>
							<td>
								${attendance.typeOfAttendance.description}
							</td>
							<td>
								${attendance.room.name}
							</td>	
						</tr>
					</spring:nestedPath>
				</c:forEach>
			</table>
			<form:hidden path="child.id"/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Delete</button>
			</div>
		</div>
	</body>
</html>