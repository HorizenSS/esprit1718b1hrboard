package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InterviewPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idEmployee;
	private Integer idCandidate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idCandidate == null) ? 0 : idCandidate.hashCode());
		result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
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
		InterviewPk other = (InterviewPk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCandidate == null) {
			if (other.idCandidate != null)
				return false;
		} else if (!idCandidate.equals(other.idCandidate))
			return false;
		if (idEmployee == null) {
			if (other.idEmployee != null)
				return false;
		} else if (!idEmployee.equals(other.idEmployee))
			return false;
		return true;
	}

}
