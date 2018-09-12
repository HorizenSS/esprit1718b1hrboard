package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Payroll;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class PayrollService extends GenericDAO<Payroll> implements  PayrollServiceLocal  ,PayrollServiceRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public PayrollService() {
		super(Payroll.class);
	}
	@Override
	public double CalculateGrossSalary(double basic, double overTime, double hourWage ,double hra ,double bonus) {

		double h = basic*hra/100;
		double b = basic*bonus/100;
		
		double GrossSalary = basic+overTime*hourWage+h+b;
		
		
		
		return GrossSalary;
	}
	@Override
	public double CalculateNetSalary(double grossSalary, double SocialSecurityTax, double Medicare) {

		double s = grossSalary*SocialSecurityTax/100;
		double m = grossSalary*Medicare/100;
		
		double NetSalary = grossSalary-s-m;
		
		
		
		
		return NetSalary;
	}
	@Override
	public void add(Payroll payroll, Employee emp) {

		entityManager.persist(payroll);
		emp.setPayroll(payroll);
		
		entityManager.refresh(payroll);
		
		
		
	}
	
	
	
	
	
	

}
