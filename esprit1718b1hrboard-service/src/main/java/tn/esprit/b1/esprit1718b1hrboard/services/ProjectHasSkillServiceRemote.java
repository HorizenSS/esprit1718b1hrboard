package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface ProjectHasSkillServiceRemote extends IGenericDAO<ProjectHasSkill> {
	
	public ProjectHasSkill find(Project project, Skill skill);
	public void removeAll();
	public void removeByProject(Project project);
	

}
