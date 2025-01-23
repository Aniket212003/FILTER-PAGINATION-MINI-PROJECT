package com.aniket.factory;

import com.aniket.service.StudentService;
import com.aniket.service.StudentServiceImpl;

public class StudentServiceFactory 
{
	private StudentServiceFactory() {}

	private static StudentService studentService = null;
	
	public static StudentService getInstanceStudentService()
	{
		try
		{
			if(studentService == null)
			{
				studentService = new StudentServiceImpl();
			}
			return studentService;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
