package com.c2s.music.user.form;

import com.c2s.music.base.form.BaseForm;

public class UserForm extends BaseForm
{

	private static final long	serialVersionUID	= 1664378707573911983L;

	private String				firstName;

	private String				lastName;

	private String				userName;

	private String				password;

	private String				password2;

	private String				email;

	private String				created;

	private String				lastLogin;

	private String				numberOfTimeLogin;

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
		this.email = email;
	}

	public String getCreated()
	{
		return created;
	}

	public void setCreated(String created)
	{
		this.created = created;
	}

	public String getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(String lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public String getNumberOfTimeLogin()
	{
		return numberOfTimeLogin;
	}

	public void setNumberOfTimeLogin(String numberOfTimeLogin)
	{
		this.numberOfTimeLogin = numberOfTimeLogin;
	}

	public String getPassword2()
	{
		return password2;
	}

	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}

}
