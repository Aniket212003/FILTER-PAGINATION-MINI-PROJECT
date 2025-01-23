package com.aniket.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.aniket.config.HibernateConfiguration;

public class HibernateUtil 
{
	private HibernateUtil() {}
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
		{
			try
			{
				Configuration configuration = new Configuration();
				configuration.configure(HibernateConfiguration.FILE_NAME);
				sessionFactory = configuration.buildSessionFactory();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return sessionFactory;
	}
	
	public static void closeSessionFactory()
	{
		if(sessionFactory != null)
		{
			sessionFactory.close();
		}
	}
	
}
