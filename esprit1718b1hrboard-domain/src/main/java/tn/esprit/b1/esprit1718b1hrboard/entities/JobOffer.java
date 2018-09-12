package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class JobOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String entitled;
	private String description;
	private String offerType;
	private Date offerDate;

	private List<CandidateHasJobOffer> jobOfferInscriptions;
	private List<JobOfferHasSkill> jobOfferSkills;
	private List<Interview> interviews ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(columnDefinition = "TEXT")
	public String getEntitled() {
		return entitled;
	}

	public void setEntitled(String entitled) {
		this.entitled = entitled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	@OneToMany(mappedBy = "jobOffer",fetch=FetchType.EAGER , cascade=CascadeType.ALL)
	public List<CandidateHasJobOffer> getJobOfferInscriptions() {
		return jobOfferInscriptions;
	}

	public void setJobOfferInscriptions(List<CandidateHasJobOffer> jobOfferInscriptions) {
		this.jobOfferInscriptions = jobOfferInscriptions;
	}
	
	@OneToMany(mappedBy = "jobOffer", fetch = FetchType.EAGER , cascade=CascadeType.ALL)
	public List<JobOfferHasSkill> getJobOfferSkills() {
		return jobOfferSkills;
	}

	public void setJobOfferSkills(List<JobOfferHasSkill> jobOfferSkills) {
		this.jobOfferSkills = jobOfferSkills;
	}

	@OneToMany(mappedBy = "jobOffer", fetch = FetchType.EAGER , cascade=CascadeType.ALL)
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public JobOffer() {
		super();
	}

	public JobOffer(Integer id, String entitled, String description, String offerType, Date offerDate) {
		super();
		this.id = id;
		this.entitled = entitled;
		this.description = description;
		this.offerType = offerType;
		this.offerDate = offerDate;
	}

	public JobOffer(String entitled, String description, String offerType, Date offerDate) {
		super();
		this.entitled = entitled;
		this.description = description;
		this.offerType = offerType;
		this.offerDate = offerDate;
	}

	@Override
	public String toString() {
		return "JobOffer [id=" + id + ", entitled=" + entitled + ", description=" + description + ", offerType="
				+ offerType + ", offerDate=" + offerDate + "]";
	}



}
