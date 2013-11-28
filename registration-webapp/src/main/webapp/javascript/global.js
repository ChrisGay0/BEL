function validateDate(){
	var error = false;
	$(".date").each(function(){
		if($(this).val() != ""){
			var splitDate = $(this).val().split("/");
			if(splitDate.length > 1){
				if(splitDate.length == 3){
					if(splitDate[0].length == 2 && splitDate[1].length == 2 && (splitDate[2].length == 2 || splitDate[2].length == 4)){
						if(splitDate[0] > "00" && splitDate[0] < "32"){
							if(splitDate[1] > "00" && splitDate[1] < "13"){
								//all ok
							}
							else{
								error = true;
							}
						}
						else{
							error = true;
						}
					}
					else{
						error = true;
					}
				}
				else{
					error = true;
				}
			}
			else{
				splitDate = $(this).val().split(" ");
				
				if(splitDate.length == 3){
					if(splitDate[0].length == 2 && splitDate[1].length == 3 && (splitDate[2].length == 2 | splitDate[2].length == 4)){
						if(splitDate[0] > "00" && splitDate[0] < "32"){
							if(splitDate[1].toUpperCase() == "JAN" || splitDate[1].toUpperCase() == "FEB" || splitDate[1].toUpperCase() == "MAR" || splitDate[1].toUpperCase() == "APR" || splitDate[1].toUpperCase() == "MAY" || splitDate[1].toUpperCase() == "JUN" || splitDate[1].toUpperCase() == "JUL" || splitDate[1].toUpperCase() == "AUG" || splitDate[1].toUpperCase() == "SEP" || splitDate[1].toUpperCase() == "OCT" || splitDate[1].toUpperCase() == "NOV" || splitDate[1].toUpperCase() == "DEC"){
								//all ok
							}
							else{
								error = true;
							}
						}
						else{
							error = true;
						}
					}
					else{
						error = true;
					}
				}
				else{
					error = true;
				}
			}
			
			if(error){
				$(this).after("<p class='error'>This is not a valid date, use the following formats 01/01/2001 or 01 Jan 2001</p>");
			}
		}
	});
	
	return !error;
}

function checkMadatoryFields(){
	var errors = false;
	$(".mandatoryField").each(function(){
		if($(this).val() == ""){
			errors = true;
			$(this).after("<p class='error'>This field cannot be blank</p>");
		}
	});
	
	return !errors;
}

function cloneRow(rowId, index){
	if($("#" + rowId + parseInt(index) + 1).length == 0){
		var $tr    = $("#" + rowId + index);
		var $clone = $tr.clone();
		var newId = $clone.attr("id").replace(index, parseInt(index + 1));
		$clone.attr("id", newId);
		$clone.find('img').remove();
		$clone.find('.deleteColumn').append('<img src="/registration-webapp/images/remove.png" style="cursor: pointer;" height="24px" width="24px" onclick="removeRow(\'' + newId + '\');"/>');
		var lastName = "";
		$clone.find('input, select, textarea').each(function(){
			var id = $(this).attr("id");
			id = id.replace(index, parseInt(index + 1));
			var name = $(this).attr("id");
			name = name.replace(index, "[" + parseInt(index + 1) + "]");
			if(name == "" && $(this).val() == "on"){
				name = "_" + lastName;
				$(this).val("on");
			}
			else{
				if($(this).attr("type") == "checkbox"){
					name = name.substring(0, name.length - 1);
					$(this).val("true");
				}
				else{
					$(this).val("");
				}
			}
			lastName = name;
			
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
		$tr.find('input, textarea').each(function(){
			$(this).attr("onchange", "");
			$(this).unbind("change");
		});
		
		$tr.after($clone);
	}
}

function doSubmit(form){
	$(".error").remove();
	var errors = false;
	errors = !validateDate();
	errors = checkMadatoryFields() ? errors : true;
	$(".numeric").each(function(){
		if (isNaN($(this).val())){
			$(this).after("<p class='error'>This is not a valid number, for financial values please do not include the £ sign</p>")
			errors = true;
		}
	});
	
	if(!errors){
		$(document.body).css("display", "none");
		form.submit();
	}
	else{
		$(document.body).find("h3").after("<p class='mainError error'>You have an error, please check all tabs to see where it is</p>");
	}
}