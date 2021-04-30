package com.ssafy.study.model;

//Dto : Data Transfer Object
//VO : Value Object
//Bean
public class StudyDto {


	private String name;
	private int cntId;


	
	public StudyDto(String name, int cntId) {
		super();
		this.name = name;
		this.cntId = cntId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCntId() {
		return cntId;
	}

	public void setCntId(int cntId) {
		this.cntId = cntId;
	}

}
