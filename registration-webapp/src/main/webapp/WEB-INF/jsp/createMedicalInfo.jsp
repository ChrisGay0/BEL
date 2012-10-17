<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Add Medical Info</h3>		
		<form:form name="pageForm" commandName="newInfo" method="post" action="/registration-webapp/addMedicalInfo.htm">
			<table class="formTable">
				<tr>
					<td>
						Medical Condition 
					</td>
					<td>
						<form:input path="medicalCondition"/>
					</td>
				</tr>
				<tr>
					<td>
						Notes
					</td>
					<td>
						<form:textarea path="notes" rows="4" cols="60"/>
					</td>
				</tr>
			</table>
			<input type="hidden" name="childId" value="${childId}"/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Create</button>
			</div>
		</div>
	</body>
</html>