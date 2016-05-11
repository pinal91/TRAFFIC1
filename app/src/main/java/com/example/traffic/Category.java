package com.example.traffic;

public class Category {
	
	private int id;
	private String city_name;
	
	public Category(){}
	
	public Category(int id, String city_name){
		this.id = id;
		this.city_name = city_name;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String ncity_ame){
		this.city_name = city_name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.city_name;
	}

}
