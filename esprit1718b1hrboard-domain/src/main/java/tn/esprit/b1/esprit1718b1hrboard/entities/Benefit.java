package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Benefit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String typeBenefit;
	private Double value;
	private Date obtentionDate;

	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeBenefit() {
		return typeBenefit;
	}

	public void setTypeBenefit(String typeBenefit) {
		this.typeBenefit = typeBenefit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Temporal(TemporalType.DATE)
	public Date getObtentionDate() {
		return obtentionDate;
	}

	public void setObtentionDate(Date obtentionDate) {
		this.obtentionDate = obtentionDate;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	public Benefit() {
		super();
	}

	public Benefit(Integer id, String typeBenefit, Double value, Date obtentionDate) {
		super();
		this.id = id;
		this.typeBenefit = typeBenefit;
		this.value = value;
		this.obtentionDate = obtentionDate;
	}

	public Benefit(String typeBenefit, Double value, Date obtentionDate) {
		super();
		this.typeBenefit = typeBenefit;
		this.value = value;
		this.obtentionDate = obtentionDate;
	}

	@Override
	public String toString() {
		return "Benefit [id=" + id + ", typeBenefit=" + typeBenefit + ", value=" + value + ", obtentionDate="
				+ obtentionDate + ", employee=" + employee + "]";
	}
	
}
