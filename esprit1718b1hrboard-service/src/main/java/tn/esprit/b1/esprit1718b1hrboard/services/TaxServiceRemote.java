package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Tax;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;
@Remote
public interface TaxServiceRemote extends IGenericDAO<Tax> {
	
	public Tax findById() ;
	

}
