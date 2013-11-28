<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Hours Between Dates</h3>		
		<form name="hoursForm" method="get" action="/registration-webapp/showHours.htm">
			<table style="width: 80%;">
				<tr>
					<td>
						Date From
					</td>
					<td>
						<input type="text" name="dateFrom" value="${dateFrom}" class="date mandatoryField">
					</td>
					<td>
						Date To
					</td>
					<td>
						<input type="text" name="dateTo" value="${dateTo}" class="date mandatoryField">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<hr/>
					</td>
				</tr>
				<c:if test="${not empty resultsMap}">
					<tr style="font-size: 14px;">
						<td>
							Total Hours
						</td>
						<td>
							${resultsMap['totalHours']}
						</td>
						<td>
							Funded Hours
						</td>
						<td>
							${resultsMap['fundedHours']}
						</td>
					</tr>
				</c:if>
			</table>
			<div id="buttonBar"> 
				<div id="holder">
					<button>Search</button>
				</div>
			</div>
		</form>
	</body>
</html>