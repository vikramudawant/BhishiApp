package com.bhishiapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Member;

@Repository
public class MemberRepoImp implements MemberRepo {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Member getMember(long id) {
		Session session = entityManager.unwrap(Session.class);
		Member member = session.get(Member.class, id);
		return member;
	}

	@Override
	public List<Member> getAllMembersByBhishi(long bhishiId) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Member> query;
		if(bhishiId != 0) {
		query = session.createQuery("from Member where bhishi_id = ?1", Member.class);
		query.setParameter(1, bhishiId);
		} else {
			query = session.createQuery("from Member", Member.class);
		}
		List<Member> members = query.getResultList();
		
		return members;
	}

	@Override
	public void save(Member member) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(member);
		
	}

	@Override
	public void delete(Member member) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(member);
		
	}

	@Override
	public List<Member> getMembersByBhishi(long id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Member> query = session.createQuery("from Member where bhishi_id=?1", Member.class);
		query.setParameter(1, id);
		List<Member> members = query.getResultList();
		return members;
	}

	@Override
	public void updateTotalAmount(double installmentAmount, long id) {
		double newAmount = getTotalAmount(id) + installmentAmount;  
		Session session = entityManager.unwrap(Session.class);
		
		Query<Member> query = session.createQuery("update Member set totalAmount = ?1 where id = ?2");
		query.setParameter(1, newAmount);
		query.setParameter(2, id);
		query.executeUpdate();
		
		
	}

	@Override
	public double getTotalAmount(long id) {
		Session session = entityManager.unwrap(Session.class);
		//double totalAmount = ((Number)session.createNativeQuery("select total_amount from member where id ="+id).getSingleResult()).doubleValue();
		Query query = session.createNativeQuery("select total_amount from member where id =?");
		query.setParameter(1, id);
		double totalAmount = (double) query.getSingleResult();
		return totalAmount;
	}

	@Override
	public int getMemberCount() {
		Session session = entityManager.unwrap(Session.class);
		String query = "select count(id) from Member";
		int count = ((Number)session.createQuery(query).getSingleResult()).intValue();
		return count;
	}

}
