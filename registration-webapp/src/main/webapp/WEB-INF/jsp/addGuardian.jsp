<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Add Guardian</h3>		
		<form:form name="pageForm" commandName="newGuardian" method="post" action="/registration-webapp/addGuardian.htm">
			<table class="formTable">
				<tr>
					<td>
						First Name
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
						Title
					</td>
					<td>
						<form:input path="title"/>
					</td>
				</tr>
				<tr>
					<td>
						Date of Birth
					</td>
					<td>
						<form:input path="dateOfBirth" cssClass="date"/>
					</td>
				</tr>
				<tr>
					<td>
						Relation
					</td>
					<td>
						<form:input path="relation"/>
					</td>
				</tr>
				<tr>
					<td>
						Contact Number 1
					</td>
					<td>
						<form:input path="contactNumber1"/>
					</td>
				</tr>
				<tr>
					<td>
						Contact Number 2
					</td>
					<td>
						<form:input path="contactNumber2"/>
					</td>
				</tr>
				<tr>
					<td>
						Allowed to Collect
					</td>
					<td>
						<form:checkbox path="allowedToCollect"/>
					</td>
				</tr>
				<tr>
					<td>
						Same Address as Child
					</td>
					<td>
						<form:checkbox path="sameAddressAsChild"/>
					</td>
				</tr>
				<tr>
					<td>
						Address Line 1
					</td>
					<td>
						<form:input path="addressLine1"/>
					</td>
				</tr>
				<tr>
					<td>
						Address Line 2
					</td>
					<td>
						<form:input path="addressLine2"/>
					</td>
				</tr>
				<tr>
					<td>
						Address Line 3
					</td>
					<td>
						<form:input path="addressLine3"/>
					</td>
				</tr>
				<tr>
					<td>
						City
					</td>
					<td>
						<form:input path="city"/>
					</td>
				</tr>
				<tr>
					<td>
						County
					</td>
					<td>
						<form:input path="county"/>
					</td>
				</tr>
				<tr>
					<td>
						Post Code
					</td>
					<td>
						<form:input path="postCode"/>
					</td>
				</tr>
			</table>
			<input type="hidden" name="childId" value="${childId}"/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Save</button>
			</div>
		</div>
	</body>
</html>