package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class AccountService extends GenericDAO<Account> implements AccountServiceRemote, AccountServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public AccountService() {
		super(Account.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Account login(String login, String password) {
		Account account = null;
		try {
			account = entityManager
					.createQuery("SELECT a FROM Account a WHERE a.login=:l AND a.password=:p", Account.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
		} catch (Exception e) {
		}
		return account;
	}

	@Override
	public Account searchaccout(Employee employe) {
		Account a = null;

		TypedQuery<Account> query = entityManager.createQuery("SELECT e FROM Account e WHERE e.employee = :code", Account.class);
		a = (Account) query.setParameter("code", employe).getSingleResult();
		return a;		
	}

	


}
