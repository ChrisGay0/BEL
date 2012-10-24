<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Create New Child</h3>		
		<form:form name="pageForm" commandName="newChild" method="post" action="/registration-webapp/createChild.htm">
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
						Date of Birth
					</td>
					<td>
						<form:input path="dateOfBirth" cssClass="date"/>
					</td>
				</tr>
				<tr>
					<td>
						Sex
					</td>
					<td>
						<form:select path="sex">
							<form:option value="Female">Female</form:option>
							<form:option value="Male">Male</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						Ethnicity
					</td>
					<td>
						<form:select path="ethnicity" items="${ethnicityList}" itemLabel="description"/>
					</td>
				</tr>
				<tr>
					<td>
						Deposit(£)
					</td>
					<td>
						<form:input path="depositPaid" size="4"/>
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
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Create</button>
			</div>
		</div>
	</body>
</html>