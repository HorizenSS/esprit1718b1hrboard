package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.b1.esprit1718b1hrboard.entities.VacationType;

@ManagedBean
@ApplicationScoped
public class VtData {
	
	public VacationType[] getVT(){
		return VacationType.values();
	}
}