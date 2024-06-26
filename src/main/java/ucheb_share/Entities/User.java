package ucheb_share.Entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USERS")
public class User {
	@Id
	int id;
	String password;
	
	String name;
	//String surname;
	//String lastName;
	int flowId;
	int admissionYear;
	int courseNum;
	//String group;
	//float karma;
	//Date registrationDate;
	//Status status;
	int defaultFolderId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public int getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	public int getDefaultFolderId() {
		return defaultFolderId;
	}
	public void setDefaultFolderId(int defaultFolderId) {
		this.defaultFolderId = defaultFolderId;
	}
}
