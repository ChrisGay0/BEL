<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Add Dates</h3>		
		<form:form name="pageForm" commandName="term" method="post" action="/registration-webapp/addExclusionDates.htm">
			<table class="formTable">
				<c:forEach items="${term.newExclusionDates}" var="newDate" varStatus="listIndex">
					<tr>
						<td>
							Date ${listIndex.index + 1}
						</td>
						<td>
							<form:input path="newExclusionDates[${listIndex.index}]" cssClass="date"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;">Save</button>
			</div>
		</div>
	</body>
</html>