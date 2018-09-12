package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Contract;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class ContractService extends GenericDAO<Contract> implements ContractServiceRemote, ContractServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public ContractService() {
		super(Contract.class);
	}

}
