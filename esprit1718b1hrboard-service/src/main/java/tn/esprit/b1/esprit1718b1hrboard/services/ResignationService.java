package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class ResignationService extends GenericDAO<Resignation>
		implements ResignationServiceRemote, ResignationServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public ResignationService() {
		super(Resignation.class);
	}

}
