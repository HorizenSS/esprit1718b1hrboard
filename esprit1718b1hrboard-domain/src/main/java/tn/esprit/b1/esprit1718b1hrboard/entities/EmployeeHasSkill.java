package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeHasSkill implements Serializable {

	// class association !!

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeHasSkillPk employeeHasSkillPk;
	private Integer level;
	private Boolean certifcation;
	private Float skillNote;// MARKED BY OTYHERS

	private Employee employee;
	private Skill skill;

	@Override
	public String toString() {
		return "EmployeeHasSkill [employeeHasSkillPk=" + employeeHasSkillPk + ", level=" + level + ", certifcation="
				+ certifcation + ", skillNote=" + skillNote + ", employee=" + employee + ", skill=" + skill + "]";
	}

	public EmployeeHasSkill(EmployeeHasSkillPk employeeHasSkillPk, Integer level, Boolean certifcation,
			Float skillNote) {
		super();
		this.employeeHasSkillPk = employeeHasSkillPk;
		this.level = level;
		this.certifcation = certifcation;
		this.skillNote = skillNote;
	}

	public EmployeeHasSkill(Integer level, Boolean certifcation, Float skillNote) {
		super();
		this.level = level;
		this.certifcation = certifcation;
		this.skillNote = skillNote;
	}

	public EmployeeHasSkill() {
		super();
	}

	@EmbeddedId
	public EmployeeHasSkillPk getEmployeeHasSkillPk() {
		return employeeHasSkillPk;
	}

	public void setEmployeeHasSkillPk(EmployeeHasSkillPk employeeHasSkillPk) {
		this.employeeHasSkillPk = employeeHasSkillPk;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getCertifcation() {
		return certifcation;
	}

	public void setCertifcation(Boolean certifcation) {
		this.certifcation = certifcation;
	}

	public Float getSkillNote() {
		return skillNote;
	}

	public void setSkillNote(Float skillNote) {
		this.skillNote = skillNote;
	}

	@ManyToOne
	@JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable = false, updatable = false )
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne
	@JoinColumn(name = "idSkill", referencedColumnName = "id", insertable = false, updatable = false)
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
