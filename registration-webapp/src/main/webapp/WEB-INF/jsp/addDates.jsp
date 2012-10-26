<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function addDates(){
				var counter = 0;
				$(".date").each(function(){
					parent.document.getElementById("newExclusionDates" + counter).value = $(this).val();
					counter++;
				});			
				
				parent.document.pageForm.submit();
				parent.GB_hide();
			}
		</script>
	</head>
	<body style="width: 100%;">
		<h3>Add Dates</h3>		
		<form name="pageForm">
			<table class="formTable">
				<tr>
					<td>
						<input type="text" id="newExclusionDates1" class="date"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="newExclusionDates2" class="date"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="newExclusionDates3" class="date"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="newExclusionDates4" class="date"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="newExclusionDates5" class="date"/>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center;">
			<button onclick="addDates();return false;">Save</button>
		</div>
	</body>
</html>