package com.c2s.music.login.bean;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.login.dto.LoginDTO;
import com.c2s.music.user.bean.UserBO;
import com.c2s.music.user.dto.UserDTO;
import com.c2s.music.utils.DateUtils;

public class LoginBO extends BaseBO
{

	private String					email;

	private String					password;

	private HashMap<String, UserBO>	usernameEmail	= new HashMap<String, UserBO>();

	public UserDTO validateUser(LoginDTO loginDTO)
	{
		UserDTO userDTO = null;
		if (usernameEmail != null && !usernameEmail.isEmpty() && usernameEmail.containsKey(loginDTO.getEmail()))
		{

			UserBO userBO = usernameEmail.get(loginDTO.getEmail().toUpperCase());
			if (loginDTO.getPassword().equalsIgnoreCase(userBO.getPassword()))
			{
				updateUserLog(userBO);
				userDTO = new UserDTO();
				userDTO = (UserDTO) userBO.toDTO(userDTO);
				return userDTO;
			}
		}
		else
		{
			UserBO findByUserName = findByEmail(loginDTO.getEmail().toUpperCase());
			if (findByUserName != null)
			{
				usernameEmail.put(loginDTO.getEmail().toUpperCase(), findByUserName);
				if (loginDTO.getPassword().equalsIgnoreCase(findByUserName.getPassword()))
				{
					updateUserLog(findByUserName);
					userDTO = new UserDTO();
					userDTO = (UserDTO) findByUserName.toDTO(userDTO);
					return userDTO;
				}
			}
		}

		return userDTO;
	}

	private void updateUserLog(UserBO userBO)
	{
		// TODO Auto-generated method stub
		userBO.setLastLogin(DateUtils.now());
		userBO.setNumberOfTimeLogin(userBO.getNumberOfTimeLogin() + 1);
		try
		{
			BaseService.saveOrUpdate(userBO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public UserDTO findByUserName(String email)
	{
		UserDTO userDTO = null;
		UserBO userBO = null;
		if (usernameEmail != null && !usernameEmail.isEmpty() && usernameEmail.containsKey(email))
		{

			userBO = usernameEmail.get(email.toUpperCase());
		}
		else
		{
			userBO = findByEmail(email.toUpperCase());
			if (userBO != null)
			{
				usernameEmail.put(email.toUpperCase(), userBO);
			}
		}
		try
		{
			userDTO = (UserDTO) (userBO.toDTO(UserDTO.class.getName()));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return userDTO;
	}

	public UserBO findByEmail(String email)
	{

		Session openSession = null;
		UserBO userBO = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + UserBO.class.getName() + " where email = '" + email + "' ");
			List<UserBO> list = createQuery.list();
			if (list != null && list.size() > 0)
			{
				userBO = list.get(0);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (openSession != null)
				BaseService.closeSession(openSession);
		}
		return userBO;
	}

	/*
	 * public UserBO findByUserName(String userName) { Configuration
	 * configuration = new Configuration(); Session openSession = null; UserBO
	 * userBO = null; try { configuration.configure("hibernate.cfg.xml");
	 * SessionFactory buildSessionFactory = configuration.buildSessionFactory();
	 * openSession = buildSessionFactory.openSession(); Query createQuery =
	 * openSession.createQuery("from " + UserBO.class.getName() + " where
	 * userName = '" + userName+"' "); List<UserBO> list = createQuery.list();
	 * if (list != null && list.size() > 0) { userBO = list.get(0); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { if (openSession != null)
	 * openSession.close(); } return userBO; }
	 */

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
