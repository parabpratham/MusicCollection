package com.c2s.music.startup;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.c2s.music.base.service.BaseService;

public class StartupManager implements PlugIn
{
	public StartupManager()
	{
	}

	public void destroy()
	{

	}

	public void init(ActionServlet arg0, ModuleConfig arg1) throws ServletException
	{
		BaseService.init_base();
	}
}
