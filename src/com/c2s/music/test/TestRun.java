package com.c2s.music.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.c2s.music.album.bean.AlbumBO;
import com.c2s.music.base.service.BaseService;

public class TestRun
{
	public static void main(String[] args)
	{
		Session openSession = BaseService.getSession();
		Query createQuery = openSession.createQuery("from " + AlbumBO.class.getName());
		List<AlbumBO> list = createQuery.list();
		try
		{
			for (AlbumBO genreBO : list)
			{
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			BaseService.closeSession(openSession);
		}
		System.out.println("Connection succesfull");
	}

}
