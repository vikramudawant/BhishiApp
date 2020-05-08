package com.bhishiapp.services;

import java.util.List;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Installment;

public interface InstallmentService {
	
	Installment getInstallment();
	List<Installment> getAllInstallments();
	void addInstallment(Installment installment);
	void deleteInstallment(long id);
	int getNextInstallmentCount(long memberId);
	List<Object[]> getInstallments(long bhishiId);
	List<Object[]> getInstallmentByMemberName(String name);
	List<Installment> getInstallmentsByMember(long id);
	int getInstallmentCount();

}
