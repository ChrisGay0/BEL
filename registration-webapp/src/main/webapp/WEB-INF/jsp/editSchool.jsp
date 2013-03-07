<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Edit School</h3>		
		<form:form name="pageForm" commandName="school" method="post" action="/registration-webapp/editSchool.htm">
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
						Managers name
					</td>
					<td>
						<form:input path="manager"/>
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
				<tr>
					<td>
						Contact Number
					</td>
					<td>
						<form:input path="contactNumber"/>
					</td>
				</tr>
				<tr>
					<td>
						Email
					</td>
					<td>
						<form:input path="email"/>
					</td>
				</tr>
				<tr>
					<td>
						Charity Number
					</td>
					<td>
						<form:input path="charityNumber"/>
					</td>
				</tr>
				<tr>
					<td>
						Child Deposit Required
					</td>
					<td>
						£<form:input path="depositAmount" cssClass="numeric"/>
					</td>
				</tr>
				<tr>
					<td>
						Registration Fee
					</td>
					<td>
						£<form:input path="registrationFee" cssClass="numeric"/>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">
						Invoice Terms and Conditions
					</td>
					<td>
						<form:textarea path="invoiceTerms" rows="6" cols="60"/>
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