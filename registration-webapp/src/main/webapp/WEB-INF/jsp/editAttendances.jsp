<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doAdd(){
				$("#addAttendances").val("Y");
				document.pageForm.submit();
			}
			
			function doDelete(){
				if(confirm("Are you sure you want to delete the selected attendances?")){
					document.pageForm.submit();
				}
			}
		</script>
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
				<c:forEach items="${form.newAttendances}" var="newAttendance" varStatus="listIndex">
					<spring:nestedPath path="newAttendances[${listIndex.index}]">
						<tr>
							<td>
							
							</td>
							<td>
							
							</td>
							<td>
								<form:input path="attendanceDate" class="date"/>
							</td>
							<td>
								<form:select path="typeOfAttendance" items="${attendanceTypes}" itemLabel="description"/>
							</td>
							<td>
								<form:select path="room" items="${rooms}" itemLabel="name" itemValue="id"/>
							</td>
						</tr>
					</spring:nestedPath>
				</c:forEach>
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
			<input type="hidden" name="addAttendances" id="addAttendances"/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doAdd();return false;">Add Attendances</button>
				<button onclick="doDelete();return false;">Delete</button>
			</div>
		</div>
	</body>
</html>