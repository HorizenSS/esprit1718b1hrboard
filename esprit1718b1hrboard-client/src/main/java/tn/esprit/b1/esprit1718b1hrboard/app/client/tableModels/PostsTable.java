package tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels;

import javafx.beans.property.SimpleStringProperty;

public class PostsTable {
	
	
	private final SimpleStringProperty code;
	private final SimpleStringProperty entitled;
	private final SimpleStringProperty department;
	private final SimpleStringProperty description;
	
	
	public PostsTable(String code, String entitled, String department,String description) {
		this.code = new SimpleStringProperty(code);
		this.entitled = new SimpleStringProperty(entitled);
		this.department = new SimpleStringProperty(department);
		this.description = new SimpleStringProperty(description);
		
	}
	
	
	public void setId(String code) {
		this.code.set(code);
	}

	public void setNames(String names) {
		this.entitled.set(names);
	}

	public void setPosts(String posts) {
		this.department.set(posts);
	}
	
	public void setDescription(String posts) {
		this.description.set(posts);
	}

	public String getCode() {
		return code.get().toString();
	}

	public String getEntitled() {

		return entitled.get();
	}

	public String getDepartment() {

		return department.get();
	}
	public String getDescription() {

		return description.get();
	}

	public SimpleStringProperty codeProperty() {
		return code;
	}

	public SimpleStringProperty entitledProperty() {
		return entitled;
	}

	public SimpleStringProperty departmentProperty() {
		return department;
	}
	public SimpleStringProperty descriptionProperty() {
		return description;
	}

	@Override
	public String toString() {
		return "PostsTable [code=" + code + ", entitled=" + entitled + ", department=" + department + "]";
	}
	

}
