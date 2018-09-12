package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CandidateHasJobOffer implements Serializable  {// Association class !

	private CandidateHasJobOfferPk candidateHasJobOfferPk;
	private Date applicationDate;
	private Boolean applicationStatus;
	private float note;

	private Candidate candidate;
	private JobOffer jobOffer;
	
	
	@Override
	public String toString() {
		return "CandidateHasJobOffer [candidateHasJobOfferPk=" + candidateHasJobOfferPk + ", applicationDate="
				+ applicationDate + ", applicationStatus=" + applicationStatus  + "]";
	}

	public CandidateHasJobOffer(Date applicationDate, Boolean applicationStatus) {
		super();
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
	}

	public CandidateHasJobOffer(CandidateHasJobOfferPk candidateHasJobOfferPk, Date applicationDate,
			Boolean applicationStatus) {
		super();
		this.candidateHasJobOfferPk = candidateHasJobOfferPk;
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
	}

	public CandidateHasJobOffer() {
		super();
	}

	@EmbeddedId
	public CandidateHasJobOfferPk getCandidateHasJobOfferPk() {
		return candidateHasJobOfferPk;
	}

	public void setCandidateHasJobOfferPk(CandidateHasJobOfferPk candidateHasJobOfferPk) {
		this.candidateHasJobOfferPk = candidateHasJobOfferPk;
	}
	
	
	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Boolean getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(Boolean applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@ManyToOne
	@JoinColumn(name = "idCandidate", referencedColumnName = "id", insertable = false, updatable = false)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	@JoinColumn(name = "idJobOffer", referencedColumnName = "id", insertable = false, updatable = false)
	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

}
