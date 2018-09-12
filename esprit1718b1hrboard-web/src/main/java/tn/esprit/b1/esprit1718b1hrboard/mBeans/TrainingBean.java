package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
@SessionScoped
public class TrainingBean {

	private Training tr = new Training();
	@EJB
	TrainingServiceLocal trainingServiceLocal;

	@EJB
	EmployeeHasTrainingServiceLocal employeeHasTrainingServiceLocal;
	
    private Employee logEmployee ;
	private Integer trainingId;
	private int employeSelectedId;

	private String employeeNote;
	private Integer noteTrainer;
	private Boolean attempt;
  
	private List<Training> listTraining;
	
	private List<Employee> listEmployee;
	
	private Set<Employee> list ;

	@PostConstruct
	public void init() {
		listTraining = trainingServiceLocal.findAll();
		logEmployee = SessionUser.employeeConnected;
		System.out.println(logEmployee);
		System.out.println(		employeeHasTrainingServiceLocal.getEmployeeToNote(7, 68));
	}

	public Training getTraining() {
		return tr;
	}

	public void setTraining(Training tr) {
		this.tr = tr;
	}

	public List<Training> getListTraining() {
		return listTraining;
	}

	public void setListTraining(List<Training> listTraining) {
		this.listTraining = listTraining;
	}



	public int getTypeTraining(Training tr) {
		if (tr.getTrainingtype().equals("NORMAL")) {
			return 1;
		} else {
			return 0;
		}

	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public Employee getLogEmployee() {
		
		return logEmployee;
		
	}

	public void setLogEmployee(Employee logEmployee) {
		this.logEmployee = logEmployee;
	}

	public Employee getEmp(){
		return logEmployee;
	}

	public Integer getTrainingId() {
		System.out.println(trainingId.toString());
		return trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	
	public void getNumber(){
		
	}
	
	public void azerty(){
		
		listEmployee = employeeHasTrainingServiceLocal.getAllEmployeeByTraining(trainingId);
		System.out.println(trainingId);
		list = listEmployee.stream().collect(Collectors.toSet());
		listEmployee = list.stream().collect(Collectors.toList());
		for(Employee emp : listEmployee){
			System.out.println(emp.toString());
		}
	}

	public int getEmployeSelectedId() {
		return employeSelectedId;
	}

	public void setEmployeSelectedId(int employeSelectedId) {
		this.employeSelectedId = employeSelectedId;
	}
	
	
	public void majdi(){
		System.out.println("xxxxxxxxxxxxxxxxxxxxx"+employeSelectedId);
		System.out.println(listEmployee);
	}
	

	public String getEmployeeNote() {
		return employeeNote;
	}

	public void setEmployeeNote(String employeeNote) {
		this.employeeNote = employeeNote;
	}

	public Integer getNoteTrainer() {
		return noteTrainer;
	}

	public void setNoteTrainer(Integer noteTrainer) {
		this.noteTrainer = noteTrainer;
	}

	public Boolean getAttempt() {
		return attempt;
	}

	public void setAttempt(Boolean attempt) {
		this.attempt = attempt;
	}

	public void modifier() {
		
		EmployeeHasTraining employe=employeeHasTrainingServiceLocal.getEmployeeToNote(trainingId, employeSelectedId);
		System.out.println(employe.toString());
		if(!employe.getEmployeeNote().equals(null)){
			this.setEmployeeNote(employe.getEmployeeNote());}
		else
		{this.setEmployeeNote("0");}
		
		this.setAttempt(employe.getAttempt());
		this.setNoteTrainer(employe.getNoteTrainer());
		
	}
	public void mettreAjourEmploye() {
		
		EmployeeHasTraining employe=employeeHasTrainingServiceLocal.getEmployeeToNote(trainingId, employeSelectedId);
		System.out.println(employe.toString());
		employe.setAttempt(attempt);
		employe.setNoteTrainer(noteTrainer);
		employe.setEmployeeNote(employeeNote);
		
		employeeHasTrainingServiceLocal.updateEmployeHasTraining(employe);
		
	}
	
}
