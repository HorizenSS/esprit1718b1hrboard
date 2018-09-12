package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class JobOfferHasSkillService extends GenericDAO<JobOfferHasSkill> implements JobOfferHasSkillServiceLocal , JobOfferHasSkillServiceRemote{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	public JobOfferHasSkillService() {
		super(JobOfferHasSkill.class);
	
	}

}
