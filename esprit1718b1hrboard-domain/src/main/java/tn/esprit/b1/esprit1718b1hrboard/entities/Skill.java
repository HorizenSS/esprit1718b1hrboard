package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String type;
	private Float note;

	private List<EmployeeHasSkill> specificationsSkills;
	private List<CandidateHasSkill> specificationsSkillsCand;
	private List<JobOfferHasSkill> skillsrequired;

	private List<ProjectHasSkill> projectsInSkill;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
	}

	@OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<EmployeeHasSkill> getSpecificationsSkills() {
		return specificationsSkills;
	}

	public void setSpecificationsSkills(List<EmployeeHasSkill> specificationsSkills) {
		this.specificationsSkills = specificationsSkills;
	}

	@OneToMany(mappedBy = "skill")
	public List<CandidateHasSkill> getSpecificationsSkillsCand() {
		return specificationsSkillsCand;
	}

	public void setSpecificationsSkillsCand(List<CandidateHasSkill> specificationsSkillsCand) {
		this.specificationsSkillsCand = specificationsSkillsCand;
	}

	@OneToMany(mappedBy = "skill")
	public List<ProjectHasSkill> getProjectsInSkill() {
		return projectsInSkill;
	}

	public void setProjectsInSkill(List<ProjectHasSkill> projectsInSkill) {
		this.projectsInSkill = projectsInSkill;
	}

	@OneToMany(mappedBy = "skill")
	public List<JobOfferHasSkill> getSkillsrequired() {
		return skillsrequired;
	}

	public void setSkillsrequired(List<JobOfferHasSkill> skillsrequired) {
		this.skillsrequired = skillsrequired;
	}

	public Skill() {
		super();
	}

	public Skill(Integer id, String name, String type, Float note) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.note = note;
	}

	public Skill(String name, String type, Float note) {
		super();
		this.name = name;
		this.type = type;
		this.note = note;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", type=" + type + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Skill [id=" + id + ", name=" + name + ", type=" + type + ", note=" + note + ", specificationsSkills="
//				+ specificationsSkills + ", specificationsSkillsCand=" + specificationsSkillsCand + "]";
//	}
	
	
	
	
	

}
