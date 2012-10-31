<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doSave(){
				if($("#newPassword").val() == $("#retypePassword").val()){
					document.pageForm.submit();
				}
				else{
					alert("New passwords do not match")
				}
			}
			
			$(document).ready(function() {
				if("${changed}" == "true"){
					alert("Password successfully changed");
				}
				else if("${changed}" == "false"){
					alert("Password not changed. Did you enter your current password correctly?");
				}
			});
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Change Password for ${user.firstName} ${user.surname}</h3>		
		<form name="pageForm" method="post" action="/registration-webapp/changePassword.htm">
			<table class="formTable">
				<tr>
					<td>
						Current Password
					</td>
					<td>
						<input type="password" name="currentPassword"/>
					</td>
				</tr>
				<tr>
					<td>
						New Password
					</td>
					<td>
						<input type="password" name="newPassword" id="newPassword"/>
					</td>
				</tr>
				<tr>
					<td>
						retypePassword
					</td>
					<td>
						<input type="password" name="retypePassword" id="retypePassword"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doSave();return false;" id="saveButton">Save</button>
			</div>
		</div>
	</body>
</html>