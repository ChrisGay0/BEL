<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Create User</h3>		
		<form:form name="pageForm" commandName="newUser" method="post" action="/registration-webapp/createUser.htm">
			<table class="formTable">
				<tr>
					<td>
						Username
					</td>
					<td>
						<form:input path="userId"/>
					</td>
				</tr>
				<tr>
					<td>
						First name
					</td>
					<td>
						<form:input path="firstName"/>
					</td>
				</tr>
				<tr>
					<td>
						Surname
					</td>
					<td>
						<form:input path="surname"/>
					</td>
				</tr>
				<tr>
					<td>
						Password
					</td>
					<td>
						<form:password path="password"/>
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