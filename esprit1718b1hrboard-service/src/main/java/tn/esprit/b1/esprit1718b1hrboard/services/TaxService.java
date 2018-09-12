package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;
import tn.esprit.b1.esprit1718b1hrboard.entities.Tax;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;


@Stateless
public class TaxService extends GenericDAO<Tax> implements TaxServiceRemote, TaxServiceLocal{

	@PersistenceContext
	private EntityManager entityManager;

	public TaxService() {
		super(Tax.class);
	}
	@Override
	public Tax findById() {
		
		Query qr = entityManager.createQuery("SELECT tx FROM Tax where id='9'",Tax.class);
		
		Tax tax = (Tax) qr.getSingleResult();
		
		return tax;
		
		
		
		
		
		
	}

}
