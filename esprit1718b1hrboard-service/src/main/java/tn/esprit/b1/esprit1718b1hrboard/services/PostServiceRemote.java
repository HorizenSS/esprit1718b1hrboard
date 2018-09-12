package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface PostServiceRemote extends IGenericDAO<Post>{
	
	public Post findPostCode(String Code);
	public List<Post> findPostsByDep(Departement dep);

}
