package com.bhishiapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Installment;
import com.bhishiapp.entities.Member;
import com.bhishiapp.repositories.InstallmentRepo;

@Service
public class InstallmentServiceImp implements InstallmentService{

	@Autowired
	private InstallmentRepo repo;
	@Autowired
	private MemberService mService;
	
	@Transactional
	@Override
	public Installment getInstallment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Installment> getAllInstallments() {
		return repo.getAllInstallments();
	}

	@Transactional
	@Override
	public void addInstallment(Installment installment) {
		
		mService.updateTotalAmount(installment.getInstallmentAmount(), installment.getMember().getId());
		repo.addInstallment(installment);
		
	}

	@Transactional
	@Override
	public void deleteInstallment(long id) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public int getNextInstallmentCount(long memberId) {
		return repo.getMaxInstallmentCount(memberId) + 1;
	}

	@Transactional
	@Override
	public List<Object[]> getInstallments(long bhishiId) {
		
		return repo.getInstallments(bhishiId);
	}

	@Transactional
	@Override
	public List<Object[]> getInstallmentByMemberName(String name) {
		
		return repo.getInstallmentByMemberName(name);
	}

	@Transactional
	@Override
	public List<Installment> getInstallmentsByMember(long id) {
		
		return repo.getInstallmentsByMember(id);
	}

	@Override
	public int getInstallmentCount() {
		return repo.getInstallmentCount();
	}

}
