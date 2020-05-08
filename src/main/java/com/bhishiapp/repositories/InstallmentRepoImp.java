package com.bhishiapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Installment;

@Repository
public class InstallmentRepoImp implements InstallmentRepo {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Installment getInstallment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Installment> getAllInstallments() {
		Session session = entityManager.unwrap(Session.class);
		Query<Installment> query = session.createQuery("from Installment", Installment.class);
		List<Installment> installments = query.getResultList();
		return installments;
	}

	@Override
	public void addInstallment(Installment installment) {
		Session session = entityManager.unwrap(Session.class);
		session.save(installment);
		
	}

	@Override
	public void deleteInstallment(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxInstallmentCount(long memberId) {
		Session session = entityManager.unwrap(Session.class);
		
		int count = ((Number)session.createNativeQuery("select COALESCE(MAX(installment_count),0) from installment where member_id ="+memberId)
					.getSingleResult()).intValue();
		
		return count;
		
	}

	@Override
	public List<Object[]> getInstallments(long bhishiId) {
		Session session = entityManager.unwrap(Session.class);
		Query query;
		if(bhishiId != 0) {
			 query = session.createNativeQuery("select m.id, m.name, m.address, m.total_amount as totalPaidAmount, count(i.member_id) as installmentCount "
					+ "from member m inner join installment i on m.id = i.member_id where m.bhishi_id = ? group by i.member_id");
			query.setParameter(1, bhishiId);
		} else {
			 query = session.createNativeQuery("select m.id, m.name, m.address, m.total_amount as totalPaidAmount, count(i.member_id) as installmentCount "
					+ "from member m inner join installment i on m.id = i.member_id group by i.member_id");
		}
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		List<Object[]> list = query.list();
		
		return list;
	}

	@Override
	public List<Object[]> getInstallmentByMemberName(String name) {
		
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery("select m.id, m.name, count(m.id) as installment_count, m.total_amount, m.rank, m.mobile from installment as i inner join member as m on i.member_id = m.id "
								+ "where m.name like '"+ name+"' group by m.id");
		//query.setParameter(1, "%"+name+"%"); 
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object[]> list = query.list();
		
		return list;
	}

	@Override
	public List<Installment> getInstallmentsByMember(long id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Installment where member_id = ?1", Installment.class);
		query.setParameter(1, id);
		
		return query.getResultList();
	}

	@Override
	public int getInstallmentCount() {
		Session session = entityManager.unwrap(Session.class);
		String query = "select count(id) from Bhishi";
		int count = ((Number)session.createQuery(query).getSingleResult()).intValue();
		return count;
	}

}
