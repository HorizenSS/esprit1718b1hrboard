package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Resignation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String submissionMsg;
	private String responseMsg;
	private Date submissionDate;
	private Date responseDate;
	private Date resignationDate;
	private Boolean status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(columnDefinition = "TEXT")
	public String getSubmissionMsg() {
		return submissionMsg;
	}

	public void setSubmissionMsg(String submissionMsg) {
		this.submissionMsg = submissionMsg;
	}

	@Column(columnDefinition = "TEXT")
	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Resignation() {
		super();
	}

	public Resignation(Integer id, String submissionMsg, String responseMsg, Date submissionDate, Date responseDate,
			Date resignationDate, Boolean status) {
		super();
		this.id = id;
		this.submissionMsg = submissionMsg;
		this.responseMsg = responseMsg;
		this.submissionDate = submissionDate;
		this.responseDate = responseDate;
		this.resignationDate = resignationDate;
		this.status = status;
	}

	public Resignation(String submissionMsg, String responseMsg, Date submissionDate, Date responseDate,
			Date resignationDate, Boolean status) {
		super();
		this.submissionMsg = submissionMsg;
		this.responseMsg = responseMsg;
		this.submissionDate = submissionDate;
		this.responseDate = responseDate;
		this.resignationDate = resignationDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Resignation [id=" + id + ", submissionMsg=" + submissionMsg + ", responseMsg=" + responseMsg
				+ ", submissionDate=" + submissionDate + ", responseDate=" + responseDate + ", resignationDate="
				+ resignationDate + ", status=" + status + "]";
	}

}
