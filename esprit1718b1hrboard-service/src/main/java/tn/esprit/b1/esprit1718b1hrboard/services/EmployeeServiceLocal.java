package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface EmployeeServiceLocal extends IGenericDAO<Employee> {
	
	Employee accessAccount(Account account);
	Employee findbyname(String name);
	Employee findByCode (String code ); 
	List<Employee> EmployeeWithoutAccount (Account account , Employee employe);
	List<Employee> EmployeeWithAccountToDelete(Account account); 
	List<Employee> EmployeeWhoWillBeDelete();
	Employee Employeeafficher();
	List<Employee> BestEmployeeMounth();
	List<Employee> BestEmployeeYear() ;
	//List<Employee> EmployeeById() ;
	Employee EmployeeByResignation(Resignation R);
	List<Employee> EmployeeBySkill(String skill);
	public Employee findbymail(String name);
	public List<Employee> EmployeeHasSkill(String skill) ;
	
	List<Employee> findAllEmployeesByProject(Project project);

	Employee findbynameComplet(String Firstname,String LastName);
	


}
