package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface DepartementServiceLocal extends IGenericDAO<Departement> {
	public List<Departement> findTopDeps();

}
