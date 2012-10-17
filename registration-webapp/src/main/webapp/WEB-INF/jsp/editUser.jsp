<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Edit User</h3>		
		<form:form name="pageForm" commandName="user" method="post" action="/registration-webapp/editUser.htm">
			<table class="formTable">
				<tr>
					<td>
						Username
					</td>
					<td>
						${user.userId}
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
				<button onclick="document.pageForm.submit();return false;">Save</button>
			</div>
		</div>
	</body>
</html>