package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOfferPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface CandidateHasJobOfferServiceRemote extends IGenericDAO<CandidateHasJobOffer>{
	CandidateHasJobOffer findByid (Candidate c , JobOffer j );
	public void removeCandidateHasSkill(CandidateHasJobOffer c);
}
