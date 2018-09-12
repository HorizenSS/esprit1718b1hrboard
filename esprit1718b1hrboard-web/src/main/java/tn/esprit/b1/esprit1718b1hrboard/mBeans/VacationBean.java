package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.entities.VacationType;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
@SessionScoped
public class VacationBean {
	
	private List<VacationType> lvt = new ArrayList<>();
	private Date NewSdate;
	private Date NewEdate;
	private String sbm;
	private Vacation cvac = new Vacation();
	private String newvacca;
	private VacationType vtselected;
	@EJB
	VacationServiceLocal VacService ;
	@EJB
	EmployeeServiceLocal EmpSer ;
	
	@PostConstruct
	public void init(){
		NewEdate = new Date();
		NewSdate = new Date();
		sbm = "";
		vtselected = VacationType.PERSONALDAYS;
	}
	public String directToNewVacation(){
		return "/template/NewVacation?faces-redirect=true";
	}
	public void CreateV(){
		Employee ee = new Employee();
		Long millis = System.currentTimeMillis();
		Date dt = new Date(millis);
		ee = EmpSer.find(SessionUser.employeeConnected.getId());
		Vacation Newv = new Vacation(sbm, "", dt, null, null, null, null, this.vtselected);
		Newv.setEmployee(ee);
		VacService.save(Newv);
	}
	public String Updatevac(int Id){
		Vacation vv = VacService.find(Id);
		vv.setVacationType(vtselected);
		Long millis = System.currentTimeMillis();
		Date dt = new Date(millis);
		vv.setSubmissionDate(dt);
		VacService.update(vv);
		return "/template/YourVacationsList?faces-redirect=true";
	}
	public List<Vacation> AllVacations(){
		return VacService.findAll().stream().filter(e -> e.getEmployee().getId().equals(SessionUser.employeeConnected.getId())).collect(Collectors.toList());
	}
	public String ToCheckVacation(Vacation v){
		cvac = v;
		SessionUser.vacat = cvac;
		if(cvac.getStatus()!= null){
		return "/template/CheckVacation?faces-redirect=true";
		}else{
			return "/template/UpdateVacation?faces-redirect=true";
		}
	}
	public int Etat(Boolean b){
		System.out.println(b);
		if(b!=null){
			if(b==true){
				return 1;
			}else{
				return 0;
			}
		}else{
			return -1;
		}
	}
	
	public String DatesToString(Date d){
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		if(d != null){
		String str = sd.format(d);
		return str;
		}else{
			return "";
		}
	}
	
	public Vacation affectVacation(){
		Vacation v = VacService.find(1);
		return v;
	}
	public Vacation getCvac() {
		return cvac;
	}
	public void setCvac(Vacation cvac) {
		this.cvac = cvac;
	}
	public List<VacationType> getLvt() {
		lvt.removeAll(lvt);
		lvt.add(VacationType.SICKLEAVE);
		lvt.add(VacationType.PERSONALDAYS);
		lvt.add(VacationType.BEREAVEMENT);
		lvt.add(VacationType.MATERNITYLEAVE);
		lvt.add(VacationType.OTHER);
		return lvt;
	}
	public void setLvt(List<VacationType> lvt) {
		this.lvt = lvt;
	}
	public VacationType getVtselected() {
		return vtselected;
	}
	public void setVtselected(VacationType vtselected) {
		this.vtselected = vtselected;
	}
	public Date getNewSdate() {
		return NewSdate;
	}
	public void setNewSdate(Date newSdate) {
		NewSdate = newSdate;
	}
	public Date getNewEdate() {
		return NewEdate;
	}
	public void setNewEdate(Date newEdate) {
		NewEdate = newEdate;
	}
	public String getSbm() {
		return sbm;
	}
	public void setSbm(String sbm) {
		this.sbm = sbm;
	}
	public String getNewvacca() {
		return newvacca;
	}
	public void setNewvacca(String newvacca) {
		this.newvacca = newvacca;
	}
	
	

}
