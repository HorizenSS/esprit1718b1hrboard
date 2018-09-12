package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CandidateHasJobOfferPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idCandidate;
	private Integer idJobOffer;

	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
	}

	public Integer getIdJobOffer() {
		return idJobOffer;
	}

	public void setIdJobOffer(Integer idJobOffer) {
		this.idJobOffer = idJobOffer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCandidate == null) ? 0 : idCandidate.hashCode());
		result = prime * result + ((idJobOffer == null) ? 0 : idJobOffer.hashCode());
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
		CandidateHasJobOfferPk other = (CandidateHasJobOfferPk) obj;
		if (idCandidate == null) {
			if (other.idCandidate != null)
				return false;
		} else if (!idCandidate.equals(other.idCandidate))
			return false;
		if (idJobOffer == null) {
			if (other.idJobOffer != null)
				return false;
		} else if (!idJobOffer.equals(other.idJobOffer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CandidateHasJobOfferPk [idCandidate=" + idCandidate + ", idJobOffer=" + idJobOffer + "]";
	}
	

}
