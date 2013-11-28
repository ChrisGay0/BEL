<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doBulkLeave(){
				if(confirm("The selected children will be set to 'Left School'. \n Are you sure you want to contine?")){
					$("#bulkLeave").val("Y");
					document.bulkRoomForm.submit();
				}
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Bulk Room Change</h3>
		<form:form commandName="formObject" name="bulkRoomForm" action="/registration-webapp/bulkRoomChange.htm" method="POST">
			<c:if test="${not empty childrenMoved}">
				<h2>
					<c:choose>
						<c:when test="${childrenMoved == 1}">
							${childrenMoved} child successfully moved
						</c:when>
						<c:otherwise>
							${childrenMoved} children successfully moved
						</c:otherwise>
					</c:choose>
				</h2>
			</c:if>
			<table class="formTable" style="width: 100%">
				<tr>
					<td style="width: 15%">
						Current Room
					</td>
					<td style="width: 85%">
						<select name="roomId" id="roomId">
							<c:forEach items="${rooms}" var="room">
								<option value="${room.id}">
									${room.name}
								</option>
							</c:forEach>
						</select>
						<script type="text/javascript">
							document.getElementById("roomId").value = "${roomId}";
						</script>
					</td>
				</tr>
			</table>
			<c:choose>
				<c:when test="${not empty formObject.children}">
					<table class="formTable" style="width: 100%">
						<tr>
							<td style="width: 15%">
								New Room
							</td>
							<td style="width: 85%">
								<select name="newRoomId" id="newRoomId">
									<c:forEach items="${rooms}" var="room">
										<option value="${room.id}">
											${room.name}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table class="listTable">
						<thead>
							<tr>
								<th>
									Select
								</th>
								<th>
									Name
								</th>
								<th>
									Date of Birth
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formObject.children}" var="child" varStatus="listIndex">
								<spring:nestedPath path="children[${listIndex.index}]">
									<tr>
										<td>
											<form:checkbox path="selected"/>
										</td>
										<td>
											${child.firstName} ${child.surname}
										</td>
										<td>
											<fmt:formatDate value="${child.dateOfBirth}" pattern="dd MMM yyyy"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
			</c:choose>
			<input type="hidden" name="bulkLeave" id="bulkLeave"/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onClick="document.location = 'bulkRoomChange.htm?action=showChildren&roomId=' + $('#roomId').val();">Find Children</button>
				<c:if test="${not empty formObject.children}">
					<button onclick="document.bulkRoomForm.submit();return false;">Transfer</button>
					<button onclick="doBulkLeave();return false;">Bulk Leave</button>
				</c:if>
			</div>
		</div>
	</body>
</html>