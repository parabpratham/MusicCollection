
package com.c2s.music.user.bean;

import java.util.Date;

import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.utils.DateUtils;

public class UserBO extends BaseBO
{
	private String	firstName;

	private String	lastName;

	private String	userName;

	private String	password;

	private String	email;

	private Date	created;

	private Date	lastLogin;

	private Integer	numberOfTimeLogin;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email.toUpperCase();
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public Integer getNumberOfTimeLogin()
	{
		return numberOfTimeLogin;
	}

	public void setNumberOfTimeLogin(Integer numberOfTimeLogin)
	{
		this.numberOfTimeLogin = numberOfTimeLogin;
	}

	@Override
	public BaseDTO saveBo(BaseDTO user) throws Exception
	{
		this.setCreated(DateUtils.now());
		this.setNumberOfTimeLogin(1);
		return super.saveBo(user);
	}
}
