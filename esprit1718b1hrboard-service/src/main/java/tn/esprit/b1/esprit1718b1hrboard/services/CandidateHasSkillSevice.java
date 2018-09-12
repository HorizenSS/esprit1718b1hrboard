package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class CandidateHasSkillSevice extends GenericDAO<CandidateHasSkill>
		implements CandidateHasSkillSeviceRemote, CandidateHasSkillSeviceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public CandidateHasSkillSevice() {
		super(CandidateHasSkill.class);
	}

}
