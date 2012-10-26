<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Edit Term</h3>		
		<form:form name="pageForm" commandName="formObject" method="post" action="/registration-webapp/editTerm.htm">
			<table class="formTable">
				<tr>
					<td>
						Name
					</td>
					<td>
						<form:input path="term.termName"/>
					</td>
				</tr>
				<tr>
					<td>
						Start Date
					</td>
					<td>
						<form:input path="term.startDate" cssClass="date"/>
					</td>
				</tr>
				<tr>
					<td>
						End Date
					</td>
					<td>
						<form:input path="term.endDate" cssClass="date"/>
					</td>
				</tr>
				<c:forEach items="${formObject.term.exclusionDates}" var="exclusionDate" varStatus="listIndex">
					<tr>
						<td>
							Exclusion Date ${listIndex.index + 1}
						</td>
						<td>
							<fmt:formatDate value="${exclusionDate.exclusionDate}" pattern="dd MMM yyyy"/>
						</td>
					</tr>
				</c:forEach>
				
				<c:forEach items="${formObject.newExclusionDates}" var="newExclusionDate" varStatus="listIndex">
					<form:hidden path="newExclusionDates[${listIndex.index}]"/>
				</c:forEach>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;" id="saveButton">Save</button>
				<button onclick="GB_show('Exclusion Dates', '/registration-webapp/addExclusionDates.htm?termId=${formObject.term.id}', 600, 800);return false;">Add Exclusion Dates</button>
				<c:choose>
					<c:when test="${formObject.term.lockTerm}">
						<button onclick="GB_show('Attendances', '/registration-webapp/viewAttendances.htm?termId=${formObject.term.id}&hideHeader=Y', 600, 800);return false;">Show Attendances</button>
						<button onclick="document.location = 'generateAttendances.htm?redo=Y&termId=${formObject.term.id}';return false;">Redo Attendances</button>
					</c:when>
					<c:otherwise>
						<button onclick="document.location = 'generateAttendances.htm?termId=${formObject.term.id}';return false;">Generate Attendances</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>