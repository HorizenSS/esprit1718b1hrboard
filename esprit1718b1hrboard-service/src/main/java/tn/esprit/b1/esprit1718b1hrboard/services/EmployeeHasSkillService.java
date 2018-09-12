package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class EmployeeHasSkillService extends GenericDAO<EmployeeHasSkill>
		implements EmployeeHasSkillServiceRemote, EmployeeHasSkillServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public EmployeeHasSkillService() {
		super(EmployeeHasSkill.class);
	}

	@Override
	public List<EmployeeHasSkill> findSkillsDetailsByEmployee(Employee employee) {
		List<EmployeeHasSkill> employeeHasSkills = null;

		TypedQuery<EmployeeHasSkill> query = entityManager
				.createQuery("SELECT e FROM EmployeeHasSkill e WHERE e.employee = :employee", EmployeeHasSkill.class);
		employeeHasSkills = query.setParameter("employee", employee).getResultList();

		return employeeHasSkills;
	}

	@Override
	public List<Skill> findSkillsDetailsByEmployeeByOrder(int employeeId) {
		
		List<Skill> employeeHasSkills = null;

		TypedQuery<Skill> query = entityManager
				.createQuery("SELECT s from Skill s WHERE s.id()=: employeeId ", Skill.class);
		employeeHasSkills = query.setParameter("employeeId", employeeId).getResultList();

		return employeeHasSkills;
	}

	@Override
	public List<EmployeeHasSkill> findEmployeeBySkill(String skill) {
		List<EmployeeHasSkill> employeeBySkills = null;

		TypedQuery<EmployeeHasSkill> query = entityManager
				.createQuery("SELECT e FROM EmployeeHasSkill e WHERE e.skill.name = :skill", EmployeeHasSkill.class);
		employeeBySkills = query.setParameter("skill", skill).getResultList();

		return employeeBySkills;
	}

}
