<script type="text/javascript" src="/registration-webapp/greybox/greybox.js"></script>
<link href="/registration-webapp/greybox/greybox.css" rel="stylesheet" type="text/css" media="all" />

<h1>Burwell Early Learners</h1>
<div id="breadcrumb">
	<ul class="navBar">
		<li onclick="document.location = 'menu.htm'">
			<< Return to Menu
		</li>
	</ul>
</div>

<script>
	function cloneRow(rowId, index){
		if($("#" + rowId + parseInt(index) + 1).length == 0){
			var $tr    = $("#" + rowId + index);
			var $clone = $tr.clone();
			var newId = $clone.attr("id").replace(index, parseInt(index + 1));
			$clone.attr("id", newId);
			$clone.find('img').remove();
			$clone.find('.deleteColumn').append('<img src="/registration-webapp/images/remove.png" style="cursor: pointer;" height="24px" width="24px" onclick="removeRow(\'' + newId + '\');"/>');
			$clone.find('input[type=text], textarea').each(function(){
				var id = $(this).attr("id");
				id = id.replace(index, parseInt(index + 1));
				var name = $(this).attr("id");
				name = name.replace(index, "[" + parseInt(index + 1) + "]");
				$(this).val("");
				$(this).attr("id", id);
				$(this).attr("name", name);
				$(this).attr("onchange", "");
				if($(this).attr("class").indexOf("date") != -1){
					$(this).datepick({showOn:'focus'});
				}
				$(this).bind('change',function(){
					cloneRow(rowId, index + 1);
				});
			});
			$tr.find('input[type=text], textarea').each(function(){
				$(this).attr("onchange", "");
				$(this).unbind("change");
			});
			
			$tr.after($clone);
		}
	}
</script>