package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Reward;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface RewardServiceLocal extends IGenericDAO<Reward> {

}
