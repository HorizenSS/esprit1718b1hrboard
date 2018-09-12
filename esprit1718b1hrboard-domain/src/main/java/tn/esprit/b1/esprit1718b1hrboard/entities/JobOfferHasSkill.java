package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobOfferHasSkill  implements Serializable {

private static final long serialVersionUID = 1L;
	
	private JobOfferHasSkillPk jobOfferHasSkillPk;
	private Integer level;
	
	

	private JobOffer jobOffer;
	private Skill skill;
	
	
	public JobOfferHasSkill() {
		super();
	}


	public JobOfferHasSkill(JobOfferHasSkillPk jobOfferHasSkillPk, Integer level, JobOffer jobOffer, Skill skill) {
		super();
		this.jobOfferHasSkillPk = jobOfferHasSkillPk;
		this.level = level;
		this.jobOffer = jobOffer;
		this.skill = skill;
	}


	public JobOfferHasSkill(Integer level, JobOffer jobOffer, Skill skill) {
		super();
		this.level = level;
		this.jobOffer = jobOffer;
		this.skill = skill;
	}

	@EmbeddedId
	public JobOfferHasSkillPk getJobOfferHasSkillPk() {
		return jobOfferHasSkillPk;
	}


	public void setJobOfferHasSkillPk(JobOfferHasSkillPk jobOfferHasSkillPk) {
		this.jobOfferHasSkillPk = jobOfferHasSkillPk;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}

	@ManyToOne
	@JoinColumn(name = "idJobOffer", referencedColumnName = "id", insertable = false, updatable = false)
	public JobOffer getJobOffer() {
		return jobOffer;
	}

	
	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	@ManyToOne
	@JoinColumn(name = "idSkill", referencedColumnName = "id", insertable = false, updatable = false)
	public Skill getSkill() {
		return skill;
	}


	public void setSkill(Skill skill) {
		this.skill = skill;
	}


	
	
}
