package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class TrainingService extends GenericDAO<Training> implements TrainingServiceRemote, TrainingServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public TrainingService() {
		super(Training.class);
	}
	public int addTraining(Training tr){
		entityManager.persist(tr);
		return tr.getId();
	}

}
