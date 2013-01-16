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
						<form:input path="userId" cssClass="mandatoryField"/>
					</td>
				</tr>
				<tr>
					<td>
						First name
					</td>
					<td>
						<form:input path="firstName" cssClass="mandatoryField"/>
					</td>
				</tr>
				<tr>
					<td>
						Surname
					</td>
					<td>
						<form:input path="surname" cssClass="mandatoryField"/>
					</td>
				</tr>
				<tr>
					<td>
						Password
					</td>
					<td>
						<form:password path="password" cssClass="mandatoryField"/>
					</td>
				</tr>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doSubmit(document.pageForm);return false;">Create</button>
			</div>
		</div>
	</body>
</html>