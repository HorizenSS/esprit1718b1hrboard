package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Document;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class DocumentService extends GenericDAO<Document> implements DocumentServiceRemote, DocumentServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public DocumentService() {
		super(Document.class);
	}

}
