package com.c2s.music.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.ModuleException;

import com.c2s.music.user.dto.UserDTO;
import com.c2s.music.user.form.UserForm;

public class BaseAction extends DispatchAction
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward execute = null;
		try
		{
			if (!request.getRequestURI().contains((String) "/home"))
			{
				System.out.println("request = " + request.getParameter("org.apache.struts.taglib.html.TOKEN"));
				System.out.println("Session = " + request.getSession().getAttribute("org.apache.struts.action.TOKEN"));
				String ajaxRequest = "" + request.getParameter("ajax");
				if (!isTokenValid(request))
				{
					saveToken(request);
					request.setAttribute("error", "Invalid Sessaion ID");
					return mapping.findForward("ErrorPage");
				}
				else
				{
					if (ajaxRequest == null || !ajaxRequest.equalsIgnoreCase("y"))
					{	
						resetToken(request);
						saveToken(request);
						setUserForm(request);
					}
				}
			}
			else
				saveToken(request);
			execute = super.execute(mapping, form, request, response);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		if (execute == null)
		{
			request.setAttribute("error", "No Method Specified");
			return mapping.findForward("ErrorPage");
		}
		else
		{
			return execute;
		}
	}

	protected int convertID(String id) throws ModuleException
	{
		int idNum = 0;
		if (id != null)// id should contain the id of the album to delete
			if (!id.equals(""))// then user is request to delete an album
			{
				// convert the String id to int id
				try
				{
					idNum = Integer.parseInt(id);
				}
				catch (NumberFormatException nfe)
				{
					log.error("error in converting string to a number");
					log.error(nfe.getLocalizedMessage());
					ModuleException me = new ModuleException("error.nfe.message");
					throw me;
				}
			}
		return idNum;
	}

	@Override
	protected boolean isTokenValid(HttpServletRequest request)
	{

		return super.isTokenValid(request);
	}

	private void setUserForm(HttpServletRequest request)
	{
		Object attribute = request.getSession().getAttribute("user");
		if (attribute != null)
		{
			UserDTO userDTO = (UserDTO) attribute;
			UserForm userForm = new UserForm();
			userForm.fillForm(userDTO);
			request.setAttribute("user", userForm);
		}
	}
}
