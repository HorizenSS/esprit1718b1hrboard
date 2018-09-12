package tn.esprit.b1.esprit1718b1hrboard.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface SkillServiceRemote extends IGenericDAO<Skill> {

	public List<Skill> findSkillsByProject(Project project);
	public List<Skill> findSkillsByEmployee(Employee employee);

	public Skill SkillByname(String name);
	
	public int saveReturn(Skill skill);

}
