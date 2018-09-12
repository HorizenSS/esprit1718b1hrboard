package tn.esprit.b1.esprit1718b1hrboard.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface PostServiceLocal extends IGenericDAO<Post> {

	Post findPostCode(String Code);

}
