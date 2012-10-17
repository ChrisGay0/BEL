<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				var roomIds = $("#rooms").val();
				window.open('/registration-webapp/register.pdf?day=' + $("#day").val() + "&roomIds=" + roomIds);
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Choose Register To Print</h3>		
		<form>
			<table class="formTable">
				<tr>
					<td>
						Register date
					</td>
					<td>
						<input type="text" id="day" class="date"/>
					</td>
				</tr>
				<tr valign="top">
					<td>
						<p style="vertical-align: top;">Room(s)</p>
					</td>
					<td>
						<select id="rooms" multiple="multiple" style="width: 200px;height: 100px;">
							<c:forEach items="${rooms}" var="room">
								<option value="${room.id}">
									${room.name}
								</option>
							</c:forEach>
						</select>
						<p style="vertical-align: top;">(Choose multiple rooms by holding the ctrl key)</p>
					</td>
				</tr>
			</table>
		</form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doPrint();return false;">Print Register</button>
			</div>
		</div>
	</body>
</html>