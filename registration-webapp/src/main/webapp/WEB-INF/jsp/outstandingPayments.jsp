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
						<th nowrap="nowrap">
							Child Name
						</th>
						<th nowrap="nowrap">
							Current Balance
						</th>
						<th nowrap="nowrap">
							New Payment
						</th>
						<th>
							New Payment Date
						</th>
						<th nowrap="nowrap">
							Registration Fee Paid
						</th>
						<th nowrap="nowrap">
							Deposit Paid
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
								£<fmt:formatNumber value="${child.totalAmountDue}" minFractionDigits="2" maxFractionDigits="2"/>
							</td>
							<td>                   
								£<form:input path="formObject.newPayments[${listIndex.index}].amount" size="5" maxlength="11" cssClass="numeric"/>
								<form:select path="formObject.newPayments[${listIndex.index}].paymentType" items="${paymentList}" itemLabel="description"/>
								<form:input path="formObject.newPayments[${listIndex.index}].comments" size="40" maxlength="255"/>
							</td>	
							<td>
								<form:input path="formObject.newPayments[${listIndex.index}].datePaid" class="date"/>
							</td>
							<td>
								<form:checkbox path="formObject.children[${listIndex.index}].registrationFeePaid"/>
							</td>
							<td>
								<form:input path="formObject.children[${listIndex.index}].depositPaid" size="5" maxlength="11" cssClass="numeric"/>
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