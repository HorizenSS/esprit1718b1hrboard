package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class ProjectHasSkillService extends GenericDAO<ProjectHasSkill>
		implements ProjectHasSkillServiceRemote, ProjectHasSkillServiceLocal {

	public ProjectHasSkillService() {
		super(ProjectHasSkill.class);
	}

	@PersistenceContext
	private EntityManager entityManager;

	public ProjectHasSkill find(Project project, Skill skill) {
		ProjectHasSkill projectHasSkill = null;
		TypedQuery<ProjectHasSkill> query = entityManager.createQuery(
				"SELECT p FROM ProjectHasSkill p WHERE p.project = :project AND p.skill = :skill ",
				ProjectHasSkill.class);
		projectHasSkill = (ProjectHasSkill) query.setParameter("project", project).setParameter("skill", skill)
				.getSingleResult();
		return projectHasSkill;

	}

	@Override
	public void removeAll() {
		entityManager.createQuery("DELETE FROM ProjectHasSkill").executeUpdate();

	}

	@Override
	public void removeByProject(Project project) {
		
		entityManager.createQuery("DELETE FROM ProjectHasSkill p WHERE p.project = :project").setParameter("project", project).executeUpdate();
		
	}
	

}
