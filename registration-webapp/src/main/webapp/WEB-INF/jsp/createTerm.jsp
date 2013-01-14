<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Create New Term</h3>		
		<form:form name="pageForm" commandName="newTerm" method="post" action="/registration-webapp/createTerm.htm">
			<table class="formTable">
				<tr>
					<td>
						Term Name
					</td>
					<td>
						<form:input path="termName"/>
					</td>
				</tr>
				<tr>
					<td>
						Start Date
					</td>
					<td>
						<form:input path="startDate" cssClass="date"/>
					</td>
				</tr>
				<tr>
					<td>
						End Date
					</td>
					<td>
						<form:input path="endDate" cssClass="date"/>
					</td>
				</tr>
				<tr>
					<td>
						Start Date of full term (Normally January, April or September)
					</td>
					<td>
						<form:input path="startDateOfFullTerm" cssClass="date"/>
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