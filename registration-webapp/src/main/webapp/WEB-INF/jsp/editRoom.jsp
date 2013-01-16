<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#searchNav").css("visibility", "visible").click(function(){
					document.location = 'findRoom.htm';
				});
			});
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>Edit Room</h3>		
		<form:form name="pageForm" commandName="room" method="post" action="/registration-webapp/editRoom.htm">
			<table class="formTable">
				<tr>
					<td>
						Room Id
					</td>
					<td>
						${room.id}
					</td>
				</tr>
				<tr>
					<td>
						Name
					</td>
					<td>
						<form:input path="name" cssClass="mandatoryField"/>
					</td>
				</tr>
				<tr>
					<td>
						Active
					</td>
					<td>
						<form:checkbox path="active"/>
					</td>
				</tr>
			</table>
			<table class="listTable">
				<thead>
					<tr>
						<th>
							Age Under
						</th>
						<th>
							Session Cost
						</th>
						<th>
							Lunch Cost
						</th>
					</tr>
				</thead>
				<c:forEach items="${room.costs}" var="sessionCost" varStatus="listIndex">
					<spring:nestedPath path="costs[${listIndex.index}]">
						<tr>
							<td>
								<form:input path="childAgeUnder" size="3"/>
							</td>
							<td>
								£<form:input path="cost" cssClass="numeric" size="5"/>
							</td>
							<td>
								£<form:input path="lunchCost" cssClass="numeric" size="5"/>
							</td>
						</tr>
					</spring:nestedPath>
				</c:forEach>
			</table>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doSubmit(document.pageForm);return false;">Save</button>
			</div>
		</div>
	</body>
</html>