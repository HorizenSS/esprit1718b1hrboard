package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface DepartementServiceRemote extends IGenericDAO<Departement> {
	
	public List<Departement> findTopDeps();
	public void RemoveDepartmentHierarchy(Departement dep);
	public List<Departement> findDepsByNames(String Name);
	

}
