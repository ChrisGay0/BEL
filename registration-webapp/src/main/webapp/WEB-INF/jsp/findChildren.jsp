<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<form:form commandName="searchBean" action="/registration-webapp/findChild.htm" method="POST">
			<table class="formTable">
				<tr>
					<td>
						First Name
					</td>
					<td>
						<form:input path="firstName"/>
					</td>
					<td>
						Surname
					</td>
					<td>
						<form:input path="surname"/>
					</td>
				</tr>
				<tr>
					<td>
						Room
					</td>
					<td>
						<form:select path="room">
							<form:option value="">All</form:option>
							<form:options items="${rooms}" itemValue="id"/>
						</form:select>
					</td>
					<td>
						Include those that have left
					</td>
					<td>
						<form:checkbox path="includeLeft"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Search"/>
		</form:form>
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
						Date of Birth
					</th>
					<th>
						Room
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${children}" var="child">
					<tr>
						<td>
							<button onclick="document.location = 'editChild.htm?childId=${child.id}'">View</button>
						</td>
						<td>
							${child.firstName} ${child.surname}
						</td>
						<td>
							<fmt:formatDate value="${child.dateOfBirth}" pattern="dd MMM yyyy"/>
						</td>
						<td>
							${child.room.name}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>