package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeHasTraining implements Serializable {// association class

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeHasTrainingPk employeeHasTrainingPk;
	private Boolean attempt;
	private String employeeNote;
	private Integer noteTrainer;

	private Employee employee;
	private Training training;
	
	@Override
	public String toString() {
		return "EmployeeHasTraining [employeeHasTrainingPk=" + employeeHasTrainingPk + ", attempt=" + attempt
				+ ", employeeNote=" + employeeNote + ", noteTrainer=" + noteTrainer + ", employee=" + employee
				+ ", training=" + training + "]";
	}

	public EmployeeHasTraining(Boolean attempt, String employeeNote, Integer noteTrainer) {
		super();
		this.attempt = attempt;
		this.employeeNote = employeeNote;
		this.noteTrainer = noteTrainer;
	}

	public EmployeeHasTraining() {
		super();
	}

	public EmployeeHasTraining(EmployeeHasTrainingPk employeeHasTrainingPk, Boolean attempt, String employeeNote,
			Integer noteTrainer) {
		super();
		this.employeeHasTrainingPk = employeeHasTrainingPk;
		this.attempt = attempt;
		this.employeeNote = employeeNote;
		this.noteTrainer = noteTrainer;
	}

	@EmbeddedId
	public EmployeeHasTrainingPk getEmployeeHasTrainingPk() {
		return employeeHasTrainingPk;
	}

	public void setEmployeeHasTrainingPk(EmployeeHasTrainingPk employeeHasTrainingPk) {
		this.employeeHasTrainingPk = employeeHasTrainingPk;
	}

	public Boolean getAttempt() {
		return attempt;
	}

	public void setAttempt(Boolean attempt) {
		this.attempt = attempt;
	}

	@Column(columnDefinition = "TEXT")
	public String getEmployeeNote() {
		return employeeNote;
	}

	public void setEmployeeNote(String employeeNote) {
		this.employeeNote = employeeNote;
	}

	public Integer getNoteTrainer() {
		return noteTrainer;
	}

	public void setNoteTrainer(Integer noteTrainer) {
		this.noteTrainer = noteTrainer;
	}

	@ManyToOne
	@JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable = false, updatable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne
	@JoinColumn(name = "idTraining", referencedColumnName = "id", insertable = false, updatable = false)
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}
