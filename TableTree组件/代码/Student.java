package com.njupt.jjw;

public class Student {
	
	private String id;
	
	private String name;
	
	public Student(String id) {
		this.id = id;
		name = "njupt"+id;
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
	

}
