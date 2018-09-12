package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Delay;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class DelayService extends GenericDAO<Delay> implements DelayServiceRemote, DelayServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public DelayService() {
		super(Delay.class);
	}

	@Override
	public List<Delay> findAllDelayByEmployee(Employee employee) {
		List<Delay> employeeDelay = null;

		TypedQuery<Delay> query = entityManager.createQuery("SELECT a FROM Delay a WHERE a.employee = :employee",
				Delay.class);
		employeeDelay = query.setParameter("employee", employee).getResultList();

		return employeeDelay;

	}

}
