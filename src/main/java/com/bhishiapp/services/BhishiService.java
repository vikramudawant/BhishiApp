package com.bhishiapp.services;

import java.util.List;

import com.bhishiapp.entities.Bhishi;

public interface BhishiService {
	Bhishi getBhishi(long id);
	List<Bhishi> getAllBhishi();
	List<Bhishi> getMemberCompletedBhishi();
	void save(Bhishi bhishi);
	void delete(long id);
	boolean checkLimit(long id);
	int getBhishiCount();
}
