<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrintAll(){
				window.open('/registration-webapp/waitingList.pdf?roomId=&termId=${termId}');
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Waiting List</h3>	
		<p style="display: inline;">
			Term 
		</p>
		<p>
			<form name="waitingListForm" method="get" action="/registration-webapp/waitingList.htm">
				<select name="termId" id="termId">
					<option value="">
					</option>
					<c:forEach items="${terms}" var="term">
						<option value="${term.id}">
							${term.termName}
						</option>
					</c:forEach>
				</select>
				<button>Search</button>
			</form>
			<script type="text/javascript">
				$("#termId").val("${termId}");
			</script>
		</p>
		<div class="tabber">
			<c:forEach items="${rooms}" var="room" varStatus="listIndex">
				<div class="tabbertab" title="${room.name}">
					<button style="float: right;" onclick="window.open('/registration-webapp/waitingList.pdf?roomId=${room.id}&termId=${termId}');">Print ${room.name}</button>
					<table class="listTable" style="width: 99%;">
						<thead>
							<tr>
								<th>
									Child
								</th>
								<th>
									Start Date
								</th>
								<th>
									Registration Date
								</th>
								<th>
									Guardians
								</th>
								<th>
									Telephone
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roomMap[room.name]}" var="child">
								<tr onclick="document.location = 'editChild.htm?childId=${child.id}'" style="cursor: pointer;">
									<td>
										${child.firstName} ${child.surname}
									</td>	
									<td>
										<fmt:formatDate value="${child.requestedStartDate}" pattern="dd MMM yyyy"/>
									</td>
									<td>
										<fmt:formatDate value="${child.registeredDate}" pattern="dd MMM yyyy"/>
									</td>
									<td>
										${child.guardianTitleAndSurnames}
									</td>
									<td>
										${child.contactNumbers}
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
				<button onclick="doPrintAll();return false;">Print All Rooms</button>
			</div>
		</div>
	</body>
</html>
