package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class AbsenceService extends GenericDAO<Absence> implements AbsenceServiceRemote, AbsenceServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public AbsenceService() {
		super(Absence.class);
	}

	@Override
	public List<Absence> findAllAbsByEmployee(Employee employee) {
		List<Absence> employeeAbs = null;

		TypedQuery<Absence> query = entityManager.createQuery("SELECT a FROM Absence a WHERE a.absentEmployee = :employee",
				Absence.class);
		employeeAbs = query.setParameter("employee", employee).getResultList();

		return employeeAbs;
	}

}
