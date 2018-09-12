package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.TimeLine;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;
import tn.esprit.b1.esprit1718b1hrboard.utilities.PostLinkedIn;

@Stateless
public class TimeLineService extends GenericDAO<TimeLine> implements TimeLineServiceLocal{

	@PersistenceContext
	private EntityManager entityManager;
	
	public TimeLineService() {
		super(TimeLine.class);
	}

	@Override
	public void postToLinkedIn(String pst) {
		PostLinkedIn linkedIn = new PostLinkedIn();
		linkedIn.postToMyLinkedIn(pst);	
	}

	@Override
	public void supprim(int line) {
			
		entityManager.createQuery("DELETE FROM TimeLine t WHERE t.id = :line").setParameter("line",line)
		.executeUpdate();
		
	}

}
