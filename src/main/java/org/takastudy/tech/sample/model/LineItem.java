package org.takastudy.tech.sample.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LineItem {
	private String id;
	private String userid;
	private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	private String contents;
	private List<String> attach;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public List<String> getAttach() {
		return attach;
	}

	public void setAttach(List<String> attach) {
		this.attach = attach;
	}

}
