package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.ActivityLog;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class ActivityLogService extends GenericDAO<ActivityLog>
		implements ActivityLogServiceRemote, ActivityLogServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public ActivityLogService() {
		super(ActivityLog.class);
	}

}
