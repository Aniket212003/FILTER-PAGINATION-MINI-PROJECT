package com.aniket.factory;

import com.aniket.dao.StudentDao;
import com.aniket.dao.StudentDaoImpl;

public class StudentDaoFactory 
{
	private StudentDaoFactory() {}

	private static StudentDao studentDao = null;
	
	public static StudentDao getInstanceStudentDao()
	{
			try 
			{
				if(studentDao == null)
				{
					studentDao = new StudentDaoImpl();
				}
				return studentDao;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
	}
}
