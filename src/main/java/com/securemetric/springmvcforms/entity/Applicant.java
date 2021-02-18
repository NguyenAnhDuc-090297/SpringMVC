package com.securemetric.springmvcforms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Applicants")
public class Applicant implements Serializable{

	private static final long serialVersionUID = -7893237204476214050L;
	private String id;
	private String name;
	private String email;
	
	private String position;
	private String gender;
	private String skills;
	
	@Id
	@Column(name = "ID", length = 50, nullable = false)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "Name", length = 50, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@Column(name = "Position", length = 50, nullable = false)
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Id
	@Column(name = "Gender", length = 10, nullable = false)
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Id
	@Column(name = "Email", length = 50, nullable = false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Id
	@Column(name = "Skills", length = 255, nullable = false)
	public String getSkills() {
		return skills;
	}
	
	public void setSkills(String skills) {
		this.skills = skills;
	}
}













