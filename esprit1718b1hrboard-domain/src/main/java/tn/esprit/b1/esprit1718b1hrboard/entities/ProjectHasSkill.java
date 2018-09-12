package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProjectHasSkill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectHasSkillPk projectHasSkillPk;
	private String name;
	private String type;
	private Integer experience;
	private Float level;

	private Project project;
	private Skill skill;

	@EmbeddedId
	public ProjectHasSkillPk getProjectHasSkillPk() {
		return projectHasSkillPk;
	}

	public void setProjectHasSkillPk(ProjectHasSkillPk projectHasSkillPk) {
		this.projectHasSkillPk = projectHasSkillPk;
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

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Float getLevel() {
		return level;
	}

	public void setLevel(Float level) {
		this.level = level;
	}

	@ManyToOne
	@JoinColumn(name = "idProject", referencedColumnName = "id", insertable = false, updatable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(name = "idSkill", referencedColumnName = "id", insertable = false, updatable = false)
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public ProjectHasSkill() {
		super();
	}

	public ProjectHasSkill(String name, String type, Integer experience, Float level) {
		super();
		this.name = name;
		this.type = type;
		this.experience = experience;
		this.level = level;
	}

	public ProjectHasSkill(ProjectHasSkillPk projectHasSkillPk, String name, String type, Integer experience,
			Float level) {
		super();
		this.projectHasSkillPk = projectHasSkillPk;
		this.name = name;
		this.type = type;
		this.experience = experience;
		this.level = level;
	}

	@Override
	public String toString() {
		return "ProjectHasSkill [projectHasSkillPk=" + projectHasSkillPk + ", name=" + name + ", type=" + type
				+ ", experience=" + experience + ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectHasSkillPk == null) ? 0 : projectHasSkillPk.hashCode());
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
		ProjectHasSkill other = (ProjectHasSkill) obj;
		if (projectHasSkillPk == null) {
			if (other.projectHasSkillPk != null)
				return false;
		} else if (!projectHasSkillPk.equals(other.projectHasSkillPk))
			return false;
		return true;
	}


//	@Override
//	public String toString() {
//		return "ProjectHasSkill [projectHasSkillPk=" + projectHasSkillPk + ", name=" + name + ", type=" + type
//				+ ", experience=" + experience + ", level=" + level + ", project=" + project + ", skill=" + skill + "]";
//	}
	
	
	
	
	
	
	

}
