package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class InterviewService extends GenericDAO<Interview> implements InterviewServiceRemote, InterviewServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public InterviewService() {
		super(Interview.class);
	}
	@Override
	public Interview findByName(String name) {

		Interview interview = null;
		TypedQuery<Interview> query = entityManager.createQuery("SELECT i FROM Interview i WHERE i.interviewType = :name",
				Interview.class);
		interview = (Interview) query.setParameter("name", name).getSingleResult();
		return interview;

	}
	@Override
	public void addinterview(Interview i) {

		Query query = entityManager.createNativeQuery("INSERT into Interview (interviewType , inteviewDate ,timebegin,timeend,attempt,idCandidate,idJobOffer)"+
		" VALUES ( :a, :b, :c, :d, :e, :f, :g)",Interview.class);
		query.setParameter("a", i.getInterviewType())
		.setParameter("b", i.getInteviewDate())
		.setParameter("c", i.getTimebegin())
		.setParameter("d", i.getTimeend())
		.setParameter("e", i.getAttempt())
		.setParameter("f", i.getCandidate().getId())
		.setParameter("g", i.getJobOffer().getId())
		.executeUpdate();

	}
	
	@Override
	public void removeInterview(Interview i) {

		entityManager.createQuery("DELETE FROM Interview i WHERE i.id = :id").setParameter("id", i.getId())
				.executeUpdate();
	}

}
