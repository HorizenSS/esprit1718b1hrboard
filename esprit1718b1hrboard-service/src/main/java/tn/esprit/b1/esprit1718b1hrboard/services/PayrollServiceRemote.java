package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Payroll;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface PayrollServiceRemote extends IGenericDAO<Payroll> {
	
	
	public double CalculateGrossSalary(double basic , double overTime, double hourWage ,double hra ,double bonus  );
	
	
	public double CalculateNetSalary(double grossSalary , double SocialSecurityTax , double Medicare  );
	
	
	public void add (Payroll payroll , Employee emp);

}
