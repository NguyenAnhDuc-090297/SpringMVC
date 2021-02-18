package com.securemetric.springmvcforms.model;

public class ApplicantInfo {

	private String id;
	private String name;
	private String email;

	private String position;
	private String gender;
	private String skillsString;
	private String[] skills;

	public ApplicantInfo() {

	}

	// Constructor su dung trong Hibernate Query
	public ApplicantInfo(String id, String name, String email, String gender, String position, String skillsString) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.gender = gender;
		this.skillsString = skillsString;
		this.skills = toArray(skillsString);
	}

	private String[] toArray(String skillStrings) {
		if (skillStrings == null) {
			return new String[0];
		}
		String[] ret = skillStrings.split(",");
		return ret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
		this.skillsString = this.joinString(skills);
	}

	private String joinString(String[] skills) {
		if (skills == null) {
			return "";
		}
		return String.join(",", skills);
	}

	public String getSkillsString() {
		return this.skillsString;
	}
}
