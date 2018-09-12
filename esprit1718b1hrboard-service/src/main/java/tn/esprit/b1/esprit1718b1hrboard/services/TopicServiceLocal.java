package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;
@Local
public interface TopicServiceLocal extends IGenericDAO<Topic> {
	public List<Topic> findbysubject(String name);


}
