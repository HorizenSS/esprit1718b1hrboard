package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vacation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String sbmissionMessage;
	private String responseMessage;
	private Date submissionDate;
	private Date responseDate;
	private Date startDate;
	private Date endDate;
	private Boolean status;
	private VacationType vacationType;

	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(columnDefinition = "TEXT")
	public String getSbmissionMessage() {
		return sbmissionMessage;
	}

	public void setSbmissionMessage(String sbmissionMessage) {
		this.sbmissionMessage = sbmissionMessage;
	}

	@Column(columnDefinition = "TEXT")
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
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
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public VacationType getVacationType() {
		return vacationType;
	}

	public void setVacationType(VacationType vacationType) {
		this.vacationType = vacationType;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Vacation() {
		super();
	}

	public Vacation(Integer id, String sbmissionMessage, String responseMessage, Date submissionDate, Date responseDate,
			Date startDate, Date endDate, Boolean status, VacationType vacationType) {
		super();
		this.id = id;
		this.sbmissionMessage = sbmissionMessage;
		this.responseMessage = responseMessage;
		this.submissionDate = submissionDate;
		this.responseDate = responseDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.vacationType = vacationType;
	}

	public Vacation(String sbmissionMessage, String responseMessage, Date submissionDate, Date responseDate,
			Date startDate, Date endDate, Boolean status, VacationType vacationType) {
		super();
		this.sbmissionMessage = sbmissionMessage;
		this.responseMessage = responseMessage;
		this.submissionDate = submissionDate;
		this.responseDate = responseDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.vacationType = vacationType;
	}

	@Override
	public String toString() {
		return "Vacation [id=" + id + ", sbmissionMessage=" + sbmissionMessage + ", responseMessage=" + responseMessage
				+ ", submissionDate=" + submissionDate + ", responseDate=" + responseDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + ", vacationType=" + vacationType + ", employee="
				+ employee + "]";
	}

}
