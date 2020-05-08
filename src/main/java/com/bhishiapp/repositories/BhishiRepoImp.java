package com.bhishiapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhishiapp.entities.Bhishi;

@Repository
public class BhishiRepoImp implements BhishiRepo{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Bhishi getBhishi(long id) {
		Session session = entityManager.unwrap(Session.class);
		Bhishi bhishi = session.get(Bhishi.class, id);
		return bhishi;
	}

	@Override
	public List<Bhishi> getAllBhishi() {
		Session session = entityManager.unwrap(Session.class);
		Query<Bhishi> query = session.createQuery("from Bhishi", Bhishi.class);
		List<Bhishi> list = query.getResultList();  
		return list;
		
	}

	@Override
	public void save(Bhishi bhishi) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(bhishi);
	}

	@Override
	public void delete(Bhishi bhishi) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(bhishi);
		
	}

	@Override
	public boolean checkLimit(long id) {
		Session session = entityManager.unwrap(Session.class);
		String queryString = "SELECT count(b.id) FROM Bhishi b where "
				+ "id= "+id+" AND b.noOfMembers = (select COUNT(*) from Member m WHERE m.bhishi = "+id+")";
		long result = ((Number)session.createQuery(queryString).getSingleResult()).longValue();
		if(result > 0)
			return true;
		return false;
	}

	@Override
	public List<Bhishi> getMemberCompletedBhishi() {
		Session session = entityManager.unwrap(Session.class);
		Query<Bhishi> query = session.createQuery("from Bhishi b where b.noOfMembers = (select count(id) from Member m where m.bhishi = b.id)");
		List<Bhishi> list = query.getResultList(); 
		return list;
	}

	@Override
	public int getBhishiCount() {
		Session session = entityManager.unwrap(Session.class);
		String query = "select count(id) from Bhishi";
		int count = ((Number)session.createQuery(query).getSingleResult()).intValue();
		return count;
	}

}
