package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.User;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.MD5;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

import javax.ejb.EJB;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	
	private boolean isLogged = false;
	private Employee user ;
	private Account accountuser = new Account();
	private Integer i = 50 ;
	private boolean var = true;
	@EJB
	AccountServiceLocal accountServiceLocal ;
	
	public String doLogin() {
		String navigateTo = "";
		
		Account account = accountServiceLocal.login(accountuser.getLogin(), MD5.hash(accountuser.getPassword()));
		if (account != null) {
			isLogged = true;
			user = account.getEmployee();
			SessionUser.employeeConnected = user;
			navigateTo = "/template/employeeProfile?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Veuillez insérer un login et un mot de passe valide", ""));
			System.out.println("errreerr");
			var = false;
			return null;
		}
		return navigateTo;

	}


	public Integer getI() {
		return i;
	}


	public void setI(Integer i) {
		this.i = i;
	}


	public boolean isVar() {
		return var;
	}


	public void setVar(boolean var) {
		this.var = var;
	}


	public boolean isLogged() {
		return isLogged;
	}


	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}


	public Employee getUser() {
		return user;
	}


	public void setUser(Employee user) {
		this.user = user;
	}


	public Account getAccountuser() {
		return accountuser;
	}


	public void setAccountuser(Account accountuser) {
		this.accountuser = accountuser;
	}

}
