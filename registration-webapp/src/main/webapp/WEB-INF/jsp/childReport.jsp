<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				var rooms = $("#rooms").val();
				var includeDays = $("#days").attr("checked");
				var includeGuardians = $("#guardians").attr("checked");
				var includeContacts = $("#contacts").attr("checked");
				var includeMedical = $("#medical").attr("checked");
				var includeAddress = $("#address").attr("checked");
				
				window.open('/registration-webapp/childList.pdf?rooms=' + rooms + "&includeDays=" + includeDays + "&includeGuardians=" + includeGuardians + "&includeContacts=" + includeContacts + "&includeMedical=" + includeMedical + "&includeAddress=" + includeAddress);
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Child Reporting</h3>		
		<form>
			<table class="formTable">
				<tr>
					<td style="vertical-align: top;">
						Room(s)
					</td>
					<td>
						<select id="rooms" multiple="multiple">
							<c:forEach items="${rooms}" var="room">
								<option value="${room.id}">
									${room.name}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						Include child address
					</td>
					<td>
						<input type="checkbox" id="address"/>
					</td>
				</tr>
				<tr>
					<td>
						Include days child attends
					</td>
					<td>
						<input type="checkbox" id="days"/>
					</td>
				</tr>
				<tr>
					<td>
						Include Guardians
					</td>
					<td>
						<input type="checkbox" id="guardians"/>
					</td>
				</tr>
				<tr>
					<td>
						Include Contacts
					</td>
					<td>
						<input type="checkbox" id="contacts"/>
					</td>
				</tr>
				<tr>
					<td>
						Include Medical
					</td>
					<td>
						<input type="checkbox" id="medical"/>
					</td>
					<td>
				</tr>
			</table>
		</form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doPrint();return false;">Print Report</button>
			</div>
		</div>
	</body>
</html>