package tn.esprit.b1.esprit1718b1hrboard.utilities;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b1.esprit1718b1hrboard.entities.Access;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.User;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private AccountServiceRemote as;

	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
		User user = new User("user", "u", "u", "user@bitbox.tn");
		userServiceLocal.update(user);
	}
}
