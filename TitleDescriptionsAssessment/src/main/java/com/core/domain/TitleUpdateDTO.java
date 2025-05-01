package com.core.domain;

//DTO for the request body
public class TitleUpdateDTO {
	
	private String title;
	private String description;
	private String status; // Use String here
	// Getters and setters...
	
	public String getTitle() {
	   return title;
	}
	
	public void setTitle(String title) {
	   this.title = title;
	}
	
	public String getDescription() {
	   return description;
	}
	
	public void setDescription(String description) {
	   this.description = description;
	}
	
	public String getStatus() {
	   return status;
	}
	
	public void setStatus(String status) {
	   this.status = status;
	}
}