package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public  class TopicService extends GenericDAO<Topic> implements  TopicServiceLocal{
	@PersistenceContext
	private EntityManager entityManager;
	public TopicService() {
		super(Topic.class);
	}

	@Override
	public List<Topic> findbysubject(String name) {
		
		List<Topic> tp = new   ArrayList<Topic>();
		List<Topic>e = new   ArrayList<Topic>();
				super.findAll();
		for (Topic ee : e) {
			if(!tp.equals(null)){
				if ((ee.getSubject() == name))
					tp.add(ee);
			}
			
		}
		return tp;
	}

}
