package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PaySlip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date extractionDate;
	private Date startPaymentDate;
	private Date endPaymentDate;
	private Date requestExtractionDate;
	private String type;
	private String state ;

	private Employee employee;

	public PaySlip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaySlip(Date extractionDate, Date requestExtractionDate, String type, String state, Employee employee) {
		super();
		this.extractionDate = extractionDate;
		this.requestExtractionDate = requestExtractionDate;
		this.type = type;
		this.state = state;
		this.employee = employee;
	}
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExtractionDate() {
		return extractionDate;
	}

	public void setExtractionDate(Date extractionDate) {
		this.extractionDate = extractionDate;
	}

	
	@Temporal(TemporalType.DATE)
	public Date getStartPaymentDate() {
		return startPaymentDate;
	}

	public void setStartPaymentDate(Date startPaymentDate) {
		this.startPaymentDate = startPaymentDate;
	}

	
	
	@Temporal(TemporalType.DATE)
	public Date getEndPaymentDate() {
		return endPaymentDate;
	}

	public void setEndPaymentDate(Date endPaymentDate) {
		this.endPaymentDate = endPaymentDate;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRequestExtractionDate() {
		return requestExtractionDate;
	}

	public void setRequestExtractionDate(Date requestExtractionDate) {
		this.requestExtractionDate = requestExtractionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	@ManyToOne(fetch=FetchType.EAGER)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "PaySlip [id=" + id + ", extractionDate=" + extractionDate + ", startPaymentDate=" + startPaymentDate
				+ ", endPaymentDate=" + endPaymentDate + ", requestExtractionDate=" + requestExtractionDate + ", type="
				+ type + ", state=" + state + ", employee=" + employee + "]";
	}


	
}
