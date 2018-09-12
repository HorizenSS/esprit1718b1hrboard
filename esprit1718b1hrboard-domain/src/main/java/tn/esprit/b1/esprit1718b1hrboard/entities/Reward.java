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
public class Reward implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Incentive rewardType;
	private String description;
	private Date obtainingDate;
	private Date tripDate;// to discuss about trip incentive
	private Integer placesNumber;
	private String tripDestination;
	private Double bonus;

	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Incentive getRewardType() {
		return rewardType;
	}

	public void setRewardType(Incentive rewardType) {
		this.rewardType = rewardType;
	}

	@Column(columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	public Date getObtainingDate() {
		return obtainingDate;
	}

	public void setObtainingDate(Date obtainingDate) {
		this.obtainingDate = obtainingDate;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public Integer getPlacesNumber() {
		return placesNumber;
	}

	public void setPlacesNumber(Integer placesNumber) {
		this.placesNumber = placesNumber;
	}

	public String getTripDestination() {
		return tripDestination;
	}

	public void setTripDestination(String tripDestination) {
		this.tripDestination = tripDestination;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Reward() {
		super();
	}

	public Reward(Integer id, Incentive rewardType, String description, Date obtainingDate, Date tripDate,
			Integer placesNumber, String tripDestination, Double bonus) {
		super();
		this.id = id;
		this.rewardType = rewardType;
		this.description = description;
		this.obtainingDate = obtainingDate;
		this.tripDate = tripDate;
		this.placesNumber = placesNumber;
		this.tripDestination = tripDestination;
		this.bonus = bonus;
	}

	public Reward(Incentive rewardType, String description, Date obtainingDate, Date tripDate, Integer placesNumber,
			String tripDestination, Double bonus) {
		super();
		this.rewardType = rewardType;
		this.description = description;
		this.obtainingDate = obtainingDate;
		this.tripDate = tripDate;
		this.placesNumber = placesNumber;
		this.tripDestination = tripDestination;
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "Reward [id=" + id + ", rewardType=" + rewardType + ", description=" + description + ", obtainingDate="
				+ obtainingDate + ", tripDate=" + tripDate + ", placesNumber=" + placesNumber + ", tripDestination="
				+ tripDestination + ", bonus=" + bonus + ", employee=" + employee + "]";
	}

}
