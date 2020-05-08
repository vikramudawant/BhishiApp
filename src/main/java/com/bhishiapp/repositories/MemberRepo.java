package com.bhishiapp.repositories;

import java.util.List;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Member;

public interface MemberRepo {
	
	Member getMember(long id);
	List<Member> getAllMembersByBhishi(long bhishiId);
	void save(Member member);
	void delete(Member member);
	List<Member> getMembersByBhishi(long id);
	void updateTotalAmount(double installmentAmount, long id);
	double getTotalAmount(long id);
	int getMemberCount();
}
