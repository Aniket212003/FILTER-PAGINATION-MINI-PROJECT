package com.aniket.service;

import java.util.List;

import com.aniket.entities.Student;

public interface StudentService 
{
	public abstract List<Student> displayStudentAllInfo(int pageNo,int maxRecords);
	public abstract List<String> filterStudentCities();
	public abstract Integer getTotalRecords(int totalRecordsPerPage);
	public abstract List<Student> getServicePerFilter(Double per);
	public abstract List<Student> getServiceCityFilter(String city);
	public abstract List<Student> getServicePerCityFilter(String city, Double per);
}
