package com.cwvs.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subject")
public class Subject {
	@Id
	private String id;

	private String name;
	private int ssn;
	private boolean active;

	public Subject() {
	}

	public Subject(String name, int ssn) 
	{
		this.name = name;
		this.ssn = ssn;
	}

	public String getId() 
	{
		return id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setSsn(int ssn) 
	{
		this.ssn = ssn;
	}

	public int getSsn() {
		return this.ssn;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", "
				+ "name=" + name + 
				", ssn=" + ssn +
				", active=" + active + "]";
	}
}
