package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.TimeLine;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface TimeLineServiceLocal extends IGenericDAO<TimeLine> {
	
	public void postToLinkedIn(String pst);
	public void supprim(int line);


}
