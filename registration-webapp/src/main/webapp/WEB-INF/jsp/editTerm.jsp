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
		<form:form name="pageForm" commandName="term" method="post" action="/registration-webapp/editTerm.htm">
			<table class="formTable">
				<tr>
					<td>
						Name
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
				<c:forEach items="${term.exclusionDates}" var="exclusionDate" varStatus="listIndex">
					<tr>
						<td>
							Exclusion Date ${listIndex.index + 1}
						</td>
						<td>
							<fmt:formatDate value="${exclusionDate.exclusionDate}" pattern="dd MMM yyyy"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Save</button>
				<button onclick="document.location = 'addExclusionDates.htm?termId=${term.id}';return false;">Add Exclusion Dates</button>
				<c:choose>
					<c:when test="${term.lockTerm}">
						<button onclick="document.location = 'viewAttendances.htm?termId=${term.id}';return false;">Show Attendances</button>
					</c:when>
					<c:otherwise>
						<button onclick="document.location = 'generateAttendances.htm?termId=${term.id}';return false;">Generate Attendances</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>