package com.bhishiapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Member;
import com.bhishiapp.repositories.MemberRepo;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	private MemberRepo repo;
	
	@Transactional
	@Override
	public Member getMember(long id) {
		return repo.getMember(id);
	}

	@Transactional
	@Override
	public List<Member> getAllMembersByBhishi(long bhishiId) {
		return repo.getAllMembersByBhishi(bhishiId);
	}

	@Transactional
	@Override
	public void save(Member member) {
		repo.save(member);
	}

	@Transactional
	@Override
	public void delete(Member member) {
		repo.delete(member);
		
	}

	@Transactional
	@Override
	public List<Member> getMembersByBhishi(long id) {
		return repo.getMembersByBhishi(id);
	}

	@Transactional
	@Override
	public void updateTotalAmount(double installmentAmount, long id) {
		repo.updateTotalAmount(installmentAmount, id);
		
	}

	@Override
	public int getMemberCount() {
		return repo.getMemberCount();
	}


}
