package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RechercheBean {

	
	private String testajaxinput="aaa";
	private String testajaxoutput="bbb";
	
	
	public String getTestajaxinput() {
		return testajaxinput;
	}
	public void setTestajaxinput(String testajaxinput) {
		this.testajaxinput = testajaxinput;
	}
	public String getTestajaxoutput() {
		return testajaxoutput;
	}
	public void setTestajaxoutput(String testajaxoutput) {
		this.testajaxoutput = testajaxoutput;
	}
	
	public void rechercheajax(){
		testajaxoutput = testajaxinput;

	}
}
