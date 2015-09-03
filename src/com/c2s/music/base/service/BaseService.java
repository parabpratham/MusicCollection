package com.c2s.music.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.ModuleException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.c2s.music.album.bean.AlbumBO;
import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.song.bean.GenreBO;
import com.c2s.music.utils.DateUtils;

public class BaseService
{
	private static SessionFactory		buildSessionFactory	= null;
	private static int					openSessions		= 0;
	private static Map<String, GenreBO>	genreMap			= new HashMap<String, GenreBO>();
	private static Map<String, AlbumBO>	albumMap			= new HashMap<String, AlbumBO>();

	public BaseService()
	{
	}

	public static void init_base()
	{
		buildSessionFactory();
		fillGenreMap();
	}

	private static void buildSessionFactory() throws HibernateException
	{
		if (buildSessionFactory == null)
		{
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			buildSessionFactory = configuration.buildSessionFactory();
		}
	}

	public static synchronized Session getSession()
	{
		buildSessionFactory();
		openSessions++;
		return buildSessionFactory.openSession();
	}

	public static synchronized void closeSession(Session openSession)
	{
		buildSessionFactory();
		openSessions--;
		openSession.close();
	}

	public static List findBaseDTOList(String BOClass, String dtoClass, String whereSpecification, String orderSpecification) throws ModuleException
	{
		Session openSession = null;
		List<BaseDTO> dtoList = new ArrayList<BaseDTO>();
		try
		{
			openSession = BaseService.getSession();
			String query = "from " + BOClass;
			if (whereSpecification != null)
				query += whereSpecification.equalsIgnoreCase("") ? "" : " where " + whereSpecification;
			if (orderSpecification != null)
				query += orderSpecification;
			Query createQuery = openSession.createQuery(query);
			List list = createQuery.list();
			for (Object BO : list)
			{
				BaseBO baseBO = (BaseBO) BO;
				BaseDTO dto = baseBO.toDTO(dtoClass);
				dtoList.add(dto);
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
		return dtoList;

	}

	public static BaseBO findBaseBOById(int id, Class<?> type) throws ModuleException
	{

		Session openSession = null;
		BaseBO baseBO = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + type.getName() + " where id = '" + id + "' ");
			List list = createQuery.list();
			if (list != null && list.size() > 0)
			{
				baseBO = (BaseBO) (type.cast(list.get(0)));
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
		return baseBO;

	}

	public static BaseBO findBaseBOBySpecification(String spec, Class<?> type) throws ModuleException
	{

		Session openSession = null;
		BaseBO baseBO = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + type.getName() + " where " + spec);
			List list = createQuery.list();
			if (list != null && list.size() > 0)
			{
				baseBO = (BaseBO) (type.cast(list.get(0)));
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
		return baseBO;

	}

	public static List findBaseBOsBySpecification(String spec, Class<?> type) throws ModuleException
	{

		Session openSession = null;
		List list = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + type.getName() + " where " + spec);
			list = createQuery.list();

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
		return list;

	}

	public static void saveOrUpdate(BaseBO baseBO) throws Exception
	{
		Session openSession = null;
		try
		{
			openSession = BaseService.getSession();
			Transaction beginTransaction = openSession.beginTransaction();
			beginTransaction.begin();
			openSession.saveOrUpdate(baseBO);
			beginTransaction.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Error in saving");
		}
		finally
		{
			if (openSession != null)
				BaseService.closeSession(openSession);
		}
	}

	public static void delete(BaseDTO baseDTO, Class<?> type) throws Exception
	{
		Session openSession = null;
		try
		{
			BaseBO baseBO = findBaseBOById(Integer.parseInt(baseDTO.getId()), type);
			if (baseBO == null)
			{
				throw new Exception("Object not Found");
			}
			else
			{
				openSession = BaseService.getSession();
				Transaction beginTransaction = openSession.beginTransaction();
				beginTransaction.begin();
				openSession.delete(baseBO);
				beginTransaction.commit();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Error in saving");
		}
		finally
		{
			if (openSession != null)
				BaseService.closeSession(openSession);
		}
	}

	/**
	 * @param id
	 * @param type
	 * @param baseDTO
	 * @return
	 * @throws ModuleException
	 *             Type ==> Type of BO
	 */

	public static BaseDTO findBaseDTOById(int id, Class<?> boType, BaseDTO baseDTO) throws ModuleException
	{
		BaseBO baseBO = findBaseBOById(id, boType);
		return baseBO.toDTO(baseDTO);

	}

	@SuppressWarnings("unchecked")
	public static void fillGenreMap()
	{
		Session openSession = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + GenreBO.class.getName());
			List<GenreBO> list = createQuery.list();
			for (GenreBO genreBO : list)
			{
				genreMap.put("" + genreBO.getCode(), genreBO);
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
	}

	public static GenreBO getGenreBO(String songGenre)
	{
		GenreBO genreBO = genreMap.get(songGenre);
		if (genreBO == null)
		{
			genreBO = getDefaultGenreBO();
		}
		return genreBO;
	}

	public static GenreBO getDefaultGenreBO()
	{
		return genreMap.get("-1");
	}

	public static AlbumBO getAlbumBO(String albumTitle, String yearReleased)
	{

		AlbumBO albumBO = albumMap.get(albumTitle);
		if (albumBO == null)
		{
			try
			{
				albumBO = (AlbumBO) BaseService.findBaseBOBySpecification(" albumTitle = '" + albumTitle + "'", AlbumBO.class);
				if (albumBO != null)
				{
					Integer noOfSongs = albumBO.getNoOfSongs();
					albumBO.setNoOfSongs(noOfSongs++);
					albumMap.put(albumTitle, albumBO);
				}
				else
				{
					// create AlbumBO
					albumBO = new AlbumBO();
					albumBO.setAlbumTitle(albumTitle);
					albumBO.setAlbumCategoryId(1);
					albumBO.setCreated(DateUtils.now());
					albumBO.setKeyTagId(1);
					albumBO.setYearReleased(yearReleased);
					albumBO.setNoOfTimesDownloaded(0);
					albumBO.setNoOfTimesPlayed(0);
					albumBO.setNoOfSongs(1);
					BaseService.saveOrUpdate(albumBO);
					albumBO = (AlbumBO) BaseService.findBaseBOBySpecification(" albumTitle = '" + albumTitle + "'", AlbumBO.class);
					albumMap.put(albumTitle, albumBO);
				}
			}
			catch (Exception e)
			{
				System.out.println("Album " + albumTitle + " not found");
				e.printStackTrace();
			}
		}
		return albumBO;
	}

}
