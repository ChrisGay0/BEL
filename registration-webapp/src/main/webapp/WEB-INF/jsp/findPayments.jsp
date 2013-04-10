<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				window.open('/registration-webapp/payments.pdf?dateFrom=' + $("#dateFrom").val() + '&dateTo=' + $("#dateTo").val());
			}
			
			function doSearch(){
				doSubmit(document.findPayments);
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<form method="POST" action="/registration-webapp/findPayments.htm" name="findPayments">
			<table class="formTable" style="width: 100%;">
				<tr>
					<td>
						Date From
					</td>
					<td>
						<input type="text" maxlength="11" class="date mandatoryField" value="${dateFrom}" name="dateFrom" id="dateFrom"/>
					</td>
					<td>
						Date To
					</td>
					<td>
						<input type="text" maxlength="11" class="date mandatoryField" value="${dateTo}" name="dateTo" id="dateTo"/>
					</td>
				</tr>
			</table>
		</form>
		<c:if test="${not empty paymentTypes}">
			<p style="text-align: center; font-size: 16px; font-weight: bold;">
				Overall Total: £<fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2"/>
			</p>
		</c:if>
		<div class="tabber">
			<c:forEach items="${paymentTypes}" var="paymentType" varStatus="listIndex">
				<div class="tabbertab" title="${paymentType} (£<fmt:formatNumber value="${totalMap[paymentType.stringValue]}" minFractionDigits="2" maxFractionDigits="2"/>)">
					<table class="listTable">
						<thead>
							<tr>
								<th>
									Date
								</th>
								<th>
									Child
								</th>
								<th>
									Amount
								</th>
								<th>
									Comments
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${paymentMap[paymentType.stringValue]}" var="payment">
								<tr>
									<td>
										<fmt:formatDate value="${payment.datePaid}" pattern="dd MMM yyyy HH:mm" />
									</td>
									<td onclick="document.location = 'editChild.htm?childId=${payment.child.id}'" style="cursor: pointer;">
										${payment.child.firstName} ${payment.child.surname}
									</td>
									<td>
										£${payment.amount}
									</td>
									<td>
										${payment.comments}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doPrint();return false;">Print</button>
				<button onclick="doSearch();return false;">Search</button>
			</div>
		</div>
	</body>
</html>