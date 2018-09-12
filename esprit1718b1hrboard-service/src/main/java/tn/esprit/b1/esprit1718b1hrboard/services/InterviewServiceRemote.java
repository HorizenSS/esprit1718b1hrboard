package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface InterviewServiceRemote extends IGenericDAO<Interview>{
	public Interview findByName(String name);
	public void addinterview(Interview i);
	public void removeInterview(Interview i);
}
