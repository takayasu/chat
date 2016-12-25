package org.takastudy.tech.sample.test;

import java.util.Date;

public class TestData {
	private String name;
	private String email;
	private Date entry;

	public TestData(String name, String email, Date entry) {
		super();
		this.name = name;
		this.email = email;
		this.entry = entry;
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

	public Date getEntry() {
		return entry;
	}

	public void setEntry(Date entry) {
		this.entry = entry;
	}

}
