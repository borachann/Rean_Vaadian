package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable, Cloneable{

	private Long id;
	private String firstName = "";
	private String lastName = "";
	private Date birthDate;
	private CustomerStatus status;
	private String email = "";
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public CustomerStatus getStatus() {
		return status;
	}
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isPersisted(){
		return id != null;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return false;
		}
		if (this.id == null){
			return false;
		}
		if (obj instanceof Customer && obj.getClass().equals(getClass())){
			return this.id.equals(((Customer) obj).id);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = 5;
		hash = 43 * hash + (id== null ? 0 : id.hashCode());
		return hash;
	}
	
	@Override
	public Customer clone() throws CloneNotSupportedException{
		return (Customer) super.clone();
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}
}
