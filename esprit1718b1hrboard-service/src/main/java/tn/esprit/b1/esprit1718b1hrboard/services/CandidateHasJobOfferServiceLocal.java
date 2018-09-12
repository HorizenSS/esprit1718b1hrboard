package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface CandidateHasJobOfferServiceLocal extends IGenericDAO<CandidateHasJobOffer> {
	CandidateHasJobOffer findByid (Candidate c , JobOffer j );
	public void removeCandidateHasSkill(CandidateHasJobOffer c);
}
