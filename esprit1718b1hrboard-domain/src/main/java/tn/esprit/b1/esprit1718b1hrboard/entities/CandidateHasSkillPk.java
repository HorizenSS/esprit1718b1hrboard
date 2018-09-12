package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CandidateHasSkillPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idCandidate;
	private Integer idSkill;



	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
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
		result = prime * result + ((idCandidate == null) ? 0 : idCandidate.hashCode());
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
		CandidateHasSkillPk other = (CandidateHasSkillPk) obj;
		if (idCandidate == null) {
			if (other.idCandidate != null)
				return false;
		} else if (!idCandidate.equals(other.idCandidate))
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
		return "CandidateHasSkillPk [idCandidate=" + idCandidate + ", idSkill=" + idSkill + "]";
	}

}
