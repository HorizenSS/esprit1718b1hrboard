package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Contract;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface ContractServiceLocal extends IGenericDAO<Contract> {

}
