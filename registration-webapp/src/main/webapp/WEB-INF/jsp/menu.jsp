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
			
			#school, #child, #quickReports{
				float: left;	
				clear: left;			
			}
			
			#term, #system, #reportScreens{
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
				<li onclick="document.location = 'editSchool.htm'">
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
					Create Half Term
				</li>
				<li onclick="document.location = 'findTerm.htm'">
					Find Half Term
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
				<li onclick="document.location = 'changePassword.htm'">
					Change Password
				</li>
			</ul>
		</div>
		
		<div id="quickReports">
			<h2>Quick Reports</h2>
			<ul class="list">
				<li onclick="window.open('/registration-webapp/fullChildList.xls');">
					Full Child List
				</li>
				<li onclick="window.open('/registration-webapp/shortChildList.xls');">
					Short Child List
				</li>
				<li onclick="window.open('/registration-webapp/welcomeLetter.pdf');">
					Print Welcome Letters
				</li>
			</ul>
		</div>
		
		<div id="reportScreens">
			<h2>Report Screens</h2>
			<ul class="list">
				<li onclick="document.location = 'chooseRegister.htm'">
					Registers
				</li>
				<li onclick="document.location = 'chooseLunchRegister.htm'">
					Lunch Registers
				</li>
				<li onclick="document.location = 'invoice.htm'">
					Invoice
				</li>
				<li onclick="document.location = 'findPayments.htm'">
					Find Payments
				</li>
				<li onclick="document.location = 'outstandingPayments.htm'">
					Outstanding Payments
				</li>
				<li onclick="document.location = 'waitingList.htm'">
					Waiting List
				</li>
				<li onclick="document.location = 'childReport.htm'">
					Child Reports
				</li>
				<li onclick="document.location = 'showHours.htm'">
					Hours Between Dates
				</li>
			</ul>
		</div>
	</body>
</html>