package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeHasSkillPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idEmployee;
	private Integer idSkill;

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
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
		result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
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
		EmployeeHasSkillPk other = (EmployeeHasSkillPk) obj;
		if (idEmployee == null) {
			if (other.idEmployee != null)
				return false;
		} else if (!idEmployee.equals(other.idEmployee))
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
		return "EmployeeHasSkillPk [idEmployee=" + idEmployee + ", idSkill=" + idSkill + "]";
	}

}
