package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TaskPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProject;
	private Integer idEmployee;

	private String name; 
	
	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TaskPk other = (TaskPk) obj;
		if (idEmployee == null) {
			if (other.idEmployee != null)
				return false;
		} else if (!idEmployee.equals(other.idEmployee))
			return false;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TaskPk [idProject=" + idProject + ", idEmployee=" + idEmployee + ", name=" + name + "]";
	}


}
