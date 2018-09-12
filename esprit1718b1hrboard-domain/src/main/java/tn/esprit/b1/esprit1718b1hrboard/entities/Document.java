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
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String docType;
	private String path;
	private Date attachementDate;
	private Double monthSalary; // to review this field

	private Employee employee;
	
	
	
	@Override
	public String toString() {
		return "Document [id=" + id + ", docType=" + docType + ", path=" + path + ", attachementDate=" + attachementDate
				+ ", monthSalary=" + monthSalary + ", employee=" + employee + "]";
	}

	public Document(String docType, String path, Date attachementDate, Double monthSalary) {
		super();
		this.docType = docType;
		this.path = path;
		this.attachementDate = attachementDate;
		this.monthSalary = monthSalary;
	}

	public Document(Integer id, String docType, String path, Date attachementDate, Double monthSalary) {
		super();
		this.id = id;
		this.docType = docType;
		this.path = path;
		this.attachementDate = attachementDate;
		this.monthSalary = monthSalary;
	}

	public Document() {
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

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	@Column(columnDefinition = "TEXT")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getAttachementDate() {
		return attachementDate;
	}

	public void setAttachementDate(Date attachementDate) {
		this.attachementDate = attachementDate;
	}

	public Double getMonthSalary() {
		return monthSalary;
	}

	public void setMonthSalary(Double monthSalary) {
		this.monthSalary = monthSalary;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
