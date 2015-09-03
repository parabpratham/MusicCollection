package com.c2s.music.user.action;

import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.c2s.music.base.action.BaseAction;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.user.bean.UserBO;
import com.c2s.music.user.dto.UserDTO;
import com.c2s.music.user.form.UserForm;

public class UserAction extends BaseAction
{
	public ActionForward SignUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UserForm userForm = (UserForm) form;
		if (userForm.getPassword().equalsIgnoreCase(userForm.getPassword2()))
		{
			UserBO findBaseBOBySpecification = (UserBO) BaseService.findBaseBOBySpecification(" email = '" + userForm.getEmail() + "'", UserBO.class);
			if (findBaseBOBySpecification != null)
			{
				// create ActionError and save in the request
				ActionErrors errors = new ActionErrors();
				ActionError error = new ActionError("error.join.exists");
				errors.add("join", error);
				saveErrors(request, errors);
				return (mapping.findForward("failure"));
			}

			UserDTO user = new UserDTO();
			userForm.fillDTO(user);
			user = (UserDTO) (new UserBO().saveBo(user));
			if (user != null)
			{
				// save UserDTO in session
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("userName", user.getUserName());
				return (mapping.findForward("success"));
			}
			else
			{
				// create ActionError and save in the request
				ActionErrors errors = new ActionErrors();
				ActionError error = new ActionError("error.join.systemerror");
				errors.add("join", error);
				saveErrors(request, errors);
				return (mapping.findForward("failure"));
			}

		}
		else
		// passwords did not match
		{
			// create ActionError and save in the request
			ActionErrors errors = new ActionErrors();
			ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			ActionError error = new ActionError("error.join.passmismatch", bundle.getString("join.password2"), bundle.getString("join.password"));
			errors.add("password2", error);
			saveErrors(request, errors);
			return (mapping.findForward("failure"));
		}
	}

	public ActionForward Cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return (mapping.findForward("cancel"));
	}

	public ActionForward checkUserName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String email = "" + request.getParameter("email");
		String error = "";
		response.setContentType("text/xml");
		response.setHeader("cache-control", "no-cache");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<pageerrors>");
		if (email != null || !email.equalsIgnoreCase("null"))
		{
			UserBO findBaseBOBySpecification = (UserBO) BaseService.findBaseBOBySpecification(" email = '" + email + "'", UserBO.class);
			if (findBaseBOBySpecification != null)
				error = "Email already registered";
		}
		else
		{
			System.out.print("--email-" + error + "--");
		}
		out.println();
		out.println("</pageerrors>");
		out.println();
		out.flush();
		return null;
	}
}
