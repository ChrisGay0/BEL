<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Edit Room</h3>		
		<form:form name="pageForm" commandName="room" method="post" action="/registration-webapp/editRoom.htm">
			<table class="formTable">
				<tr>
					<td>
						Room Id
					</td>
					<td>
						${room.id}
					</td>
				</tr>
				<tr>
					<td>
						Name
					</td>
					<td>
						<form:input path="name"/>
					</td>
				</tr>
				<tr>
					<td>
						Active
					</td>
					<td>
						<form:checkbox path="active"/>
					</td>
				</tr>
				<c:forEach items="${room.costs}" var="sessionCost" varStatus="listIndex">
					<tr>
						<td colspan="2">
							<hr/>
						</td>
					</tr>
					<spring:nestedPath path="costs[${listIndex.index}]">
						<tr>
							<td colspan="2">
								Cost ${listIndex.index + 1}
							</td>
						</tr>
						<tr>
							<td>
								Age Under (The age this cost applies to)
							</td>
							<td>
								<form:input path="childAgeUnder"/>
							</td>
						</tr>
						<tr>
							<td>
								Session Cost
							</td>
							<td>
								<form:input path="cost"/>
							</td>
						</tr>
						<tr>
							<td>
								Lunch Cost
							</td>
							<td>
								<form:input path="lunchCost"/>
							</td>
						</tr>
					</spring:nestedPath>
				</c:forEach>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Save</button>
			</div>
		</div>
	</body>
</html>