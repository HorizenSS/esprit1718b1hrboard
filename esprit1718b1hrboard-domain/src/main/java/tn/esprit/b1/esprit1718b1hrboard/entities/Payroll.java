package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

public class Payroll implements Serializable  {
	
	
	
	private int id ;
	private double GrossSalary;
	private double NetSalary;
	
	
	
	



	public Payroll() {
		super();
		// TODO Auto-generated constructor stub
	}







	public Payroll(double grossSalary, double netSalary) {
		super();
		GrossSalary = grossSalary;
		NetSalary = netSalary;
	}






	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}







	public void setId(int id) {
		this.id = id;
	}







	public double getGrossSalary() {
		return GrossSalary;
	}







	public void setGrossSalary(double grossSalary) {
		GrossSalary = grossSalary;
	}







	public double getNetSalary() {
		return NetSalary;
	}







	public void setNetSalary(double netSalary) {
		NetSalary = netSalary;
	}



	

	
	
	
	
	

}
