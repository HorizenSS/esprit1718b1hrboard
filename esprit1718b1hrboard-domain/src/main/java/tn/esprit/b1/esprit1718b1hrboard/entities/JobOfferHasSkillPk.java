package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class JobOfferHasSkillPk implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idJobOffer;
	private Integer idSkill;
	public Integer getIdJobOffer() {
		return idJobOffer;
	}
	public void setIdJobOffer(Integer idJobOffer) {
		this.idJobOffer = idJobOffer;
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
		result = prime * result + ((idJobOffer == null) ? 0 : idJobOffer.hashCode());
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
		JobOfferHasSkillPk other = (JobOfferHasSkillPk) obj;
		if (idJobOffer == null) {
			if (other.idJobOffer != null)
				return false;
		} else if (!idJobOffer.equals(other.idJobOffer))
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
		return "JobOfferHasSkillPk [idJobOffer=" + idJobOffer + ", idSkill=" + idSkill + "]";
	}
	
	
}
