package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface EmployeeHasSkillServiceRemote extends IGenericDAO<EmployeeHasSkill> {
	
	 List<EmployeeHasSkill> findSkillsDetailsByEmployee(Employee employee);
	 List<Skill> findSkillsDetailsByEmployeeByOrder(int employeeId);
	 List<EmployeeHasSkill> findEmployeeBySkill(String skill);
}
