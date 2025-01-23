package com.aniket.dao;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.aniket.entities.Student;
import com.aniket.util.HibernateUtil;

public class StudentDaoImpl implements StudentDao
{
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Override
	public List<Student> displayAllInfo(int pageNo, int maxRecords) 
	{
		try(Session session = sessionFactory.openSession())
		{
			Query<Student> query = session.createQuery("From Student",Student.class);
			query.setFirstResult(pageNo);
			query.setMaxResults(maxRecords);
			
			List<Student> list = query.list();
			
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<String> filteredCities() 
	{
		try(Session session = sessionFactory.openSession())
		{
			@SuppressWarnings("unchecked")
			Query<String> query = session.createQuery("select Distinct(addr) From Student Order by addr");
			List<String> cityList = query.list();
			return cityList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> cityWiseFilter(String city, int pageNo, int maxRecords) 
	{
		
		return null;
	}

	@Override
	public Long getTotalRecords() 
	{
		try(Session session = sessionFactory.openSession())
		{
			@SuppressWarnings("unchecked")
			Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Student");
			Long totalRecords = query.getSingleResult();
			return totalRecords;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getDaoPerFilter(Double per) 
	{
		try(Session session = sessionFactory.openSession())
		{
			Filter filter = session.enableFilter("perFilter");
			filter.setParameter("sper", per);
			
			Query<Student> query = session.createQuery("From Student",Student.class);
			List<Student> studentPerFilter = query.list();
			
			//session.disableFilter("perFilter");
			return studentPerFilter;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getDaoCityFilter(String city) 
	{
		try(Session session = sessionFactory.openSession())
		{
			Filter filter = session.enableFilter("cityFilter");
			filter.setParameter("city", city);
			
			Query<Student> query = session.createQuery("From Student",Student.class);
			List<Student> studentCityFilter = query.list();
			
			return studentCityFilter;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getDaoPerCityFilter(String city, Double per) 
	{
		try(Session session = sessionFactory.openSession())
		{
			Filter filter = session.enableFilter("perCityFilter");
			filter.setParameter("scity", city);
			filter.setParameter("sper", per);
			
			Query<Student> query = session.createQuery("From Student",Student.class);
			List<Student> studentPerCityFilter = query.list();
			
			return studentPerCityFilter;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	

}
