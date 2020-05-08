package com.bhishiapp.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Bhishi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min=4)
	private String name;
	@Min(3)
	private double amountPerPerson;
	@NotNull
	private int noOfInstallments;
	@Min(5)
	private int noOfMembers; 
	@NotNull
	private Date startDate; 
	@NotNull
	private Date endDate;
	@NotNull
	private boolean isActive;
	
//	@JsonIgnoreProperties("bhishi")
	@JsonManagedReference
	@OneToMany(mappedBy = "bhishi")
	private List<Member> members;
	
	//@JsonIgnoreProperties("bhishi")
	@JsonManagedReference
	@OneToMany(mappedBy = "bhishi")
	private List<Installment> installments;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmountPerPerson() {
		return amountPerPerson;
	}

	public void setAmountPerPerson(double amountPerPerson) {
		this.amountPerPerson = amountPerPerson;
	}

	public int getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public int getNoOfMembers() {
		return noOfMembers;
	}

	public void setNoOfMembers(int noOfMembers) {
		this.noOfMembers = noOfMembers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}

	@Override
	public String toString() {
		return "Bhishi [id=" + id + ", name=" + name + ", amountPerPerson=" + amountPerPerson + ", noOfInstallments="
				+ noOfInstallments + ", noOfMembers=" + noOfMembers + ", startDate=" + startDate + ", endDate="
				+ endDate + ", isActive=" + isActive + "]";
	}


}
