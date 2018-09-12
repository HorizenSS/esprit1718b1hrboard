package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String cin;
	private String numPassport;
	private String code;
	private String gender;

	public Employee(Integer id, String firstName, String lastName, String cin, String numPassport, String code,
			String gender, String email, Long phoneNumber, Date birthDate, String nationality, Date startDay,
			Date endDay, String socialStaus, Integer childNumber, String address, Long ribAccount, String grade,
			Integer workedHours, Long emergencyPhone, String emergencyAddress, Float appreciationMonth,
			Float appreciationYear, String profilPic, Boolean workStatus, Integer vacationDays, Employee supervisor,
			List<Employee> myTeam, Account account, List<ActivityLog> activityLogs, Bank bank, List<Benefit> benefits,
			Contract contract, List<Delay> delays, List<Document> documents, List<PaySlip> paySlips, Post post,
			Resignation resignation, List<Absence> myabsences, List<Absence> subustitutedAbs, List<Reward> rewards,
			List<EmployeeHasTraining> formParticipations, List<Vacation> vacations,
			Set<EmployeeHasSkill> specificationsSkills, List<Interview> interviews, List<Training> formerTrainings,
			Set<Task> tasks, Set<Project> masterProjects) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.numPassport = numPassport;
		this.code = code;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.startDay = startDay;
		this.endDay = endDay;
		this.socialStaus = socialStaus;
		this.childNumber = childNumber;
		this.address = address;
		this.ribAccount = ribAccount;
		this.grade = grade;
		this.workedHours = workedHours;
		this.emergencyPhone = emergencyPhone;
		this.emergencyAddress = emergencyAddress;
		this.appreciationMonth = appreciationMonth;
		this.appreciationYear = appreciationYear;
		this.profilPic = profilPic;
		this.workStatus = workStatus;
		this.vacationDays = vacationDays;
		this.supervisor = supervisor;
		this.myTeam = myTeam;
		this.account = account;
		this.activityLogs = activityLogs;
		this.bank = bank;
		this.benefits = benefits;
		this.contract = contract;
		this.delays = delays;
		this.documents = documents;
		this.paySlips = paySlips;
		this.post = post;
		this.resignation = resignation;
		this.myabsences = myabsences;
		this.subustitutedAbs = subustitutedAbs;
		this.rewards = rewards;
		this.formParticipations = formParticipations;
		this.vacations = vacations;
		this.specificationsSkills = specificationsSkills;
		this.interviews = interviews;
		this.formerTrainings = formerTrainings;
		this.tasks = tasks;
		this.masterProjects = masterProjects;
	}

	private String email;
	private Long phoneNumber;
	private Date birthDate;
	private String nationality;
	private Date startDay;
	private Date endDay;
	private String socialStaus;
	private Integer childNumber;
	private String address;
	private Long ribAccount;
	private String grade;// his level grade in the post designated
	private Integer workedHours;// per month
	private Long emergencyPhone;
	private String emergencyAddress;
	private Float appreciationMonth;
	private Float appreciationYear;
	private String profilPic;
	private Boolean workStatus;// old employee
	private Integer vacationDays;
	private Float levelProject;

	// Relations Attributes

	private Employee supervisor;
	private List<Employee> myTeam;

	private Account account;

	private List<ActivityLog> activityLogs;

	private Bank bank;

	private List<Benefit> benefits;

	private Contract contract;

	private List<Delay> delays;

	private List<Document> documents;

	private List<PaySlip> paySlips;

	private Post post;

	private Resignation resignation;

	private List<Absence> myabsences;
	private List<Absence> subustitutedAbs;

	private List<Reward> rewards;

	private List<EmployeeHasTraining> formParticipations;

	private List<Vacation> vacations;

	private Set<EmployeeHasSkill> specificationsSkills;

	private List<Interview> interviews;

	private List<Training> formerTrainings;

	private Set<Task> tasks;

	private Set<Project> masterProjects;
	
	private Payroll payroll;
	
	private List<Topic> topicsPosted; 
	
	private List<Topic> topicsResponses;
	
	private List<TimeLine> timelines;
	
	

	// Getters and setters

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public Employee() {
		super();
	}

	// @Override
	// public String toString() {
	// return "Employee [id=" + id + ", firstName=" + firstName + ", lastName="
	// + lastName + ", cin=" + cin
	// + ", numPassport=" + numPassport + ", code=" + code + ", gender=" +
	// gender + ", email=" + email
	// + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ",
	// nationality=" + nationality
	// + ", startDay=" + startDay + ", endDay=" + endDay + ", socialStaus=" +
	// socialStaus + ", childNumber="
	// + childNumber + ", address=" + address + ", ribAccount=" + ribAccount +
	// ", grade=" + grade
	// + ", workedHours=" + workedHours + ", emergencyPhone=" + emergencyPhone +
	// ", emergencyAddress="
	// + emergencyAddress + ", appreciationMonth=" + appreciationMonth + ",
	// appreciationYear="
	// + appreciationYear + ", profilPic=" + profilPic + ", workStatus=" +
	// workStatus + ", vacationDays="
	// + vacationDays + ", supervisor=" + supervisor + ", myTeam=" + myTeam + ",
	// account=" + account
	// + ", activityLogs=" + activityLogs + ", bank=" + bank + ", benefits=" +
	// benefits + ", contract="
	// + contract + ", delays=" + delays + ", documents=" + documents + ",
	// paySlips=" + paySlips + ", post="
	// + post + ", resignation=" + resignation + ", myabsences=" + myabsences +
	// ", subustitutedAbs="
	// + subustitutedAbs + ", rewards=" + rewards + ", formParticipations=" +
	// formParticipations
	// + ", vacations=" + vacations + ", specificationsSkills=" +
	// specificationsSkills + ", interviews="
	// + interviews + ", formerTrainings=" + formerTrainings + ", tasks=" +
	// tasks + "]";
	// }

	/* ToString with Only the apropriate fields of class employee */
	// @Override
	// public String toString() {
	// return "Employee [id=" + id + ", firstName=" + firstName + ", lastName="
	// + lastName + ", cin=" + cin
	// + ", numPassport=" + numPassport + ", code=" + code + ", gender=" +
	// gender + ", email=" + email
	// + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ",
	// nationality=" + nationality
	// + ", startDay=" + startDay + ", endDay=" + endDay + ", socialStaus=" +
	// socialStaus + ", childNumber="
	// + childNumber + ", address=" + address + ", ribAccount=" + ribAccount +
	// ", grade=" + grade
	// + ", workedHours=" + workedHours + ", emergencyPhone=" + emergencyPhone +
	// ", emergencyAddress="
	// + emergencyAddress + ", appreciationMonth=" + appreciationMonth + ",
	// appreciationYear="
	// + appreciationYear + ", profilPic=" + profilPic + ", workStatus=" +
	// workStatus + ", vacationDays="
	// + vacationDays + "post=" + post + "]";
	// }

	public Employee(Integer id, String firstName, String lastName, String cin, String numPassport, String code,
			String gender, String email, Long phoneNumber, Date birthDate, String nationality, Date startDay,
			Date endDay, String socialStaus, Integer childNumber, String address, Long ribAccount, String grade,
			Integer workedHours, Long emergencyPhone, String emergencyAddress, Float appreciationMonth,
			Float appreciationYear, String profilPic, Boolean workStatus, Integer vacationDays) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.numPassport = numPassport;
		this.code = code;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.startDay = startDay;
		this.endDay = endDay;
		this.socialStaus = socialStaus;
		this.childNumber = childNumber;
		this.address = address;
		this.ribAccount = ribAccount;
		this.grade = grade;
		this.workedHours = workedHours;
		this.emergencyPhone = emergencyPhone;
		this.emergencyAddress = emergencyAddress;
		this.appreciationMonth = appreciationMonth;
		this.appreciationYear = appreciationYear;
		this.profilPic = profilPic;
		this.workStatus = workStatus;
		this.vacationDays = vacationDays;
	}

	@Override
	public String toString() {

		return firstName + " " + lastName;

	}

	public Employee(String firstName, String lastName, String cin, String numPassport, String gender, String email,
			Long phoneNumber, Date birthDate, String nationality, Date startDay, Date endDay, String socialStaus,
			Integer childNumber, String address, Long ribAccount, String grade, Integer workedHours,
			Long emergencyPhone, String emergencyAddress, Float appreciationMonth, Float appreciationYear,
			String profilPic, Boolean workStatus, Integer vacationDays) {
		super();
		Year year = Year.now();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.numPassport = numPassport;
		this.code = year + "esprit" + cin;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.startDay = startDay;
		this.endDay = endDay;
		this.socialStaus = socialStaus;
		this.childNumber = childNumber;
		this.address = address;
		this.ribAccount = ribAccount;
		this.grade = grade;
		this.workedHours = workedHours;
		this.emergencyPhone = emergencyPhone;
		this.emergencyAddress = emergencyAddress;
		this.appreciationMonth = appreciationMonth;
		this.appreciationYear = appreciationYear;
		this.profilPic = profilPic;
		this.workStatus = workStatus;
		this.vacationDays = vacationDays;
	}

	public Employee(String string, String string2, String string3, String string4, String string5, String string6,
			Long long1, Date birth, String string7, Date start, Date end, String string8, int i, String string9,
			Long long2, String string10, int j, Long long3, String string11, Float float1, String string12, boolean b,
			int k) {
		super();
		Year year = Year.now();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.numPassport = numPassport;
		this.code = year + "esprit" + cin;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.startDay = startDay;
		this.endDay = endDay;
		this.socialStaus = socialStaus;
		this.childNumber = childNumber;
		this.address = address;
		this.ribAccount = ribAccount;
		this.grade = grade;
		this.workedHours = workedHours;
		this.emergencyPhone = emergencyPhone;
		this.emergencyAddress = emergencyAddress;
		this.appreciationMonth = appreciationMonth;
		this.appreciationYear = appreciationYear;
		this.profilPic = profilPic;
		this.workStatus = workStatus;
		this.vacationDays = vacationDays;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNumPassport() {
		return numPassport;
	}

	public void setNumPassport(String numPassport) {
		this.numPassport = numPassport;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code =code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Temporal(TemporalType.DATE)
	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getSocialStaus() {
		return socialStaus;
	}

	public void setSocialStaus(String socialStaus) {
		this.socialStaus = socialStaus;
	}

	public Integer getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(Integer childNumber) {
		this.childNumber = childNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getRibAccount() {
		return ribAccount;
	}

	public void setRibAccount(Long ribAccount) {
		this.ribAccount = ribAccount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(Integer workedHours) {
		this.workedHours = workedHours;
	}

	public Long getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(Long emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getEmergencyAddress() {
		return emergencyAddress;
	}

	public void setEmergencyAddress(String emergencyAddress) {
		this.emergencyAddress = emergencyAddress;
	}

	public Float getAppreciationMonth() {
		return appreciationMonth;
	}

	public void setAppreciationMonth(Float appreciationMonth) {
		this.appreciationMonth = appreciationMonth;
	}

	public Float getAppreciationYear() {
		return appreciationYear;
	}

	public void setAppreciationYear(Float appreciationYear) {
		this.appreciationYear = appreciationYear;
	}

	public String getProfilPic() {
		return profilPic;
	}

	public void setProfilPic(String profilPic) {
		this.profilPic = profilPic;
	}

	public Boolean getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Boolean workStatus) {
		this.workStatus = workStatus;
	}

	public Integer getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(Integer vacationDays) {
		this.vacationDays = vacationDays;
	}

	public Float getLevelProject() {
		return levelProject;
	}

	public void setLevelProject(Float levelProject) {
		this.levelProject = levelProject;
	}

	@ManyToOne
	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	@OneToMany(mappedBy = "supervisor")
	public List<Employee> getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(List<Employee> myTeam) {
		this.myTeam = myTeam;
	}

	@OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<ActivityLog> getActivityLogs() {
		return activityLogs;
	}

	public void setActivityLogs(List<ActivityLog> activityLogs) {
		this.activityLogs = activityLogs;
	}

	@ManyToOne
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Benefit> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<Benefit> benefits) {
		this.benefits = benefits;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Delay> getDelays() {
		return delays;
	}

	public void setDelays(List<Delay> delays) {
		this.delays = delays;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<PaySlip> getPaySlips() {
		return paySlips;
	}

	public void setPaySlips(List<PaySlip> paySlips) {
		this.paySlips = paySlips;
	}

	@ManyToOne
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@OneToOne
	public Resignation getResignation() {
		return resignation;
	}

	public void setResignation(Resignation resignation) {
		this.resignation = resignation;
	}

	@OneToMany(mappedBy = "absentEmployee", cascade = CascadeType.REMOVE)
	public List<Absence> getMyabsences() {
		return myabsences;
	}

	public void setMyabsences(List<Absence> myabsences) {
		this.myabsences = myabsences;
	}

	@OneToMany(mappedBy = "substituteEmployee", cascade = CascadeType.REMOVE)
	public List<Absence> getSubustitutedAbs() {
		return subustitutedAbs;
	}

	public void setSubustitutedAbs(List<Absence> subustitutedAbs) {
		this.subustitutedAbs = subustitutedAbs;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<EmployeeHasTraining> getFormParticipations() {
		return formParticipations;
	}

	public void setFormParticipations(List<EmployeeHasTraining> formParticipations) {
		this.formParticipations = formParticipations;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public Set<EmployeeHasSkill> getSpecificationsSkills() {
		return specificationsSkills;
	}

	public void setSpecificationsSkills(Set<EmployeeHasSkill> specificationsSkills) {
		this.specificationsSkills = specificationsSkills;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	@OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE)
	public List<Training> getFormerTrainings() {
		return formerTrainings;
	}

	public void setFormerTrainings(List<Training> formerTrainings) {
		this.formerTrainings = formerTrainings;
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(mappedBy = "projectMaster", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	public Set<Project> getMasterProjects() {
		return masterProjects;
	}

	public void setMasterProjects(Set<Project> masterProjects) {
		this.masterProjects = masterProjects;
	}

	
	@OneToOne(cascade=CascadeType.ALL)
	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	@OneToMany(mappedBy="employee")
	public List<Topic> getTopics() {
		return topicsPosted;
	}

	public void setTopics(List<Topic> topics) {
		this.topicsPosted = topics;
	}

	@OneToMany(mappedBy="employee")
	public List<Topic> getTopicsResponses() {
		return topicsResponses;
	}

	public void setTopicsResponses(List<Topic> topicsResponses) {
		this.topicsResponses = topicsResponses;
	}

	@OneToMany(mappedBy="employee",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	public List<TimeLine> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<TimeLine> timelines) {
		this.timelines = timelines;
	}
	
}
