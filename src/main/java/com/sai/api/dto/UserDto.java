package com.sai.api.dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.sun.el.parser.ParseException;

public class UserDto {

	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	private static final SimpleDateFormat dateFormat
    = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	  private Long id;
	
	 
//	  private String lastName;
	  private String email;
	  private String password;
	  private String about;
	
	  private String firstName;
	

	
	  private String createdAt;
	
	  private String imageUrl;

//	  public Date getSubmissionDateConverted(String timezone) throws java.text.ParseException{
//	      dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//	      return dateFormat.parse(this.createdAt);
//	  }

	  public void setSubmissionDate(Date date, String timezone) {
		  System.out.println("in dto function *************** : "+date.getClass());
	      dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
	      System.out.println("in dto function  after *************** : "+date);
	      this.createdAt = dateFormat.format(date);
	  }

	public UserDto(Long id, String email, String password, String about, String firstName,
			 String imageUrl) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.about = about;
		this.firstName = firstName;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
