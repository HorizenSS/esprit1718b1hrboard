package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CandidateHasSkill implements Serializable {

	// class association 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CandidateHasSkillPk candidateHasSkillPk;
	private Integer level;
	private Boolean certifcation;
	private Integer skillExperience;

	private Candidate candidate;
	private Skill skill;
	public CandidateHasSkill() {
		super();
	}
	
	public CandidateHasSkill(Integer level, Boolean certifcation, Integer skillExperience, Candidate candidate,
			Skill skill) {
		super();
		this.level = level;
		this.certifcation = certifcation;
		this.skillExperience = skillExperience;
		this.candidate = candidate;
		this.skill = skill;
	}

	public CandidateHasSkill(CandidateHasSkillPk candidateHasSkillPk, Integer level, Boolean certifcation,
			Integer skillExperience) {
		super();
		this.candidateHasSkillPk = candidateHasSkillPk;
		this.level = level;
		this.certifcation = certifcation;
		this.skillExperience = skillExperience;
	}

	@EmbeddedId
	public CandidateHasSkillPk getCandidateHasSkillPk() {
		return candidateHasSkillPk;
	}

	public void setCandidateHasSkillPk(CandidateHasSkillPk candidateHasSkillPk) {
		this.candidateHasSkillPk = candidateHasSkillPk;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getCertifcation() {
		return certifcation;
	}

	public void setCertifcation(Boolean certifcation) {
		this.certifcation = certifcation;
	}

	public Integer getSkillExperience() {
		return skillExperience;
	}

	public void setSkillExperience(Integer skillExperience) {
		this.skillExperience = skillExperience;
	}

	@ManyToOne
	@JoinColumn(name = "idCandidate", referencedColumnName = "id", insertable = false, updatable = false)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	@JoinColumn(name = "idSkill", referencedColumnName = "id", insertable = false, updatable = false)
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "CandidateHasSkill [candidateHasSkillPk=" + candidateHasSkillPk + ", level=" + level + ", certifcation="
				+ certifcation + ", skillExperience=" + skillExperience + "]" ;
	}
	
	
}
