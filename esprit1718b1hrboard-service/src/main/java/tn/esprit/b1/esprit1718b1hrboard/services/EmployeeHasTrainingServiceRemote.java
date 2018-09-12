package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface EmployeeHasTrainingServiceRemote extends IGenericDAO<EmployeeHasTraining> {
	public List<String> getAllEmployeeNamesByTraining(int trainingId);

}
