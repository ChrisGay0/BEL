<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Create New Room</h3>		
		<form:form name="pageForm" commandName="newRoom" method="post" action="/registration-webapp/createRoom.htm">
			<table class="formTable">
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
				<tr>
					<td colspan="2">
						Initial Cost (You can add more later)
					</td>
				</tr>
				<tr>
					<td>
						Age Under (The age this cost applies to)
					</td>
					<td>
						<form:input path="costs[0].childAgeUnder"/>
					</td>
				</tr>
				<tr>
					<td>
						Session Cost
					</td>
					<td>
						<form:input path="costs[0].cost"/>
					</td>
				</tr>
				<tr>
					<td>
						Lunch Cost
					</td>
					<td>
						<form:input path="costs[0].lunchCost"/>
					</td>
				</tr>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Create</button>
			</div>
		</div>
	</body>
</html>