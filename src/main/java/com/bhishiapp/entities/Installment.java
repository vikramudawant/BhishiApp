package com.bhishiapp.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Installment {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@NotNull
	private int installmentCount;
	@NotNull
	@Min(10)
	private double installmentAmount;
	//@NotNull
	private Date installmentDate;
	
//	@JsonIgnoreProperties("installments")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "bhishi_id")
	private Bhishi bhishi;
	
//	@JsonIgnoreProperties("installments")
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Bhishi getBhishi() {
		return bhishi;
	}
	public void setBhishi(Bhishi bhishi) {
		this.bhishi = bhishi;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getInstallmentCount() {
		return installmentCount;
	}
	public void setInstallmentCount(int installmentCount) {
		this.installmentCount = installmentCount;
	}
	public double getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public Date getInstallmentDate() {
		return installmentDate;
	}
	public void setInstallmentDate(Date installmentDate) {
		this.installmentDate = installmentDate;
	}
	@Override
	public String toString() {
		return "Installment [id=" + id + ", installmentCount=" + installmentCount + ", installmentAmount="
				+ installmentAmount + ", installmentDate=" + installmentDate + ", bhishi=" + bhishi + ", member="
				+ member + "]";
	}
	
	
	
	
	

}
