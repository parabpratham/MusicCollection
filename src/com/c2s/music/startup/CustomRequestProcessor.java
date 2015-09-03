package com.c2s.music.startup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;

import com.c2s.music.user.dto.UserDTO;

public class CustomRequestProcessor extends RequestProcessor
{

	protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response)
	{
		boolean continueProcessing = true; // assume success
		String requestPath = request.getContextPath();
		// Test if the request is a login request
		try	
		{
			HttpSession session = null;
			// ensure that the user’s session has not timed out
			if (request.isRequestedSessionIdValid())
				session = request.getSession();
			else
			{
				request.setAttribute("error", "No Method Specified");
				response.sendRedirect(requestPath+"/home.do?method=homePageList");
			}

			// get the current request’s path
			String path = processPath(request, response);
			// don’t do any testing if user is logging on
			if (!path.equals((String) "/home"))
			{
				// get the user bean
				UserDTO user = (UserDTO) session.getAttribute("user");
				// ensure that user has logged on
				if (user == null) // else make them login first
				{
					try
					{
						//response.sendRedirect(requestPath+"/Jsp/Login/home.jsp");
					}
					catch (Exception e)
					{
						// TODO: handle exception
						log.error("problem redirecting in processPreprocess - " + e.getMessage());
					}
					// continueProcessing = false;
				}
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			log.error("problem processing path - " + e.getMessage());
			// continueProcessing = false;
		}
		return continueProcessing;
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		try
		{
			super.process(request, response);
		}
		catch (IllegalStateException e)
		{
			log.error("problem processing ajax - " + e.getMessage());
		}
	}

}
