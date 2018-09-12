package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface EmployeeHasTrainingServiceLocal extends IGenericDAO<EmployeeHasTraining> {
	public List<Employee> getAllEmployeeByTraining(int trainingId);
	public EmployeeHasTraining getEmployeeToNote(int trainingId,int employeeId );
	public void updateEmployeHasTraining(EmployeeHasTraining emp);
}
