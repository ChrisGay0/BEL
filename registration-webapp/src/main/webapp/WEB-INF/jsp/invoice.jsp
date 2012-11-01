<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				var roomIds = $("#rooms").val();
				window.open('/registration-webapp/invoice.pdf?termId=' + $("#term").val());
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Select the term to print invoices for</h3>		
		<form>
			<table class="formTable">
				<tr>
					<td>
						Term
					</td>
					<td>
						<select id="term">
							<c:forEach items="${terms}" var="term" varStatus="listIndex">
								<option value="${term.id}">
									${term.termName}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doPrint();return false;">Print Invoices</button>
			</div>
		</div>
	</body>
</html>