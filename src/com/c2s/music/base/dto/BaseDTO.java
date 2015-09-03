package com.c2s.music.base.dto;

import java.lang.reflect.Method;

import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.form.BaseForm;

public class BaseDTO
{

	protected String	id;

	protected String	updateLink;

	protected String	deleteLink;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUpdateLink()
	{
		return updateLink;
	}

	public void setUpdateLink(String updateLink)
	{
		this.updateLink = updateLink;
	}

	public String getDeleteLink()
	{
		return deleteLink;
	}

	public void setDeleteLink(String deleteLink)
	{
		this.deleteLink = deleteLink;
	}

	public BaseBO toBO(BaseBO baseBO)
	{
		// String parameter
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		Method[] dtoMethods = this.getClass().getMethods();
		for (Method method : dtoMethods)
		{
			try
			{
				if (method.getName().contains("get"))
				{
					Method BOMethod = baseBO.getClass().getDeclaredMethod(method.getName().replace("get", "set"), paramString);
					BOMethod.invoke(this, method.invoke(baseBO, null));
				}
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		return baseBO;
	}

}
