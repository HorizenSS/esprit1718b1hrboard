package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tax implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double HRA ;
	private double bonus;
	private double SocialSecurityTax;
	private double MedicareTax;
	private int PaymentDate;
	
		
	
	
	
	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Tax(Integer id, double hRA, double bonus, double socialSecurityTax, double medicareTax, int paymentDate) {
		super();
		this.id = id;
		HRA = hRA;
		this.bonus = bonus;
		SocialSecurityTax = socialSecurityTax;
		MedicareTax = medicareTax;
		PaymentDate = paymentDate;
	}





	public Tax(double hRA, double bonus, double socialSecurityTax, double medicareTax, int paymentDate) {
		super();
		HRA = hRA;
		this.bonus = bonus;
		SocialSecurityTax = socialSecurityTax;
		MedicareTax = medicareTax;
		PaymentDate = paymentDate;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public double getHRA() {
		return HRA;
	}





	public void setHRA(double hRA) {
		HRA = hRA;
	}





	public double getBonus() {
		return bonus;
	}





	public void setBonus(double bonus) {
		this.bonus = bonus;
	}





	public double getSocialSecurityTax() {
		return SocialSecurityTax;
	}





	public void setSocialSecurityTax(double socialSecurityTax) {
		SocialSecurityTax = socialSecurityTax;
	}





	public double getMedicareTax() {
		return MedicareTax;
	}





	public void setMedicareTax(double medicareTax) {
		MedicareTax = medicareTax;
	}





	public int getPaymentDate() {
		return PaymentDate;
	}





	public void setPaymentDate(int paymentDate) {
		PaymentDate = paymentDate;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
