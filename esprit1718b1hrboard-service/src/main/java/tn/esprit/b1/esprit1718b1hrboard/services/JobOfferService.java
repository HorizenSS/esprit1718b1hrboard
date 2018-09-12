package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class JobOfferService extends GenericDAO<JobOffer> implements JobOfferServiceRemote, JobOfferServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public JobOfferService() {
		super(JobOffer.class);
	}

	@Override
	public JobOffer JobOfferByEntitled(String entitled) {
		JobOffer jo = null;	
		TypedQuery<JobOffer> query = entityManager.createQuery("SELECT jo FROM JobOffer jo WHERE jo.entitled = :entitled", JobOffer.class);
		jo = (JobOffer) query.setParameter("entitled", entitled).getSingleResult();
		return jo;
	}

}
