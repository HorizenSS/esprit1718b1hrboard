package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Interview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String interviewType;
	private Date inteviewDate;
	private Time timebegin;
	private Time timeend;
	private Boolean attempt;
	private String interviewerNotes;

	private Employee employee;
	private Candidate candidate;
	private JobOffer jobOffer;

	
	
	public String getInterviewType() {
		return interviewType;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Time getTimebegin() {
		return timebegin;
	}
	public void setTimebegin(Time timebegin) {
		this.timebegin = timebegin;
	}
	public Time getTimeend() {
		return timeend;
	}
	public void setTimeend(Time timeend) {
		this.timeend = timeend;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInteviewDate() {
		return inteviewDate;
	}

	public void setInteviewDate(Date inteviewDate) {
		this.inteviewDate = inteviewDate;
	}

	public Boolean getAttempt() {
		return attempt;
	}

	public void setAttempt(Boolean attempt) {
		this.attempt = attempt;
	}

	@Column(columnDefinition = "TEXT")
	public String getInterviewerNotes() {
		return interviewerNotes;
	}

	public void setInterviewerNotes(String interviewerNotes) {
		this.interviewerNotes = interviewerNotes;
	}

	@ManyToOne
	@JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable = false, updatable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
