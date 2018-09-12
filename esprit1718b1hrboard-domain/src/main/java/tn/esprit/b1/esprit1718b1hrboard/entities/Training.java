package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Training implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String trainingName;
	private String place;
	private String trainingtype;
	private String subject;
	private Date tarainingDateStart;
	private Date tarainingDateEnd;
	// private Integer duration;

	private List<EmployeeHasTraining> formParticipations;

	private Employee trainer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTrainingtype() {
		return trainingtype;
	}

	public void setTrainingtype(String trainingtype) {
		this.trainingtype = trainingtype;
	}

	@Column(columnDefinition = "TEXT")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getTarainingDateStart() {
		return tarainingDateStart;
	}

	public void setTarainingDateStart(Date tarainingDateStart) {
		this.tarainingDateStart = tarainingDateStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getTarainingDateEnd() {
		return tarainingDateEnd;
	}

	public void setTarainingDateEnd(Date tarainingDateEnd) {
		this.tarainingDateEnd = tarainingDateEnd;
	}

	@OneToMany(mappedBy = "training",fetch=FetchType.EAGER , cascade = CascadeType.ALL)
	public List<EmployeeHasTraining> getFormParticipations() {
		return formParticipations;
	}

	public void setFormParticipations(List<EmployeeHasTraining> formParticipations) {
		this.formParticipations = formParticipations;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public Employee getTrainer() {
		return trainer;
	}

	public void setTrainer(Employee trainer) {
		this.trainer = trainer;
	}

	public Training() {
		super();
	}

	
	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public Training(Integer id, String trainingName,String place, String trainingtype, String subject, Date tarainingDateStart,
			Date tarainingDateEnd) {
		super();
		this.id = id;
		this.trainingName = trainingName;
		this.place = place;
		this.trainingtype = trainingtype;
		this.subject = subject;
		this.tarainingDateStart = tarainingDateStart;
		this.tarainingDateEnd = tarainingDateEnd;
	}

	public Training( String trainingName,String place, String trainingtype, String subject, Date tarainingDateStart, Date tarainingDateEnd) {
		super();
		this.place = place;
		this.trainingName = trainingName;
		this.trainingtype = trainingtype;
		this.subject = subject;
		this.tarainingDateStart = tarainingDateStart;
		this.tarainingDateEnd = tarainingDateEnd;
	}

	public Training(String place, String trainingName, String trainingtype, String subject, Date tarainingDateStart, Date tarainingDateEnd,
			Employee trainer) {
		super();
		this.place = place;
		this.trainingName = trainingName;
		this.trainingtype = trainingtype;
		this.subject = subject;
		this.tarainingDateStart = tarainingDateStart;
		this.tarainingDateEnd = tarainingDateEnd;
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "Training [id=" + id +", Name=" + trainingName +", place=" + place + ", trainingtype=" + trainingtype + ", subject=" + subject
				+ ", tarainingDateStart=" + tarainingDateStart + ", tarainingDateEnd=" + tarainingDateEnd
				+  ", trainer=" + trainer + "]";
	}



	

	

}
