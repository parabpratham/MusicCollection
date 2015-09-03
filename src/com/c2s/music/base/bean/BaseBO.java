package com.c2s.music.base.bean;

import java.lang.reflect.Method;
import java.util.Date;

import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.utils.DateUtils;

public class BaseBO
{
	protected Integer	id;
	public Date			created;

	public BaseBO()
	{
	}

	public BaseDTO toDTO(String baseDTOType) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		BaseDTO baseDTO = (BaseDTO) (Class.forName(baseDTOType).newInstance());
		return toDTO(baseDTO);
	}

	public BaseDTO toDTO(BaseDTO baseDTO)
	{
		// String parameter
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		Method[] BOMethods = this.getClass().getMethods();
		for (Method BOMethod : BOMethods)
		{
			try
			{
				if (BOMethod.getName().contains("get"))
				{
					Class<?> returnType = BOMethod.getReturnType();
					Method DTOMethod = null;
					String invoke = null;
					if (returnType == String.class)
					{
						DTOMethod = baseDTO.getClass().getDeclaredMethod(BOMethod.getName().replace("get", "set"), paramString);
						invoke = "" + BOMethod.invoke(this, null);

					}
					else if (returnType == Date.class)
					{
						DTOMethod = baseDTO.getClass().getDeclaredMethod(BOMethod.getName().replace("get", "set"), paramString);
						Object date = BOMethod.invoke(this, null);
						if (date == null)
							invoke = "";
						else
							invoke = DateUtils.toString((Date) date);
					}
					else if (returnType == Integer.class)
					{
						if ("getId".equalsIgnoreCase(BOMethod.getName()))
							DTOMethod = BaseDTO.class.getDeclaredMethod(BOMethod.getName().replace("get", "set"), paramString);
						else
							DTOMethod = baseDTO.getClass().getDeclaredMethod(BOMethod.getName().replace("get", "set"), paramString);
						Object integer = BOMethod.invoke(this, null);
						if (integer == null)
							invoke = "";
						else
							invoke = "" + (Integer) integer;
					}
					else if (returnType == Double.class)
					{
						DTOMethod = baseDTO.getClass().getDeclaredMethod(BOMethod.getName().replace("get", "set"), paramString);
						Object doubleNo = BOMethod.invoke(this, null);
						if (doubleNo == null)
							invoke = "";
						else
							invoke = "" + (Double) doubleNo;
					}
					DTOMethod.invoke(baseDTO, invoke);
				}
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		return baseDTO;

	}

	public BaseBO fillBO(BaseDTO baseDTO)
	{
		Method[] DTOMethods = baseDTO.getClass().getMethods();
		Method[] BOMethods = this.getClass().getMethods();
		Class noparams[] =
		{};
		for (Method DTOMethod : DTOMethods)
		{
			try
			{
				if (DTOMethod.getName().contains("get"))
				{
					for (Method BOMethod : BOMethods)
					{
						if (BOMethod.getName().equalsIgnoreCase(DTOMethod.getName().replace("get", "set")))
						{
							Class<?>[] returnTypes = BOMethod.getParameterTypes();
							Class<?> returnType = returnTypes[0];
							if (returnType == String.class)
							{
								BOMethod.invoke(this, "" + DTOMethod.invoke(baseDTO, noparams));
							}
							else if (returnType == Date.class)
							{
								BOMethod.invoke(this, DateUtils.toDate("" + DTOMethod.invoke(baseDTO, noparams)));
							}
							else if (returnType == Integer.class)
							{
								BOMethod.invoke(this, Integer.parseInt("" + DTOMethod.invoke(baseDTO, noparams)));
							}
							else if (returnType == Double.class)
							{
								BOMethod.invoke(this, Double.parseDouble("" + DTOMethod.invoke(baseDTO, noparams)));
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}

		return this;
	}

	public BaseDTO saveBo(BaseDTO baseDTO) throws Exception
	{
		fillBO(baseDTO);
		BaseService.saveOrUpdate(this);
		return toDTO(baseDTO);
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

}
