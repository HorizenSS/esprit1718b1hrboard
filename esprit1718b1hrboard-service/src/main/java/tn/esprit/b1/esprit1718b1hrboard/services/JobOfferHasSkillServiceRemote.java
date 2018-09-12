package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;


@Remote
public interface JobOfferHasSkillServiceRemote extends IGenericDAO<JobOfferHasSkill> {

}
