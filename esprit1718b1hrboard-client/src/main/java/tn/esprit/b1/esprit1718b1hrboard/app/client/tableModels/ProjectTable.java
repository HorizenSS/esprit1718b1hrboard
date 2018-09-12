package tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class ProjectTable {
	private final SimpleStringProperty pojects;
	private final SimpleStringProperty datedebut;
	private final SimpleStringProperty datefin;
	
	public ProjectTable(String p, String date, String date2) {
		super();
		this.pojects = new SimpleStringProperty(p);
		this.datedebut =  new SimpleStringProperty( date);
		this.datefin =  new SimpleStringProperty( date2);
	}
	

	public String getPojects() {

		return pojects.get();
	}

	public String getDatedebut() {

		return datedebut.get();
	}
	public String getDatefin() {

		return datefin.get();
	}
	public SimpleStringProperty projectsProperty() {
		return  pojects;
	}

	public SimpleStringProperty datefinProperty() {
		return datefin ;
	}

	public SimpleStringProperty datedebutProperty() {
		return datedebut;
	}
	@Override
	public String toString() {
		return "EmployeeTable [Projects=" + pojects + ", datefin=" + datefin + ", datedebut=" + datedebut + "]";
	}

}

