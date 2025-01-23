package com.aniket.service;

import java.util.List;

import com.aniket.dao.StudentDao;
import com.aniket.entities.Student;
import com.aniket.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService
{
	StudentDao studentDao = StudentDaoFactory.getInstanceStudentDao();
	@Override
	public List<Student> displayStudentAllInfo(int pageNo,int maxRecords) 
	{
		try 
		{
			int page = (pageNo-1) * 10;
			return studentDao.displayAllInfo(page, maxRecords);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> filterStudentCities() 
	{
		return studentDao.filteredCities();
	}

	@Override
	public Integer getTotalRecords(int totalRecordsPerPage) 
	{
		Long totalRecords = studentDao.getTotalRecords();
		int totalPages = (int) Math.ceil((double)totalRecords/totalRecordsPerPage);
		return totalPages;
	}
	
	@Override
	public List<Student> getServicePerFilter(Double per) 
	{
		return studentDao.getDaoPerFilter(per);
	}

	@Override
	public List<Student> getServiceCityFilter(String city) 
	{
		
		return studentDao.getDaoCityFilter(city);
	}

	@Override
	public List<Student> getServicePerCityFilter(String city, Double per) 
	{
		try
		{
			return studentDao.getDaoPerCityFilter(city,per);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
