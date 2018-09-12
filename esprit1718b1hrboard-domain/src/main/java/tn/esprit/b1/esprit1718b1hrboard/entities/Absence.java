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
public class Absence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date dateOfAbsence;
	private Boolean justified;
	private String justifiation;
	private Boolean status;
	private Date replacementDate;

	private Employee absentEmployee;
	private Employee substituteEmployee;
	
	public Absence() {
		super();
	}
//	@Override
//	public String toString() {
//		return "Absence [id=" + id + ", dateOfAbsence=" + dateOfAbsence + ", justified=" + justified + ", justifiation="
//				+ justifiation + ", status=" + status + ", replacementDate=" + replacementDate + ", absentEmployee="
//				+ absentEmployee + ", substituteEmployee=" + substituteEmployee + "]";
//	}
	
	
	public Absence(Date dateOfAbsence, Boolean justified, String justifiation, Boolean status, Date replacementDate) {
		super();
		this.dateOfAbsence = dateOfAbsence;
		this.justified = justified;
		this.justifiation = justifiation;
		this.status = status;
		this.replacementDate = replacementDate;
	}
	
	@Override
	public String toString() {
		return "Absence [id=" + id + ", dateOfAbsence=" + dateOfAbsence + ", justified=" + justified + ", justifiation="
				+ justifiation + ", status=" + status + ", replacementDate=" + replacementDate + "]";
	}


	public Absence(Integer id, Date dateOfAbsence, Boolean justified, String justifiation, Boolean status,
			Date replacementDate) {
		super();
		this.id = id;
		this.dateOfAbsence = dateOfAbsence;
		this.justified = justified;
		this.justifiation = justifiation;
		this.status = status;
		this.replacementDate = replacementDate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateOfAbsence() {
		return dateOfAbsence;
	}

	public void setDateOfAbsence(Date dateOfAbsence) {
		this.dateOfAbsence = dateOfAbsence;
	}

	public Boolean getJustified() {
		return justified;
	}

	public void setJustified(Boolean justified) {
		this.justified = justified;
	}

	@Column(columnDefinition="TEXT")
	public String getJustifiation() {
		return justifiation;
	}

	public void setJustifiation(String justifiation) {
		this.justifiation = justifiation;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	public Date getReplacementDate() {
		return replacementDate;
	}

	public void setReplacementDate(Date replacementDate) {
		this.replacementDate = replacementDate;
	}

	@ManyToOne
	public Employee getAbsentEmployee() {
		return absentEmployee;
	}

	public void setAbsentEmployee(Employee absentEmployee) {
		this.absentEmployee = absentEmployee;
	}

	@ManyToOne
	public Employee getSubstituteEmployee() {
		return substituteEmployee;
	}

	public void setSubstituteEmployee(Employee substituteEmployee) {
		this.substituteEmployee = substituteEmployee;
	}

}
