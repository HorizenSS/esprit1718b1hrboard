package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Benefit;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class BenefitService extends GenericDAO<Benefit> implements BenefitServiceRemote, BenefitServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public BenefitService() {
		super(Benefit.class);
	}

}
