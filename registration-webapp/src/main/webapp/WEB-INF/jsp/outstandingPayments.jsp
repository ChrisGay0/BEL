<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>	
		<%@ include file="headerIncludes.jspf" %>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Outstanding Payments</h3>		
		<form name="pageForm">
			<table class="listTable">
				<thead>
					<tr>
						<th>
							Child Name
						</th>
						<th>
							Current Balance
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${outstandingChildren}" var="child">
						<tr>
							<td>
								${child.firstName} ${child.surname}
							</td>
							<td>
								${child.currentBalance}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</body>
</html>