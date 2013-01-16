<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#searchNav").css("visibility", "visible").click(function(){
					document.location = 'findUser.htm';
				});
			});
		</script>
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
				<button onclick="doSubmit(document.pageForm);return false;">Save</button>
			</div>
		</div>
	</body>
</html>