package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.TimeLine;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TimeLineServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
@SessionScoped
public class EmployeeProfileBean {

	@EJB
	EmployeeServiceLocal employeeService;

	@EJB
	ProjectServiceLocal projectService;

	@EJB
	TaskServiceLocal taskService;

	@EJB
	TimeLineServiceLocal timeLineService;

	/* Managed Bean Injection -> To verify config */
	@ManagedProperty(value = "#{loginBean}")
	LoginBean loginBean;

	// Employee employeeSession = loginBean.getUser();

	// public Employee getEmployeeSession() {
	// return employeeSession;
	// }
	//
	// public void setEmployeeSession(Employee employeeSession) {
	// this.employeeSession = employeeSession;
	// }

	Employee employeeProfile = SessionUser.employeeConnected;
	List<EmployeeHasSkill> skillsEmp = new ArrayList<>();
	List<Project> projectsEmp = new ArrayList<>();
	private String postContent;
	private String ajaxPost;

	private List<TimeLine> timeline = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (EmployeeHasSkill employeeHasSkill : employeeProfile.getSpecificationsSkills()) {
			// System.out.println(employeeHasSkill.getSkill().getName());
			skillsEmp.add(employeeHasSkill);
		}
		projectsEmp = projectService.findAllProjectsByEmployee(employeeProfile);

		timeline.clear();
		timeline.addAll(timeLineService.findAll());
		Collections.reverse(timeline);

		System.out.println(timeline);

		// System.out.println(employeeProfile.getId());
		// System.out.println(employeeProfile.getFirstName());
		// System.out.println(employeeProfile.getLastName());

	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Employee getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(Employee employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	public List<EmployeeHasSkill> getSkillsEmp() {
		return skillsEmp;
	}

	public void setSkillsEmp(List<EmployeeHasSkill> skillsEmp) {
		this.skillsEmp = skillsEmp;
	}

	public List<Project> getProjectsEmp() {
		return projectsEmp;
	}

	public void setProjectsEmp(List<Project> projectsEmp) {
		this.projectsEmp = projectsEmp;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public List<TimeLine> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<TimeLine> timeline) {
		this.timeline = timeline;
	}

	public String getAjaxPost() {
		return ajaxPost;
	}

	public void setAjaxPost(String ajaxPost) {
		this.ajaxPost = ajaxPost;
	}

	public void updateEmployeeGneraleInfos() {

		employeeService.update(employeeProfile);
		System.out.println("successfuly update !!!");
	}

	public int skillColor(Float note) {
		if (note < 5) {
			return 1;
		} else {
			return 2;
		}

	}

	public int taskImportance(Float val) {
		if (val <= 3.5) {
			return 1;
		} else if (val > 3.5 && val < 7.0) {
			return 2;
		} else {
			return 3;

		}

	}

	public int taskProgress(Float val) {
		if (val <= 35) {
			return 1;
		} else if (val > 35 && val < 70) {
			return 2;
		} else {
			return 3;

		}

	}

	public void addToTimeLine() {

		TimeLine line = new TimeLine();
		line.setContent(postContent);
		line.setEmployee(employeeProfile);

		timeLineService.save(line);
		timeline.clear();
		timeline.addAll(timeLineService.findAll());
		Collections.reverse(timeline);
		postContent = null;

	}

	public void postInLinked(String post) {

		// timeLineService.postToLinkedIn(post);

		System.out.println("deleeeeeeeeete !!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!"+post);
	}

	public String deleteTimeLine(int id) {

		System.out.println("deleeeeeeeeete !!!!!! "+id);
		
		TimeLine timeLine;
		timeLine = timeLineService.find(id);
		
		System.out.println(timeLine);

		timeLineService.supprim(id);
		timeline.clear();
		timeline.addAll(timeLineService.findAll());
		Collections.reverse(timeline);
		
		return null;
	}
}
