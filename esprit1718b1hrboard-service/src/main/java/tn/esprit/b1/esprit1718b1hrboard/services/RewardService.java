package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Reward;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class RewardService extends GenericDAO<Reward> implements RewardServiceRemote, RewardServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public RewardService() {
		super(Reward.class);
	}

	@Override
	public Reward AffecterRewardsToEmployee(Employee employee , Reward reward) {
		Reward c = entityManager.find(Reward.class, reward.getId());
		Employee emp = entityManager.find(Employee.class, employee.getId());	
		 c.setEmployee(emp);
		 return c ;
	}

}
