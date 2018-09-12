package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class VacationService extends GenericDAO<Vacation> implements VacationServiceRemote, VacationServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public VacationService() {
		super(Vacation.class);
	}

}
