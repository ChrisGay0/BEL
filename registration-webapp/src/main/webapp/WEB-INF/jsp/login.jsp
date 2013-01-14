<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#breadcrumb").hide();
				$("#userId").focus();
			});
		</script>
		<style type="text/css">
			div{
				padding-top: 30px;
				text-align: center;
				margin: auto;
				width: 20%;
				font-size: 14px;
			}
			
			p{
				width: 100%;
				text-align: center;
				font-size: 16px;
				padding-top: 10px;
			}
		</style>
	</head>
	<body>
		<h1>${schoolName}</h1>
		<p>
			Please enter your username and password below.
		</p>
		<div>
			<form method="post" action="/registration-webapp/login.htm">	
				<table class="formTable">
					<tr>
						<td>
							Username
						</td>
						<td>
							<input type="text" name="userId" id="userId"/>
						</td>
					</tr>
					<tr>
						<td>
							Password
						</td>
						<td>
							<input type="password" name="password" />
						</td>
					</tr>
				</table>
				<input type="submit" value="Login">
			</form>
			<a class="url" href="/resetPassword.htm">
				Forgotten your password?
			</a>
		</div>
	</body>
</html>