package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Delay;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface DelayServiceRemote extends IGenericDAO<Delay> {

	List<Delay> findAllDelayByEmployee(Employee employee);

}
