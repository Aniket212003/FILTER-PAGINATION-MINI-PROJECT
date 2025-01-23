package com.aniket.dao;

import java.util.List;

import com.aniket.entities.Student;

public interface StudentDao 
{
	public abstract List<Student> displayAllInfo(int pageNo, int maxRecords);
	public abstract List<String> filteredCities();
	public abstract List<Student> cityWiseFilter(String city,int pageNo, int maxRecords);
	public abstract Long getTotalRecords();
	public abstract List<Student> getDaoPerFilter(Double per);
	public abstract List<Student> getDaoCityFilter(String city);
	public abstract List<Student> getDaoPerCityFilter(String city, Double per);
	
}
