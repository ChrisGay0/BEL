<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function doPrint(){
				window.open('/registration-webapp/lunchRegister.pdf?day=' + $("#day").val() + "&daysToPrint=" + $("#daysToPrint").val());
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<h3>Choose Days To Print Lunch Register</h3>		
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
			</table>
		</form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="doPrint();return false;">Print Lunch Register(s)</button>
			</div>
		</div>
	</body>
</html>