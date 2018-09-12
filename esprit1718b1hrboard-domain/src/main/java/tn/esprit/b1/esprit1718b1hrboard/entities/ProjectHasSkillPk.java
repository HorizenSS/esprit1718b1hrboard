package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProjectHasSkillPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProject;
	private Integer idSkill;

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public Integer getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(Integer idSkill) {
		this.idSkill = idSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		result = prime * result + ((idSkill == null) ? 0 : idSkill.hashCode());
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
		ProjectHasSkillPk other = (ProjectHasSkillPk) obj;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		if (idSkill == null) {
			if (other.idSkill != null)
				return false;
		} else if (!idSkill.equals(other.idSkill))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectHasSkillPk [idProject=" + idProject + ", idSkill=" + idSkill + "]";
	}

}
