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
public class Delay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date dateDelay;
	private Integer durationPerHour;

	private Employee employee;
	
	public Delay() {
		super();
	}

	public Delay(Integer id, Date dateDelay, Integer durationPerHour) {
		super();
		this.id = id;
		this.dateDelay = dateDelay;
		this.durationPerHour = durationPerHour;
	}

	public Delay(Date dateDelay, Integer durationPerHour) {
		super();
		this.dateDelay = dateDelay;
		this.durationPerHour = durationPerHour;
	}

	@Override
	public String toString() {
		return "Delay [id=" + id + ", dateDelay=" + dateDelay + ", durationPerHour=" + durationPerHour + ", employee="
				+ employee + "]";
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
	public Date getDateDelay() {
		return dateDelay;
	}

	public void setDateDelay(Date dateDelay) {
		this.dateDelay = dateDelay;
	}

	public Integer getDurationPerHour() {
		return durationPerHour;
	}

	public void setDurationPerHour(Integer durationPerHour) {
		this.durationPerHour = durationPerHour;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
