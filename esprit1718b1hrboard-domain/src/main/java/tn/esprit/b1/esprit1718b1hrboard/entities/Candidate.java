package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Candidate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private String address;
	private String workExperiences;
	private String diplomas;

	private List<CandidateHasJobOffer> jobOfferInscriptions;

	private List<CandidateHasSkill> specificationsSkillsCand;

	private List<Interview> interviews;
	
	
	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", workExperiences=" + workExperiences
				+ ", diplomas=" + diplomas + ", jobOfferInscriptions=" + jobOfferInscriptions
				+ ", specificationsSkillsCand=" + specificationsSkillsCand + ", interviews=" + interviews + "]";
	}

	public Candidate(Integer id, String firstName, String lastName, String email, Long phoneNumber, String address,
			String workExperiences, String diplomas) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.workExperiences = workExperiences;
		this.diplomas = diplomas;
	}

	public Candidate(String firstName, String lastName, String email, Long phoneNumber, String address,
			String workExperiences, String diplomas) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.workExperiences = workExperiences;
		this.diplomas = diplomas;
	}

	public Candidate() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(columnDefinition = "TEXT")
	public String getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(String workExperiences) {
		this.workExperiences = workExperiences;
	}

	public String getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(String diplomas) {
		this.diplomas = diplomas;
	}

	@OneToMany(mappedBy = "candidate" , fetch = FetchType.EAGER , cascade=CascadeType.ALL)
	public List<CandidateHasJobOffer> getJobOfferInscriptions() {
		return jobOfferInscriptions;
	}

	public void setJobOfferInscriptions(List<CandidateHasJobOffer> jobOfferInscriptions) {
		this.jobOfferInscriptions = jobOfferInscriptions;
	}

	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER , cascade=CascadeType.ALL)
	public List<CandidateHasSkill> getSpecificationsSkillsCand() {
		return specificationsSkillsCand;
	}

	public void setSpecificationsSkillsCand(List<CandidateHasSkill> specificationsSkillsCand) {
		this.specificationsSkillsCand = specificationsSkillsCand;
	}

	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER , cascade=CascadeType.ALL)
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

}
