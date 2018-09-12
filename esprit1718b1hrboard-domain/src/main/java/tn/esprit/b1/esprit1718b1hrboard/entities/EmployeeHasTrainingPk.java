package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeHasTrainingPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idEmployee;
	private Integer idTraining;

	

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Integer getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Integer idTraining) {
		this.idTraining = idTraining;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
		result = prime * result + ((idTraining == null) ? 0 : idTraining.hashCode());
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
		EmployeeHasTrainingPk other = (EmployeeHasTrainingPk) obj;
	
		if (idEmployee == null) {
			if (other.idEmployee != null)
				return false;
		} else if (!idEmployee.equals(other.idEmployee))
			return false;
		if (idTraining == null) {
			if (other.idTraining != null)
				return false;
		} else if (!idTraining.equals(other.idTraining))
			return false;
		return true;
	}

}
