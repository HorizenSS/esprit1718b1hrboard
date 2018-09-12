package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface ProjectServiceRemote extends IGenericDAO<Project> {

	List<Project> findAllProjectsByEmployee(Employee employee);

	List<Project> findProjectsByEmployee(Employee employee);

	List<Project> findProjectByMaster(Employee master);
	List<Project> findProjectByname(String master);


	public void	removeProject(Project project);

}
