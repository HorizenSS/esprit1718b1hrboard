package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class EmployeeHasTrainingService extends GenericDAO<EmployeeHasTraining>
		implements EmployeeHasTrainingServiceRemote, EmployeeHasTrainingServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public EmployeeHasTrainingService() {
		super(EmployeeHasTraining.class);
	}

	@Override
	public List<String> getAllEmployeeNamesByTraining(int trainingId) {
		
			Training tr = entityManager.find(Training.class, trainingId);
			List<EmployeeHasTraining> EmpList = tr.getFormParticipations();
			
			
			List<String> names = new ArrayList<>();
			for(EmployeeHasTraining emp : EmpList){
				names.add(emp.getEmployee().getFirstName());
			}
			
			return names;
	
	}

	@Override
	public List<Employee> getAllEmployeeByTraining(int trainingId) {
		Training tr = entityManager.find(Training.class, trainingId);
		List<EmployeeHasTraining> EmpList = tr.getFormParticipations();
		
		
		List<Employee> names = new ArrayList<>();
		for(EmployeeHasTraining emp : EmpList){
			names.add(emp.getEmployee());
		}
		
		return names;
	}

	@Override
	public EmployeeHasTraining getEmployeeToNote(int trainingId, int employeeId) {
		EmployeeHasTraining emp = new EmployeeHasTraining();
		TypedQuery<EmployeeHasTraining> query = entityManager.createQuery("SELECT e FROM EmployeeHasTraining e WHERE e.training.id = :trainingId and e.employee.id=:employeeId",
				EmployeeHasTraining.class);
		emp = (EmployeeHasTraining) query.setParameter("trainingId",trainingId ).setParameter("employeeId",employeeId ).getSingleResult();
		return emp;
	}

	@Override
	public void updateEmployeHasTraining(EmployeeHasTraining emp) {
		entityManager.merge(emp);
		
	}
	
}
