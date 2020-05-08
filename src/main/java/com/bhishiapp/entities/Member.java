package com.bhishiapp.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String name;
	@Size(min=10, max=10)
	private String mobile;
	@NotEmpty
	private String address;
	@NotNull
	private double totalAmount;
	@NotNull
	private int rank;
	@Transient
	public Date paybleDate;
	
	//@JsonIgnoreProperties("member")
	@JsonManagedReference
	@OneToMany(mappedBy = "member")
	private List<Installment> installments;
	
	@JsonIgnoreProperties("members")
//	@JsonBackReference
	@ManyToOne
//	@JoinColumn(name = "bhishi_id")
	private Bhishi bhishi;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}

	public Bhishi getBhishi() {
		return bhishi;
	}

	public void setBhishi(Bhishi bhishi) {
		this.bhishi = bhishi;
	}

	public Date getPaybleDate() {
		return paybleDate;
	}

	public void setPaybleDate(Date paybleDate) {
		this.paybleDate = paybleDate;
	}

	@Override
	public String toString() { 
		return "Member [id=" + id + ", name=" + name + ", mobile=" + mobile + ", address=" + address + ", totalAmount="
				+ totalAmount + ", rank=" + rank + ", bhishi=" + bhishi + "]";
	}

	
	
	
	
	

	
	
}
