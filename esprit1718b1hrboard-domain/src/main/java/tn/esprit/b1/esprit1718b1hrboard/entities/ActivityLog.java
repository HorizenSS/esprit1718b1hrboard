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
public class ActivityLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date activityDate;
	private String actLog;
	private Integer importance;

	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	@Column(columnDefinition = "TEXT")
	public String getActLog() {
		return actLog;
	}

	public void setActLog(String actLog) {
		this.actLog = actLog;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ActivityLog() {
		super();
	}

	public ActivityLog(Date activityDate, String actLog, Integer importance) {
		super();
		this.activityDate = activityDate;
		this.actLog = actLog;
		this.importance = importance;
	}

	public ActivityLog(Integer id, Date activityDate, String actLog, Integer importance) {
		super();
		this.id = id;
		this.activityDate = activityDate;
		this.actLog = actLog;
		this.importance = importance;
	}

	@Override
	public String toString() {
		return "ActivityLog [id=" + id + ", activityDate=" + activityDate + ", actLog=" + actLog + ", importance="
				+ importance + ", employee=" + employee + "]";
	}

	
	
}
