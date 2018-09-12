package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface AccountServiceLocal extends IGenericDAO<Account> {
	public Account login(String login, String password);
	public Account searchaccout(Employee employe);

}
