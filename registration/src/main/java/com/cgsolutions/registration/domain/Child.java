package com.cgsolutions.registration.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.enums.Ethnicity;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.security.utility.MyDateUtils;

@Entity
@Table(name = "child")
public class Child {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Version
	private Date lastUpdate;
	private String firstName;
	private String surname;
	@ManyToOne()
	@JoinColumn(name="roomId")
	private Room room;
	private Date dateOfBirth;
	@OneToMany(targetEntity=MedicalInfo.class, cascade=CascadeType.ALL)
	@JoinColumn(name="childId")
	private List<MedicalInfo> medicalInfo;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance mondayAttendance;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance tuesdayAttendance;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance wednesdayAttendance;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance thursdayAttendance;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance fridayAttendance;
	@Enumerated(EnumType.STRING)
	private Ethnicity ethnicity;
	private String doctorsName;
	private String doctorsContactNumber;
	private Integer depositPaid;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String county;
	private String postCode;
	private String password;
	private String sex;
	private Date startDate;
	private boolean leftSchool;
	private boolean registrationFeePaid;
	@Transient
	private boolean selected;
	private int fundedSessions;
	private int fundedLunches;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=Guardian.class, cascade=CascadeType.ALL)
	private List<Guardian> guardians;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=Intolerance.class, cascade=CascadeType.ALL)
	private List<Intolerance> intolerances;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=Authorisation.class, cascade=CascadeType.ALL)
	private List<Authorisation> authorisations;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=Contact.class, cascade=CascadeType.ALL)
	private List<Contact> contacts;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=AdditionalSetting.class, cascade=CascadeType.ALL)
	private List<AdditionalSetting> additionalSettings;
	@JoinColumn(name="childId")
	@OneToMany(targetEntity=Payment.class, cascade=CascadeType.ALL)
	private List<Payment> payments;
	@Transient
	private List<TermBill> bills;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<MedicalInfo> getMedicalInfo() {
		return medicalInfo;
	}
	public void setMedicalInfo(List<MedicalInfo> medicalInfo) {
		this.medicalInfo = medicalInfo;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Guardian> getGuardians() {
		return guardians;
	}
	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}
	public TypeOfAttendance getMondayAttendance() {
		return mondayAttendance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMondayAttendance(TypeOfAttendance mondayAttendance) {
		this.mondayAttendance = mondayAttendance;
	}
	public TypeOfAttendance getTuesdayAttendance() {
		return tuesdayAttendance;
	}
	public void setTuesdayAttendance(TypeOfAttendance tuesdayAttendance) {
		this.tuesdayAttendance = tuesdayAttendance;
	}
	public TypeOfAttendance getWednesdayAttendance() {
		return wednesdayAttendance;
	}
	public void setWednesdayAttendance(TypeOfAttendance wednesdayAttendance) {
		this.wednesdayAttendance = wednesdayAttendance;
	}
	public TypeOfAttendance getThursdayAttendance() {
		return thursdayAttendance;
	}
	public void setThursdayAttendance(TypeOfAttendance thursdayAttendance) {
		this.thursdayAttendance = thursdayAttendance;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public TypeOfAttendance getFridayAttendance() {
		return fridayAttendance;
	}
	public void setFridayAttendance(TypeOfAttendance fridayAttendance) {
		this.fridayAttendance = fridayAttendance;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Ethnicity getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getDoctorsName() {
		return doctorsName;
	}
	public void setDoctorsName(String doctorsName) {
		this.doctorsName = doctorsName;
	}
	public String getDoctorsContactNumber() {
		return doctorsContactNumber;
	}
	public void setDoctorsContactNumber(String doctorsContactNumber) {
		this.doctorsContactNumber = doctorsContactNumber;
	}
	public Integer getDepositPaid() {
		return depositPaid;
	}
	public void setDepositPaid(Integer depositPaid) {
		this.depositPaid = depositPaid;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<AdditionalSetting> getAdditionalSettings() {
		return additionalSettings;
	}
	public void setAdditionalSettings(List<AdditionalSetting> additionalSettings) {
		this.additionalSettings = additionalSettings;
	}
	public String getCounty() {
		return county;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isRegistrationFeePaid() {
		return registrationFeePaid;
	}
	public void setRegistrationFeePaid(boolean registrationFeePaid) {
		this.registrationFeePaid = registrationFeePaid;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public boolean isLeftSchool() {
		return leftSchool;
	}
	public void setLeftSchool(boolean leftSchool) {
		this.leftSchool = leftSchool;
	}
	public int getFundedSessions() {
		return fundedSessions;
	}
	public void setFundedSessions(int fundedSessions) {
		this.fundedSessions = fundedSessions;
	}
	public int getFundedLunches() {
		return fundedLunches;
	}
	public void setFundedLunches(int fundedLunches) {
		this.fundedLunches = fundedLunches;
	}
	public List<Intolerance> getIntolerances() {
		return intolerances;
	}
	public void setIntolerances(List<Intolerance> intolerances) {
		this.intolerances = intolerances;
	}
	public List<Authorisation> getAuthorisations() {
		return authorisations;
	}
	public void setAuthorisations(List<Authorisation> authorisations) {
		this.authorisations = authorisations;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public TypeOfAttendance getTypeOfAttendance(Date attendanceDate){
		if(MyDateUtils.isItAMonday(attendanceDate)){
			return this.mondayAttendance;
		}
		else if(MyDateUtils.isItATuesday(attendanceDate)){
			return this.tuesdayAttendance;
		}
		else if(MyDateUtils.isItAWednesday(attendanceDate)){
			return this.wednesdayAttendance;
		}
		else if(MyDateUtils.isItAThursday(attendanceDate)){
			return this.thursdayAttendance;
		}
		else if(MyDateUtils.isItAFriday(attendanceDate)){
			return this.fridayAttendance;
		}
		else{
			throw new RuntimeException("Date " + MyDateUtils.getStringFromDate(attendanceDate, MyDateUtils.STANDARD_DATE_FORMAT) + " is not a weekday");
		}
	}
	
	public boolean shouldAttendMorning(Date day){
		TypeOfAttendance attendance = getTypeOfAttendance(day);
		if(attendance != null){
			return attendance.equals(TypeOfAttendance.FULL) || attendance.equals(TypeOfAttendance.MORNING);
		}
		else{
			return false;
		}
	}
	
	public boolean shouldAttendAfternoon(Date day){
		TypeOfAttendance attendance = getTypeOfAttendance(day);
		if(attendance != null){
			return attendance.equals(TypeOfAttendance.FULL) || attendance.equals(TypeOfAttendance.AFTERNOON);
		}
		else{
			return false;
		}
	}
	
	public boolean shouldHaveLunch(Date day){
		TypeOfAttendance attendance = getTypeOfAttendance(day);
		if(attendance != null){
			return attendance.equals(TypeOfAttendance.FULL);
		}
		else{
			return false;
		}
	}
	
	public int getChildsAge(){
		int days = MyDateUtils.getDifferenceInDaysBetweenDates(new Date(), this.getDateOfBirth());
		int years = 0;
		while(days > 0){
			Date dateToProcess = MyDateUtils.incrementByYears(this.getDateOfBirth(), years);
			int yearToProcess = Integer.parseInt(MyDateUtils.getStringFromDate(dateToProcess, "yyyy"));
			//If its a leap year
			if(yearToProcess % 4 == 0){
				days -= 366;
			}
			else{
				days -=365;
			}
			if(days > -1){
				years++;
			}
		}
		
		return years;
	}
	public List<TermBill> getBills() {
		return bills;
	}
	public void setBills(List<TermBill> bills) {
		this.bills = bills;
	}
	
	public String getCurrentBalance(){
		BigDecimal total = new BigDecimal(getPaymentTotal() - getBillTotal());
		BigDecimal rounded = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return rounded.toPlainString();
	}
	
	public float getBillTotal(){
		float billTotal = 0;
		if(!CollectionUtils.isEmpty(this.bills)){
			for(TermBill bill: this.getBills()){
				billTotal += bill.getTotalCost();
			}
		}
		
		return billTotal;
	}
	
	public float getPaymentTotal(){
		float paymentsTotal = 0;
		
		if(!CollectionUtils.isEmpty(this.getPayments())){
			for(Payment payment: this.getPayments()){
				paymentsTotal += payment.getAmount();
			}
		}
		
		return paymentsTotal;
	}
}