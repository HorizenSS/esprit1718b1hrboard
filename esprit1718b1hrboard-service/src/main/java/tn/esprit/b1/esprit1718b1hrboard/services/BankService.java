package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Bank;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class BankService extends GenericDAO<Bank> implements BankServiceRemote, BankServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public BankService() {
		super(Bank.class);
	}

}
