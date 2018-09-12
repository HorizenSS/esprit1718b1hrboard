package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOfferPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class CandidateHasJobOfferService extends GenericDAO<CandidateHasJobOffer>
		implements CandidateHasJobOfferServiceRemote, CandidateHasJobOfferServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public CandidateHasJobOfferService() {
		super(CandidateHasJobOffer.class);
	}

	@Override
	public CandidateHasJobOffer findByid(Candidate c , JobOffer j) {
		CandidateHasJobOffer candidateHasJobOffer=null;
		
		TypedQuery<CandidateHasJobOffer> query = entityManager.createQuery("SELECT c FROM CandidateHasJobOffer c WHERE c.candidate = :candidate and c.jobOffer =:jobOffer", CandidateHasJobOffer.class);
		candidateHasJobOffer = (CandidateHasJobOffer) query.setParameter("candidate", c).setParameter("jobOffer", j).getSingleResult();
		
		return candidateHasJobOffer;
	}
	@Override
	public void removeCandidateHasSkill(CandidateHasJobOffer c) {

		entityManager.createQuery("DELETE FROM CandidateHasJobOffer c WHERE c.candidate =:candidate and c.jobOffer =:joboffer").setParameter("candidate", c.getCandidate()).setParameter("joboffer", c.getJobOffer())
				.executeUpdate();
	}

}
