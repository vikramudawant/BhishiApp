package com.bhishiapp.repositories;

import java.util.List;


import com.bhishiapp.entities.Bhishi;

public interface BhishiRepo {
	
	Bhishi getBhishi(long id);
	List<Bhishi> getAllBhishi();
	List<Bhishi> getMemberCompletedBhishi();
	void save(Bhishi bhishi);
	void delete(Bhishi bhishi);
	boolean checkLimit(long id);
	int getBhishiCount();
}
