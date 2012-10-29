<%@ include file="taglibraries.jspf" %>
<!doctype html>

<html>
	<head>
		<%@ include file="headerIncludes.jspf" %>
		<script type="text/javascript">
			$(document).ready(function() {
				if("${attendancesDeleted}" != ""){
					alert("${attendancesDeleted} attendances deleted");
				}
			});
			
			function deleteAttendances(){
				document.location = "editChild.htm?deleteAttendances=Y&childId=${formObject.child.id}";
			}
			
			function removeRow(rowId){
				$("#" + rowId).remove();
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
								Password
							</td>
							<td>
								<form:input path="child.password"/>
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
								<form:select path="child.ethnicity" items="${ethnicityList}" itemLabel="description"/>
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
								Start Date
							</td>
							<td>
								<form:input path="child.startDate" cssClass="date"/>
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
										<table class="listTable">
											<thead>
												<tr>
													<th>
														Delete
													</th>
													<th>
														Condition
													</th>
													<th>
														Notes
													</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${formObject.newMedicalInfos}" var="newMedicalInfo" varStatus="listIndex">
													<spring:nestedPath path="newMedicalInfos[${listIndex.index}]">
														<tr id="medicalCondition${listIndex.index}" >
															<td style="vertical-align: top; text-align: center;" class="deleteColumn">

															</td>
															<td style="vertical-align: top;">
																<form:input path="medicalCondition" onchange="cloneRow('medicalCondition', ${listIndex.index});"/>
															</td>
															<td>
																<form:textarea path="notes" rows="4" cols="60"/>
															</td>
														</tr>
													</spring:nestedPath>
												</c:forEach>
											</tbody>
											<tbody>
												<c:forEach items="${formObject.child.medicalInfo}" var="info" varStatus="listIndex">
													<spring:nestedPath path="child.medicalInfo[${listIndex.index}]">
														<tr valign="top">
															<td style="vertical-align: top; text-align: center;">
																<form:checkbox path="selected"/> 
															</td>
															<td style="vertical-align: top;">
																<form:input path="medicalCondition"/>
															</td>
															<td>
																<form:textarea path="notes" rows="4" cols="60"/>
															</td>
													</spring:nestedPath>
												</c:forEach>
											</tbody>
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
														<tr id="intolerance${listIndex.index}" >
															<td style="vertical-align: top; text-align: center;" class="deleteColumn">

															</td>
															<td style="vertical-align: top;">
																<form:input path="intolerance" onchange="cloneRow('intolerance', ${listIndex.index});"/>
															</td>
															<td>
																<form:textarea path="precaution" rows="4" cols="60"/>
															</td>
														</tr>
													</spring:nestedPath>
												</c:forEach>
											</tbody>
											<tbody>
												<c:forEach items="${formObject.child.intolerances}" var="intolerance" varStatus="listIndex">
													<spring:nestedPath path="child.intolerances[${listIndex.index}]">
														<tr valign="top">
															<td style="vertical-align: top; text-align: center;">
																<form:checkbox path="selected"/> 
															</td>
															<td style="vertical-align: top;">
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
				<div class="tabbertab" title="Authorisations">
					<table class="listTable">
						<thead>
							<tr>
								<th style="text-align: center;">
									Delete
								</th>
								<th>
									Activity
								</th>
								<th>
									Reason
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formObject.newAuthorisations}" var="newAuthorisation" varStatus="listIndex">
								<spring:nestedPath path="newAuthorisations[${listIndex.index}]">
									<tr id="authorisation${listIndex.index}">
										<td style="vertical-align: top; text-align: center;" class="deleteColumn">
											
										</td>
										<td style="vertical-align: top;">
											<form:input path="activity" onchange="cloneRow('authorisation', ${listIndex.index});"/>
										</td>
										<td>
											<form:textarea path="reason" rows="4" cols="60"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
							<c:forEach var="authorisation" items="${formObject.child.authorisations}" varStatus="listIndex"> 
								<spring:nestedPath path="child.authorisations[${listIndex.index}]">
									<tr>
										<td style="vertical-align: top; text-align: center;">
											<form:checkbox path="selected"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="activity"/>
										</td>
										<td>
											<form:textarea path="reason" rows="4" cols="60"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
						</tbody>
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
										<c:choose>
											<c:when test="${guardian.sameAddressAsChild}">
												<tr>
													<td>
														Address Line 1
													</td>
													<td>
														${formObject.child.addressLine1}
													</td>
												</tr>
												<tr>
													<td>
														Address Line 2
													</td>
													<td>
														${formObject.child.addressLine2}
													</td>
												</tr>
												<tr>
													<td>
														Address Line 3
													</td>
													<td>
														${formObject.child.addressLine3}
													</td>
												</tr>
												<tr>
													<td>
														City
													</td>
													<td>
														${formObject.child.city}
													</td>
												</tr>
												<tr>
													<td>
														County
													</td>
													<td>
														${formObject.child.county}
													</td>
												</tr>
												<tr>
													<td>
														Post Code
													</td>
													<td>
														${formObject.child.postCode}
													</td>
												</tr>
											</c:when>
											<c:otherwise>
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
											</c:otherwise>
										</c:choose>
									</table>
								</div>
							</spring:nestedPath>
						</c:forEach>
					</div>
				</div>
				<div class="tabbertab" title="Contacts">
					<p>Childs Password is '<b>${formObject.child.password}</b>'</p>
					<table class="listTable">
						<thead>
							<tr>
								<th style="text-align: center;">
									Delete
								</th>
								<th>
									First Name
								</th>
								<th>
									Surname
								</th>
								<th>
									Phone Number
								</th>
								<th>
									Can Collect
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formObject.newContacts}" var="newContact" varStatus="listIndex">
								<spring:nestedPath path="newContacts[${listIndex.index}]">
									<tr id="contact${listIndex.index}">
										<td style="vertical-align: top; text-align: center;" class="deleteColumn">
											
										</td>
										<td style="vertical-align: top;">
											<form:input path="firstName" onchange="cloneRow('contact', ${listIndex.index});"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="surname"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="phoneNumber"/>
										</td>
										<td style="vertical-align: top;">
											<form:checkbox path="allowedToCollect"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
							<c:forEach var="contact" items="${formObject.child.contacts}" varStatus="listIndex"> 
								<spring:nestedPath path="child.contacts[${listIndex.index}]">
									<tr>
										<td style="vertical-align: top; text-align: center;">
											<form:checkbox path="selected"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="firstName"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="surname"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="phoneNumber"/>
										</td>
										<td style="vertical-align: top;">
											<form:checkbox path="allowedToCollect"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tabbertab" title="Additional Settings">
					<table class="listTable">
						<thead>
							<tr>
								<th style="text-align: center;">
									Delete
								</th>
								<th>
									Name
								</th>
								<th>
									Phone Number
								</th>
								<th>
									Share Information
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formObject.newSettings}" var="newSetting" varStatus="listIndex">
								<spring:nestedPath path="newSettings[${listIndex.index}]">
									<tr id="setting${listIndex.index}">
										<td style="vertical-align: top; text-align: center;" class="deleteColumn">
											
										</td>
										<td style="vertical-align: top;">
											<form:input path="name" onchange="cloneRow('setting', ${listIndex.index});"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="phoneNumber"/>
										</td>
										<td style="vertical-align: top;">
											<form:checkbox path="shareInfo"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
							<c:forEach var="contact" items="${formObject.child.additionalSettings}" varStatus="listIndex"> 
								<spring:nestedPath path="child.additionalSettings[${listIndex.index}]">
									<tr>
										<td style="vertical-align: top; text-align: center;">
											<form:checkbox path="selected"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="name"/>
										</td>
										<td style="vertical-align: top;">
											<form:input path="phoneNumber"/>
										</td>
										<td style="vertical-align: top;">
											<form:checkbox path="shareInfo"/>
										</td>
									</tr>
								</spring:nestedPath>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tabbertab" title="Financial">
					<h4 style="float: right;">Current Balance ${formObject.child.currentBalance}</h4>
					<div class="tabber">
						<div class="tabbertab" title="General">
							<table class="formTable">
								<tr style="cursor: pointer;" onclick="GB_show('Attendances', '/registration-webapp/viewAttendances.htm?termId=${bill.term.id}&hideHeader=Y', 600, 800);">
									<td>
										Registration Fee Paid
									</td>
									<td>
										<form:checkbox path="child.registrationFeePaid"/>
									</td>
								</tr>
								<tr>
									<td>
										Deposit(£)
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
											Term Name
										</th>
										<th>
											Start Date
										</th>
										<th>
											End Date
										</th>
										<th>
											Lunches
										</th>
										<th>
											Sessions
										</th>
										<th>
											Cost
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${formObject.child.bills}" var="bill">
										<tr style="cursor: pointer;" onclick="GB_show('Attendances', '/registration-webapp/viewAttendances.htm?termId=${bill.term.id}&hideHeader=Y', 600, 800);">
											<td>
												${bill.term.termName}
											</td>
											<td>
												${bill.room.name}
											</td>
											<td>
												<fmt:formatDate value="${bill.term.startDate}" pattern="dd MMM yyyy"/>
											</td>
											<td>
												<fmt:formatDate value="${bill.term.endDate}" pattern="dd MMM yyyy"/>
											</td>
											<td>
												${bill.lunches} (£${bill.totalLunchesCost})
											</td>
											<td>
												${bill.sessions} (£${bill.totalSessionsCost})
											</td>
											<td>
												£${bill.totalCost}
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tabbertab" title="Payments">
							<table class="listTable">
								<thead>
									<tr>
										<th>
											Remove
										</th>
										<th>
											Payment Date
										</th>
										<th>
											Amount
										</th>
										<th>
											Payment Type
										</th>
										<th>
											Comments
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${formObject.newPayments}" var="newPayment" varStatus="listIndex">
										<spring:nestedPath path="newPayments[${listIndex.index}]">
											<tr id="payment${listIndex.index}">
												<td style="vertical-align: top; text-align: center;" class="deleteColumn">
													
												</td>
												<td style="vertical-align: top;">
													<form:input path="datePaid" onchange="cloneRow('payment', ${listIndex.index});" cssClass="date"/>
												</td>
												<td style="vertical-align: top;">
													<form:input path="amount" size="5" maxlength="11"/>
												</td>
												<td style="vertical-align: top;">
													<form:select path="paymentType" items="${paymentList}" itemLabel="description"/>
												</td>
												<td>
													<form:input path="comments" size="60" maxlength="255"/>
												</td>
											</tr>
										</spring:nestedPath>
									</c:forEach>
									<c:forEach var="payment" items="${formObject.child.payments}" varStatus="listIndex"> 
										<spring:nestedPath path="child.payments[${listIndex.index}]">
											<tr>
												<td style="vertical-align: top; text-align: center;">
													&nbsp;
												</td>
												<td style="vertical-align: top;">
													<fmt:formatDate value="${payment.datePaid}" pattern="dd MMM yyyy"/>
												</td>
												<td style="vertical-align: top;">
													<form:input path="amount" size="5" maxlength="11"/>
												</td>
												<td style="vertical-align: top;">
													<form:select path="paymentType" items="${paymentList}" itemLabel="description"/>
												</td>
												<td>
													<form:input path="comments" size="60" maxlength="255"/>
												</td>
											</tr>
										</spring:nestedPath>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>			
			</div>
			<input type="hidden" name="action" value=""/>
		</form:form>
		<div id="buttonBar"> 
			<div id="holder">
				<button onclick="document.pageForm.action.value='Save';document.pageForm.submit();return false;">Save</button>
				<button onclick="GB_show('Add Guardian', '/registration-webapp/addGuardian.htm?childId=${formObject.child.id}', 600, 850);return false;">Add Guardian</button>
				<button onclick="window.open('/registration-webapp/generateAttendances.htm?childId=${formObject.child.id}&redo=Y', 'Redo');return false;">Recalculate Attendances</button>
				<button onclick="deleteAttendances();return false;">Delete Attendances</button>
				<button onclick="document.location = 'addGuardian.htm?childId=${formObject.child.id}';return false;">Add Guardian</button>
				<button onclick="deleteAttendances();return false;">Delete Attendances</button>
			</div>
		</div>
	</body>
</html>