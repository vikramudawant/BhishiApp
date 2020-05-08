package com.bhishiapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.repositories.BhishiRepo;

@Service
public class BhishiServiceImp implements BhishiService{

	@Autowired
	private BhishiRepo repo;
	
	@Transactional
	@Override
	public Bhishi getBhishi(long id) {
		return repo.getBhishi(id);
	}

	@Transactional
	@Override
	public List<Bhishi> getAllBhishi() {
		return repo.getAllBhishi();
	}

	@Transactional
	@Override
	public void save(Bhishi bhishi) {
		repo.save(bhishi);
	}

	@Transactional
	@Override
	public void delete(long id) {
		Bhishi bhishi = repo.getBhishi(id);
		repo.delete(bhishi);
		
	}

	@Transactional
	@Override
	public boolean checkLimit(long id) {
		
		return repo.checkLimit(id);
	}

	@Override
	public List<Bhishi> getMemberCompletedBhishi() {
		return repo.getMemberCompletedBhishi();
	}

	@Override
	public int getBhishiCount() {
		
		return repo.getBhishiCount();
	}

}
