package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class ProjectService extends GenericDAO<Project> implements ProjectServiceRemote, ProjectServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public ProjectService() {
		super(Project.class);
	}

	@Override
	public List<Project> findAllProjectsByEmployee(Employee employee) {
		List<Project> projects = null;

		TypedQuery<Project> query = entityManager.createQuery(
				"SELECT DISTINCT p FROM Project p JOIN p.tasks t WHERE t.employee = :employee", Project.class);
		projects = query.setParameter("employee", employee).getResultList();
		return projects;
	}

	@Override
	public List<Project> findProjectsByEmployee(Employee employee) {
		List<Project> projects = null;

		// TypedQuery<Project> query = entityManager.createQuery("SELECT
		// DISTINCT p FROM Project p JOIN p.tasks t WHERE t.employee =
		// :employee",Project.class);
		// projects = query.setParameter("employee", employee).getResultList();
		return null;
	}

	@Override
	public List<Project> findProjectByMaster(Employee master) {
		List<Project> projects = null;

		TypedQuery<Project> query = entityManager
				.createQuery("SELECT p FROM Project p WHERE p.projectMaster = :employee", Project.class);
		projects = query.setParameter("employee", master).getResultList();
		return projects;
	}

	@Override
	public void removeProject(Project project) {
		// TODO Auto-generated method stub
		entityManager.createQuery("DELETE FROM Task p WHERE p.project = :project").setParameter("project", project)
				.executeUpdate();
		entityManager.createQuery("DELETE FROM ProjectHasSkill p WHERE p.project = :project")
				.setParameter("project", project).executeUpdate();
		entityManager.createQuery("DELETE FROM Project p WHERE p.id = :project")
				.setParameter("project", project.getId()).executeUpdate();
		entityManager.flush();
	}

	@Override
	public List<Project> findProjectByname(String master) {
		List<Project> projects = null;

		TypedQuery<Project> query = entityManager
				.createQuery("SELECT p FROM Project p WHERE p.name = :employee", Project.class);
		projects = query.setParameter("employee", master).getResultList();
		return projects;
		
	}

}
