package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double hourPrice;
	private Integer requiredHoursNb;//per day
	private Date endDate;
	private String Type;

	private Employee employee;

	public Contract() {
		super();
	}

	public Contract(Integer id, Double hourPrice, Integer requiredHoursNb, Date endDate, String type) {
		super();
		this.id = id;
		this.hourPrice = hourPrice;
		this.requiredHoursNb = requiredHoursNb;
		this.endDate = endDate;
		Type = type;
	}

	public Contract(Double hourPrice, Integer requiredHoursNb, Date endDate, String type) {
		super();
		this.hourPrice = hourPrice;
		this.requiredHoursNb = requiredHoursNb;
		this.endDate = endDate;
		Type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getHourPrice() {
		return hourPrice;
	}

	public void setHourPrice(Double hourPrice) {
		this.hourPrice = hourPrice;
	}

	public Integer getRequiredHoursNb() {
		return requiredHoursNb;
	}

	public void setRequiredHoursNb(Integer requiredHoursNb) {
		this.requiredHoursNb = requiredHoursNb;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToOne(mappedBy = "contract")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
