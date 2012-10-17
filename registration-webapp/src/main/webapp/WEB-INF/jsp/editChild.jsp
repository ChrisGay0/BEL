<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			function removeRow(image){
				$(image).previos().previous().remove();
			}
			function cloneRow(rowId, index){
				if($("#" + rowId + parseInt(index) + 1).length == 0){
					var $tr    = $("#" + rowId + index);
					var $clone = $tr.clone();
					$clone.attr("id", $clone.attr("id").replace(index, parseInt(index + 1)));
					$clone.find('input[type=text], textarea').each(function(){
						var id = $(this).attr("id");
						id = id.replace(index, parseInt(index + 1));
						var name = $(this).attr("id");
						name = name.replace(index, "[" + parseInt(index + 1) + "]");
						$(this).val("");
						$(this).attr("id", id);
						$(this).attr("name", name);
						$(this).attr("onchange", "");
						$(this).bind('change',function(){
							cloneRow(rowId, index + 1);
						});
					});
					$tr.find('input[type=text], textarea').each(function(){
						$(this).attr("onchange", "");
					});
					
					$tr.after($clone);
				}
			}
		</script>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<br/>
		<h3>
			Edit Child
		</h3>
		<form:form name="pageForm" commandName="formObject" method="post" action="/registration-webapp/editChild.htm">
			<div class="tabber">
				<div class="tabbertab" title="Gerneral">
					<table class="formTable">
						<tr>
							<td>
								Child Id
							</td>
							<td>
								${formObject.child.id}
							</td>
						</tr>
						<tr>
							<td>
								Left school
							</td>
							<td>
								<form:checkbox path="child.leftSchool"/>
							</td>
						</tr>
						<tr>
							<td>
								First Name
							</td>
							<td>
								<form:input path="child.firstName"/>
							</td>
						</tr>
						<tr>
							<td>
								Surname
							</td>
							<td>
								<form:input path="child.surname"/>
							</td>
						</tr>
						<tr>
							<td>
								Date of Birth
							</td>
							<td>
								<form:input path="child.dateOfBirth" cssClass="date"/>
							</td>
						</tr>
						<tr>
							<td>
								Sex
							</td>
							<td>
								<form:select path="child.sex">
									<form:option value="Female">Female</form:option>
									<form:option value="Male">Male</form:option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								Ethnicity
							</td>
							<td>
								<form:input path="child.ethnicity"/>
							</td>
						</tr>
						<tr>
							<td>
								Address Line 1
							</td>
							<td>
								<form:input path="child.addressLine1"/>
							</td>
						</tr>
						<tr>
							<td>
								Address Line 2
							</td>
							<td>
								<form:input path="child.addressLine2"/>
							</td>
						</tr>
						<tr>
							<td>
								Address Line 3
							</td>
							<td>
								<form:input path="child.addressLine3"/>
							</td>
						</tr>
						<tr>
							<td>
								City
							</td>
							<td>
								<form:input path="child.city"/>
							</td>
						</tr>
						<tr>
							<td>
								County
							</td>
							<td>
								<form:input path="child.county"/>
							</td>
						</tr>
						<tr>
							<td>
								Post Code
							</td>
							<td>
								<form:input path="child.postCode"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabbertab" title="Attendance">
					<table class="formTable">
						<tr>
							<td>
								Room
							</td>
							<td>
								<form:select path="child.room" items="${rooms}" itemValue="id"/>
							</td>
						</tr>
						<tr>
							<td>
								Monday
							</td>
							<td>
								<form:select path="child.mondayAttendance">
									<form:option value=""></form:option>
									<form:options items="${typeOfAttendances}" itemLabel="description"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								Tuesday
							</td>
							<td>
								<form:select path="child.tuesdayAttendance">
									<form:option value=""></form:option>
									<form:options items="${typeOfAttendances}" itemLabel="description"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								Wednesday
							</td>
							<td>
								<form:select path="child.wednesdayAttendance">
									<form:option value=""></form:option>
									<form:options items="${typeOfAttendances}" itemLabel="description"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								Thursday
							</td>
							<td>
								<form:select path="child.thursdayAttendance">
									<form:option value=""></form:option>
									<form:options items="${typeOfAttendances}" itemLabel="description"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								Friday
							</td>
							<td>
								<form:select path="child.fridayAttendance">
									<form:option value=""></form:option>
									<form:options items="${typeOfAttendances}" itemLabel="description"/>
								</form:select>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabbertab" title="Medical">
					<table class="formTable">
						<tr>
							<td>
								Doctor
							</td>
							<td>
								<form:input path="child.doctorsName"/>
							</td>
						</tr>
						<tr>
							<td>
								Doctors Contact Number
							</td>
							<td>
								<form:input path="child.doctorsContactNumber"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="tabber">
									<div class="tabbertab" title="Conditions">
										<table>
											<c:forEach items="${formObject.child.medicalInfo}" var="medicalInfo" varStatus="listIndex">
												<spring:nestedPath path="child.medicalInfo[${listIndex.index}]">
													<tr>
														<td>
															Medical Condition
														</td>
														<td>
															<form:input path="medicalCondition"/>
														</td>
													</tr>
													<tr>
														<td valign="top">
															Notes
														</td>
														<td>
															<form:textarea path="notes" rows="4" cols="60"/>
														</td>
													</tr>
												</spring:nestedPath>
											</c:forEach>
										</table>
									</div>
									<div class="tabbertab" title="Intolerances">
										<table class="listTable">
											<thead>
												<tr>
													<th>
														Delete
													</th>
													<th>
														Intolerance
													</th>
													<th>
														Action
													</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${formObject.newIntolerances}" var="newIntolerance" varStatus="listIndex">
													<spring:nestedPath path="newIntolerances[${listIndex.index}]">
														<tr id="intolerance${listIndex.index}">
															<td>
																<img src="/registration-webapp/images/remove.png" height="16px" width="16px" onclick="removeRow(this);"/>
															</td>
															<td>
																<form:input path="intolerance" onchange="cloneRow('intolerance', ${listIndex.index});"/>
															</td>
															<td>
																<form:textarea path="precaution" rows="4" cols="60" onchange="cloneRow('intolerance', ${listIndex.index});"/>
															</td>
														</tr>
													</spring:nestedPath>
												</c:forEach>
											</tbody>
											<tbody>
												<c:forEach items="${formObject.child.intolerances}" var="intolerance" varStatus="listIndex">
													<spring:nestedPath path="child.intolerances[${listIndex.index}]">
														<tr valign="top">
															<td>
																<form:checkbox path="selected"/> 
															</td>
															<td>
																<form:input path="intolerance"/>
															</td>
															<td>
																<form:textarea path="precaution" rows="4" cols="60"/>
															</td>
													</spring:nestedPath>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabbertab" title="Guardians">
					<div class="tabber">
						<c:forEach items="${formObject.child.guardians}" var="guardian" varStatus="listIndex">
							<spring:nestedPath path="child.guardians[${listIndex.index}]">
								<div class="tabbertab" title="${guardian.firstName} ${guardian.surname}">
									<table class="formTable">
										<tr>
											<td>
												FirstName
											</td>
											<td>
												<form:input path="firstName"/>
											</td>
										</tr>
										<tr>
											<td>
												Surname
											</td>
											<td>
												<form:input path="surname"/>
											</td>
										</tr>
										<tr>
											<td>
												Title
											</td>
											<td>
												<form:input path="title"/>
											</td>
										</tr>
										<tr>
											<td>
												Date of Birth
											</td>
											<td>
												<form:input path="dateOfBirth" cssClass="date"/>
											</td>
										</tr>
										<tr>
											<td>
												Relation
											</td>
											<td>
												<form:input path="relation"/>
											</td>
										</tr>
										<tr>
											<td>
												Contact Number 1
											</td>
											<td>
												<form:input path="contactNumber1"/>
											</td>
										</tr>
										<tr>
											<td>
												Contact Number 2
											</td>
											<td>
												<form:input path="contactNumber2"/>
											</td>
										</tr>
										<tr>
											<td>
												Allowed to Collect
											</td>
											<td>
												<form:checkbox path="allowedToCollect"/>
											</td>
										</tr>
										<tr>
											<td>
												Same Address as Child
											</td>
											<td>
												<form:checkbox path="sameAddressAsChild"/>
											</td>
										</tr>
										<tr>
											<td>
												Address Line 1
											</td>
											<td>
												<form:input path="addressLine1"/>
											</td>
										</tr>
										<tr>
											<td>
												Address Line 2
											</td>
											<td>
												<form:input path="addressLine2"/>
											</td>
										</tr>
										<tr>
											<td>
												Address Line 3
											</td>
											<td>
												<form:input path="addressLine3"/>
											</td>
										</tr>
										<tr>
											<td>
												City
											</td>
											<td>
												<form:input path="city"/>
											</td>
										</tr>
										<tr>
											<td>
												County
											</td>
											<td>
												<form:input path="county"/>
											</td>
										</tr>
										<tr>
											<td>
												Post Code
											</td>
											<td>
												<form:input path="postCode"/>
											</td>
										</tr>
									</table>
								</div>
							</spring:nestedPath>
						</c:forEach>
					</div>
				</div>
				<div class="tabbertab" title="Financial">
					<table class="formTable">
						<tr>
							<td>
								Registration Fee Paid
							</td>
							<td>
								<form:checkbox path="child.registrationFeePaid"/>
							</td>
						</tr>
						<tr>
							<td>
								Deposit(�)
							</td>
							<td>
								<form:input path="child.depositPaid" size="4"/>
							</td>
						</tr>
						<tr>
							<td>
								Funded Sessions
							</td>
							<td>
								<form:input path="child.fundedSessions"/>
							</td>
						</tr>
						<tr>
							<td>
								Funded Lunches
							</td>
							<td>
								<form:input path="child.fundedLunches"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabbertab" title="Bills">
					<table class="listTable">
						<thead>
							<tr>
								<th>
									Term Name
								</th>
								<th>
									Amount Owed
								</th>
								<th>
									Amount Paid
								</th>
							</tr>
							<c:forEach items="${formObject.child.outstandingBills}" var="outstandingBill">
								<tr>
									<td>
										${outstandingBill.termName}
									</td>
									<td>
										${outstandingBill.billAmount}
									</td>
									<td>
										${outstandingBill.balancePaid}
									</td>
								</tr>
							</c:forEach>
						</thead>
					</table>
				</div>			
			</div>
			<input type="hidden" name="action" value=""/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.action.value='Save';document.pageForm.submit();return false;">Save</button>
				<button onclick="document.pageForm.action.value='Delete Selected';document.pageForm.submit();return false;">Delete Selected</button>
				<button onclick="document.location = 'addMedicalInfo.htm?childId=${formObject.child.id}';return false;">Add Medical Conditions</button>
				<button onclick="document.location = 'addGuardian.htm?childId=${formObject.child.id}';return false;">Add Guardian</button>
			</div>
		</div>
	</body>
</html>