package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOfferPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasSkillSeviceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.MD5;
import tn.esprit.b1.esprit1718b1hrboard.utils.RepeatPaginator;

@ManagedBean
@SessionScoped
public class JobOfferBean {

	private JobOffer jo = new JobOffer();
	private Candidate c = new Candidate();
	private Integer note = 5;
	private Integer note1 = 5;
	private Integer note2 = 5;
	private Boolean te = true;
	private RepeatPaginator paginator;
	private Integer nbjoboffer;
	private Integer nbcondidat;
	private Integer nbcondidatneeded;

	private JobOffer jobofferselected;

	@EJB
	JobOfferServiceLocal jobOfferServiceLocal;

	@EJB
	CandidateHasJobOfferServiceLocal candidateHasJobOfferServiceLocal;

	@EJB
	CandidateHasSkillSeviceLocal candidateHasSkillSeviceLocal;

	@EJB
	CandidateServiceLocal candidateServiceLocal;

	private List<JobOffer> ljo = new ArrayList<>();

	@PostConstruct
	public void init() {
		nbcondidat = candidateHasJobOfferServiceLocal.findAll().size();
		ljo = jobOfferServiceLocal.findAll();
		nbjoboffer=ljo.size();
		nbcondidatneeded = nbjoboffer *4 ;
		paginator = new RepeatPaginator(ljo);
	}

	public JobOffer getJo() {
		return jo;
	}

	public void setJo(JobOffer jo) {
		this.jo = jo;
	}

	public List<JobOffer> getLjo() {
		return ljo;
	}

	public void setLjo(List<JobOffer> ljo) {
		this.ljo = ljo;
	}

	public JobOffer getJobofferselected() {
		return jobofferselected;
	}

	public void setJobofferselected(JobOffer jobofferselected) {
		this.jobofferselected = jobofferselected;
	}

	public Candidate getC() {
		return c;
	}

	public void setC(Candidate c) {
		this.c = c;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Integer getNote1() {
		return note1;
	}

	public void setNote1(Integer note1) {
		this.note1 = note1;
	}

	public Integer getNote2() {
		return note2;
	}

	public void setNote2(Integer note2) {
		this.note2 = note2;
	}

	public Boolean getTe() {
		return te;
	}

	public void setTe(Boolean te) {
		this.te = te;
	}
	

	public RepeatPaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}
	
	
	public Integer getNbjoboffer() {
		return nbjoboffer;
	}

	public void setNbjoboffer(Integer nbjoboffer) {
		this.nbjoboffer = nbjoboffer;
	}

	public Integer getNbcondidat() {
		return nbcondidat;
	}

	public void setNbcondidat(Integer nbcondidat) {
		this.nbcondidat = nbcondidat;
	}

	public Integer getNbcondidatneeded() {
		return nbcondidatneeded;
	}

	public void setNbcondidatneeded(Integer nbcondidatneeded) {
		this.nbcondidatneeded = nbcondidatneeded;
	}

	public void next() {
		paginator.next();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&");
    }

	public String post(JobOffer job) {
		jobofferselected = job;
		return "/template/PostJobOfferCandidat?faces-redirect=true";
	}
	public Boolean switchte(){
		te=true;
		return true;
	}

	public String finalpost() {
		List<CandidateHasJobOffer> chjo = candidateHasJobOfferServiceLocal.findAll();
		Boolean test = false, test2 = false;
		if (candidateServiceLocal.findbymail(c) != null) {
			test2 = true;
		}
		for (CandidateHasJobOffer candidateHasJobOffer : chjo) {
			if(candidateHasJobOffer.getJobOffer().getId()==jobofferselected.getId()){
				test=true;
			}
		}

		if ((test2 == false)&&(test == false)) {
			/////////////////////////// Candidate
			if (candidateServiceLocal.findbymail(c) == null) {
				System.out.println("mahouch mawjoud");
				candidateServiceLocal.save(c);
			} else {
				System.out.println("mawjoud");
			}

			Candidate c2 = candidateServiceLocal.findbymail(c);
			///////////////////////// Candidate Has Skill

			CandidateHasSkillPk candidateHasSkillPk = new CandidateHasSkillPk();
			candidateHasSkillPk.setIdCandidate(c2.getId());
			candidateHasSkillPk.setIdSkill(jobofferselected.getJobOfferSkills().get(0).getSkill().getId());
			CandidateHasSkillPk candidateHasSkillPk1 = new CandidateHasSkillPk();
			candidateHasSkillPk1.setIdCandidate(c2.getId());
			candidateHasSkillPk1.setIdSkill(jobofferselected.getJobOfferSkills().get(1).getSkill().getId());
			CandidateHasSkillPk candidateHasSkillPk2 = new CandidateHasSkillPk();
			candidateHasSkillPk2.setIdCandidate(c2.getId());
			candidateHasSkillPk2.setIdSkill(jobofferselected.getJobOfferSkills().get(2).getSkill().getId());

			CandidateHasSkill candidateHasSkill = new CandidateHasSkill();
			candidateHasSkill.setCandidateHasSkillPk(candidateHasSkillPk);
			candidateHasSkill.setLevel(note);
			candidateHasSkill.setCertifcation(false);
			candidateHasSkill.setSkillExperience(1);

			CandidateHasSkill candidateHasSkill1 = new CandidateHasSkill();
			candidateHasSkill1.setCandidateHasSkillPk(candidateHasSkillPk1);
			candidateHasSkill1.setLevel(note1);
			candidateHasSkill1.setCertifcation(false);
			candidateHasSkill1.setSkillExperience(1);

			CandidateHasSkill candidateHasSkill2 = new CandidateHasSkill();
			candidateHasSkill2.setCandidateHasSkillPk(candidateHasSkillPk2);
			candidateHasSkill2.setLevel(note2);
			candidateHasSkill2.setCertifcation(false);
			candidateHasSkill2.setSkillExperience(1);

			List<CandidateHasSkill> chk = candidateHasSkillSeviceLocal.findAll();
			Boolean a = true, b = true, c = true;
			for (CandidateHasSkill cand : chk) {
				if ((cand.getCandidate().getId() == (c2.getId())) && (cand.getSkill()
						.getId() == (jobofferselected.getJobOfferSkills().get(0).getSkill().getId()))) {
					a = false;
				}
				if ((cand.getCandidate().getId() == (c2.getId())) && (cand.getSkill()
						.getId() == (jobofferselected.getJobOfferSkills().get(1).getSkill().getId()))) {
					b = false;
				}
				if ((cand.getCandidate().getId() == (c2.getId())) && (cand.getSkill()
						.getId() == (jobofferselected.getJobOfferSkills().get(2).getSkill().getId()))) {
					c = false;
				}

			}
			if (a == true) {
				candidateHasSkillSeviceLocal.save(candidateHasSkill);
			} else {
				candidateHasSkillSeviceLocal.update(candidateHasSkill);
			}
			if (b == true) {
				candidateHasSkillSeviceLocal.save(candidateHasSkill1);
			} else {
				candidateHasSkillSeviceLocal.update(candidateHasSkill1);
			}
			if (c == true) {
				candidateHasSkillSeviceLocal.save(candidateHasSkill2);
			} else {
				candidateHasSkillSeviceLocal.update(candidateHasSkill2);
			}
			///////////////////////////////// Candidate Has Job Offer
			CandidateHasJobOfferPk candidateHasJobOfferPk = new CandidateHasJobOfferPk();
			candidateHasJobOfferPk.setIdCandidate(c2.getId());
			candidateHasJobOfferPk.setIdJobOffer(jobofferselected.getId());
			CandidateHasJobOffer candidateHasJobOffer = new CandidateHasJobOffer();
			candidateHasJobOffer.setCandidateHasJobOfferPk(candidateHasJobOfferPk);
			candidateHasJobOffer.setApplicationStatus(false);
			candidateHasJobOffer
					.setApplicationDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			Float bff = 0f;
			Float coef = jobofferselected.getJobOfferSkills().get(0).getSkill().getNote()
					+ jobofferselected.getJobOfferSkills().get(1).getSkill().getNote()
					+ jobofferselected.getJobOfferSkills().get(2).getSkill().getNote();
			bff = note * jobofferselected.getJobOfferSkills().get(0).getSkill().getNote()
					+ note1 * jobofferselected.getJobOfferSkills().get(1).getSkill().getNote()
					+ note2 * jobofferselected.getJobOfferSkills().get(2).getSkill().getNote();
			candidateHasJobOffer.setNote(bff / coef);
			candidateHasJobOfferServiceLocal.save(candidateHasJobOffer);
			return "/template/joboffercandidat?faces-redirect=true";
		} else {
			te=false;
			System.out.println("deja posta");
			return "";
		}
	}
}
