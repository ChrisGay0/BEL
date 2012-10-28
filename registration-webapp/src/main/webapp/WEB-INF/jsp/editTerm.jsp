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
				<tr>
					<td colspan="2">
						<table class="listTable" style="width: 100%">
							<thead>
								<tr>
									<th style="width: 10%">
										Delete
									</th>
									<th style="width: 30%">
										Exclusion Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</th>
									<th style="width: 50%">
										Reason
									</th>
									<th style="width: 10%">
										Chargeable
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${formObject.newDates}" var="newDate" varStatus="listIndex">
									<spring:nestedPath path="newDates[${listIndex.index}]">
										<tr id="date${listIndex.index}" >
											<td style="vertical-align: top; text-align: center;" class="deleteColumn">

											</td>
											<td style="vertical-align: top;">
												<form:input path="exclusionDate" cssClass="date" onchange="cloneRow('date', ${listIndex.index});"/>
											</td>
											<td>
												<form:input path="reason" size="80"/>
											</td>
											<td>
												<form:checkbox path="chargeable"/>
											</td>
										</tr>
									</spring:nestedPath>
								</c:forEach>
								<c:forEach items="${formObject.term.exclusionDates}" var="exclusionDate" varStatus="listIndex">
									<tr>
										<td style="text-align: center;">
											<form:checkbox path="term.exclusionDates[${listIndex.index}].selected"/>
										</td>
										<td>
											<fmt:formatDate value="${exclusionDate.exclusionDate}" pattern="dd MMM yyyy"/>
										</td>
										<td>
											<form:input path="term.exclusionDates[${listIndex.index}].reason" size="80" maxlength="255"/>
										</td>
										<td>
											<form:checkbox path="term.exclusionDates[${listIndex.index}].chargeable"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.submit();return false;" id="saveButton">Save</button>
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