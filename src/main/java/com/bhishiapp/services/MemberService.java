package com.bhishiapp.services;

import java.util.List;

import javax.validation.Valid;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Member;

public interface MemberService {
	
	Member getMember(long id);
	List<Member> getAllMembersByBhishi(long bhishiId);
	void save(Member member);
	void delete(Member member);
	List<Member> getMembersByBhishi(long id);
	void updateTotalAmount(double installmentAmount, long id);
	int getMemberCount();
}
