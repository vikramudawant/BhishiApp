package com.bhishiapp.repositories;

import java.util.List;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Installment;

public interface InstallmentRepo {


	Installment getInstallment();
	List<Installment> getAllInstallments();
	void addInstallment(Installment installment);
	void deleteInstallment(long id);
	int getMaxInstallmentCount(long memberId);
	List<Object[]> getInstallments(long bhishiId);
	List<Object[]> getInstallmentByMemberName(String name);
	List<Installment> getInstallmentsByMember(long id);
	int getInstallmentCount();
}
