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
		<form name="pageForm" commandName="formObject" method="post" action="/registration-webapp/outstandingPayments.htm">
			<table class="listTable">
				<thead>
					<tr>
						<th>
							Child Name
						</th>
						<th>
							Current Balance
						</th>
						<th>
							New Payment
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${formObject.children}" var="child" varStatus="listIndex">
						<tr>
							<td>
								<a style="cursor: pointer;" onclick="document.location = 'editChild.htm?childId=${child.id}'">${child.firstName} ${child.surname}</a>
							</td>
							<td>
								£${child.totalAmountDue}
							</td>
							<td>                   
								£<form:input path="formObject.newPayments[${listIndex.index}].amount" size="5" maxlength="11" cssClass="numeric"/>
								<form:select path="formObject.newPayments[${listIndex.index}].paymentType" items="${paymentList}" itemLabel="description"/>
								<form:input path="formObject.newPayments[${listIndex.index}].comments" size="60" maxlength="255"/>
							</td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="buttonBar"> 
				<div id="holder">
					<button onclick="doSubmit(document.pageForm);return false;">Save</button>
				</div>
			</div>
		</form>
	</body>
</html>