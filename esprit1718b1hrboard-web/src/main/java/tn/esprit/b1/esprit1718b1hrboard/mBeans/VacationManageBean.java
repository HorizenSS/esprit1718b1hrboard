package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.entities.VacationType;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
public class VacationManageBean {
	private Date NewSdate;
	private Date NewEdate;
	private String sbm;
	private Vacation nv;
	private Vacation vtou;
	private Employee E;
	private VacationType vtselected;
	@EJB
	VacationServiceLocal VacService ;
	@EJB
	EmployeeServiceLocal EmpSer ;
	
	@PostConstruct
	public void init(){
		E = EmpSer.find(SessionUser.employeeConnected.getId());
		NewEdate = new Date();
		NewSdate = new Date();
		nv = null;
		nv = new Vacation();
		vtou = null;
		vtou = SessionUser.vacat;
	}
	public String CreateV(){
		Employee ee = new Employee();
		Long millis = System.currentTimeMillis();
		Date dt = new Date(millis);
		ee = EmpSer.find(SessionUser.employeeConnected.getId());
		nv.setStatus(null);
		nv.setSubmissionDate(dt);
//		Vacation Newv = new Vacation(sbm, null, dt, null, NewSdate, NewSdate, null, this.vtselected);
		nv.setEmployee(ee);
		VacService.save(nv);
		return "/template/YourVacationsList?faces-redirect=true";
	}
	public String Updatevac(){
		Employee ee = new Employee();
		Long millis = System.currentTimeMillis();
		Date dt = new Date(millis);
		ee = EmpSer.find(SessionUser.employeeConnected.getId());
		vtou.setStatus(null);
		vtou.setSubmissionDate(dt);
		vtou.setEmployee(ee);
		VacService.update(vtou);
		return "/template/YourVacationsList?faces-redirect=true";
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
	public VacationType getVtselected() {
		return vtselected;
	}
	public void setVtselected(VacationType vtselected) {
		this.vtselected = vtselected;
	}
	public Vacation getNv() {
		return nv;
	}
	public void setNv(Vacation nv) {
		this.nv = nv;
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
	public Vacation getVtou() {
		return vtou;
	}
	public void setVtou(Vacation vtou) {
		this.vtou = vtou;
	}
	public Employee getE() {
		return E;
	}
	public void setE(Employee e) {
		E = e;
	}
	
}
