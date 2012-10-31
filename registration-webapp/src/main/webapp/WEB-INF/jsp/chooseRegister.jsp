<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				var roomIds = $("#rooms").val();
				window.open('/registration-webapp/register.pdf?day=' + $("#day").val() + "&roomIds=" + roomIds + "&daysToPrint=" + $("#daysToPrint").val());
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
						Week Starting
					</td>
					<td>
						<input type="text" id="day" class="date"/>
					</td>
					<td>
						Days to print
					</td>
					<td>
						<select id="daysToPrint">
							<option value="1">
								1
							</option>
							<option value="2">
								2
							</option>
							<option value="3">
								3
							</option>
							<option value="4">
								4
							</option>
							<option value="5" selected="selected">
								5
							</option>
						</select>
					</td>
				</tr>
				<tr valign="top">
					<td style="vertical-align: top;">
						Room(s)
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
				<button onclick="doPrint();return false;">Print Register(s)</button>
			</div>
		</div>
	</body>
</html>