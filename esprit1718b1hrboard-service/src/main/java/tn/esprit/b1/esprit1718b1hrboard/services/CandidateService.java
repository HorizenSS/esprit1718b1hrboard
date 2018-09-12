package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class CandidateService extends GenericDAO<Candidate> implements CandidateServiceRemote, CandidateServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public CandidateService() {
		super(Candidate.class);
	}
	
	public Candidate findbymail(Candidate c){
		
		Candidate candidat = null;
		try {
			TypedQuery<Candidate> query = entityManager.createQuery("SELECT c FROM Candidate c WHERE c.email = :mail",
					Candidate.class);
			candidat = (Candidate) query.setParameter("mail", c.getEmail()).getSingleResult();
		} catch (Exception e) {
		}
		return candidat;
		
	}

}
