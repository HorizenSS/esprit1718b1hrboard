package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class SkillService extends GenericDAO<Skill> implements SkillServiceRemote, SkillServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public SkillService() {
		super(Skill.class);
	}

	@Override
	public List<Skill> findSkillsByProject(Project project) {
		List<Skill> skills = null;

		TypedQuery<Skill> query = entityManager.createQuery(
				"SELECT DISTINCT s FROM Skill s JOIN s.projectsInSkill p WHERE p.project = :project", Skill.class);
		skills = query.setParameter("project", project).getResultList();
		return skills;
	}

	public Skill SkillByname(String name) {
		Skill s = null;
		TypedQuery<Skill> query = entityManager.createQuery("SELECT s FROM Skill s WHERE s.name = :name", Skill.class);
		s = (Skill) query.setParameter("name", name).getSingleResult();
		return s;
	}

	@Override
	public List<Skill> findSkillsByEmployee(Employee employee) {
		List<Skill> skills = null;

		TypedQuery<Skill> query = entityManager.createQuery(
				"SELECT DISTINCT s FROM Skill s JOIN s.specificationsSkills p WHERE p.employee = :employee",
				Skill.class);
		skills = query.setParameter("employee", employee).getResultList();
		return skills;
	}

	@Override
	public int saveReturn(Skill skill) {

		entityManager.persist(skill);
		return skill.getId();
	}

}
