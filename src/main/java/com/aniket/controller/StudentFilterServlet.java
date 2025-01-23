package com.aniket.controller;

import java.io.IOException;
import java.util.List;

import com.aniket.entities.Student;
import com.aniket.factory.StudentServiceFactory;
import com.aniket.service.StudentService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;






//import java.io.PrintWriter;



@WebServlet("/StudentFilterServlet")
public class StudentFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StudentFilterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		try
		{
			StudentService studentService = StudentServiceFactory.getInstanceStudentService();
			String action = request.getParameter("action");
			int page=1;
			int recordsPerPage = 10;
			
			if(request.getParameter("page") != null)
			{
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			if(action.equals("All"))
			{
				List<Student> studentList = studentService.displayStudentAllInfo(page, recordsPerPage);
				int totalPages = studentService.getTotalRecords(recordsPerPage);
				
				//System.out.println("hello");
			
				request.setAttribute("students", studentList);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);
			
			}
			else if(action.equals("Filter"))
			{
				String city = null;
				Double per = null;
				if(request.getParameter("cityFilter") != null && !request.getParameter("cityFilter").isEmpty())
				{
					city = request.getParameter("cityFilter");
				}
				
				if(request.getParameter("perFilter") != null && !request.getParameter("perFilter").isEmpty())
				{
					per = Double.parseDouble(request.getParameter("perFilter"));
					//System.out.println(per);
				}
				
				if(per != null && city == null)
				{
					System.out.println("Hello i'm in per");
					List<Student> perFilterList = studentService.getServicePerFilter(per);
					request.setAttribute("students", perFilterList);
				}
				else if(city != null && per == null)
				{
					System.out.println("Hello i'm in city");
					List<Student> cityFilterList = studentService.getServiceCityFilter(city);
					request.setAttribute("students", cityFilterList);
				}
				else if(city != null && per != null)
				{
					System.out.println("Hello i'm in city & per");
					List<Student> perCityFilterList = studentService.getServicePerCityFilter(city,per);
					request.setAttribute("students", perCityFilterList);
				}
				
				
				
				
			}
			
			
			
			/*
			 * PrintWriter out = response.getWriter(); for(Student student:studentList) {
			 * out.println("<tr><td>" + student.getRno() + "</td><td>" + student.getName() +
			 * "</td><td>" + student.getPer() + "</td><td>" + student.getAddr() +
			 * "</td><td>" + student.getBday() + "</td></tr>"); }
			 */
			
			/*
			 * for(Student s:studentList) { System.out.println( s.getRno() + " " +
			 * s.getName() + " " + s.getPer() ); }
			 */
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String Error = "Error Occured";
			request.setAttribute("Error", Error);
		}
		finally
		{
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

}
