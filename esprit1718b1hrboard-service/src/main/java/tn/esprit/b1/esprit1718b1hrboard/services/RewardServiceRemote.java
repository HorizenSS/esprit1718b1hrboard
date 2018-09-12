package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Reward;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface RewardServiceRemote extends IGenericDAO<Reward> {
   Reward  AffecterRewardsToEmployee(Employee employee , Reward reward );
}
