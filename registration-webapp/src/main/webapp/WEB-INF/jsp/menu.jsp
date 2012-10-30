<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<style type="text/css">
			div{
				width: 40%;
				padding: 3%
			}
			
			#school, #child, #reports{
				float: left;	
				clear: left;			
			}
			
			#term, #system{
				float: right;
				clear: right;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#breadcrumb").hide();
			});
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="school">
			<h2>School</h2>
			<ul class="list">
				<li>
					Edit School Details
				</li>
				<li onclick="document.location = 'createRoom.htm'">
					Create Room
				</li>
				<li onclick="document.location = 'findRoom.htm'">
					Find Room
				</li>
			</ul>
		</div>
		
		<div id="term">
			<h2>Terms</h2>
			<ul class="list">
				<li onclick="document.location = 'createTerm.htm'">
					Create Term
				</li>
				<li onclick="document.location = 'findTerm.htm'">
					Find Term
				</li>
			</ul>
		</div>
		
		<div id="child">
			<h2>Child</h2>
			<ul class="list">
				<li onclick="document.location = 'createChild.htm'">
					Create Child
				</li>
				<li onclick="document.location = 'findChild.htm'">
					Find Child
				</li>
				<li onclick="document.location = 'bulkRoomChange.htm'">
					Bulk Room Change
				</li>
			</ul>
		</div>
		
		<div id="system">
			<h2>System</h2>
			<ul class="list">
				<li onclick="document.location = 'createUser.htm'">
					Create User
				</li>
				<li onclick="document.location = 'findUser.htm'">
					Find User
				</li>
				<li>
					Change Password
				</li>
			</ul>
		</div>
		
		<div id="reports">
			<h2>Reports</h2>
			<ul class="list">
				<li onclick="document.location = 'chooseRegister.htm'">
					Registers
				</li>
				<li onclick="window.open('/registration-webapp/invoice.pdf?termId=109');">
					Billing
				</li>
			</ul>
		</div>
	</body>
</html>