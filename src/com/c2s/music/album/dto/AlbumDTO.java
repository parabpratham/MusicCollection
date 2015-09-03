package com.c2s.music.album.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.ModuleException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.c2s.music.album.bean.AlbumBO;
import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.song.dto.SongDTO;

public class AlbumDTO extends BaseDTO
{

	private String			albumTitle;

	private String			yearReleased;

	private String			noOfSongs;

	private String			created;

	private String			noOfTimesPlayed;

	private String			noOfTimesDownloaded;

	private String			keyTagId;

	private String			albumCategoryId;

	private String			albumFilePath;

	private List<SongDTO>	songList;

	public String getAlbumTitle()
	{
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}

	public String getYearReleased()
	{
		return yearReleased;
	}

	public void setYearReleased(String yearReleased)
	{
		this.yearReleased = yearReleased;
	}

	public String getNoOfSongs()
	{
		return noOfSongs;
	}

	public void setNoOfSongs(String noOfSongs)
	{
		this.noOfSongs = noOfSongs;
	}

	public String getCreated()
	{
		return created;
	}

	public void setCreated(String created)
	{
		this.created = created;
	}

	public String getNoOfTimesPlayed()
	{
		return noOfTimesPlayed;
	}

	public void setNoOfTimesPlayed(String noOfTimesPlayed)
	{
		this.noOfTimesPlayed = noOfTimesPlayed;
	}

	public String getNoOfTimesDownloaded()
	{
		return noOfTimesDownloaded;
	}

	public void setNoOfTimesDownloaded(String noOfTimesDownloaded)
	{
		this.noOfTimesDownloaded = noOfTimesDownloaded;
	}

	public String getKeyTagId()
	{
		return keyTagId;
	}

	public void setKeyTagId(String keyTagId)
	{
		this.keyTagId = keyTagId;
	}

	public String getAlbumCategoryId()
	{
		return albumCategoryId;
	}

	public void setAlbumCategoryId(String albumCategoryId)
	{
		this.albumCategoryId = albumCategoryId;
	}

	public static List<AlbumDTO> listLatestAlbums()
	{
		String whereSpec = " rownum < 11 ";
		String specification = " order by created ";
		return listAlbums(whereSpec, specification, false);
	}

	public static List<AlbumDTO> listMostViewedAlbums()
	{
		String whereSpec = " rownum < 11 ";
		String specification = " order by noOfTimesPlayed ";
		return listAlbums(whereSpec, specification, false);
	}

	public static List<AlbumDTO> listAlbums(String whereSpecification, String orderSpecification, Boolean isSongsRequired)
	{
		List<AlbumDTO> albumList = null;
		try
		{
			albumList =findBaseDTOList(AlbumBO.class.getName(), AlbumDTO.class.getName(), whereSpecification, orderSpecification, isSongsRequired);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return albumList;
	}

	private static List<AlbumDTO> findBaseDTOList(String BOClass, String dtoClass, String whereSpecification, String orderSpecification, Boolean isSongsRequired)
	{
		Session openSession = null;
		List<AlbumDTO> dtoList = new ArrayList<AlbumDTO>();
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
				AlbumBO baseBO = (AlbumBO) BO;
				AlbumDTO dto = null;
				if (isSongsRequired)
					dto = baseBO.toDTO(dto, isSongsRequired);
				else
					dto = (AlbumDTO) baseBO.toDTO(dtoClass);
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

	public String getAlbumFilePath()
	{
		return albumFilePath;
	}

	public void setAlbumFilePath(String albumFilePath)
	{
		this.albumFilePath = albumFilePath;
	}

	public List<SongDTO> getSongList()
	{
		return songList;
	}

	public void setSongList(List<SongDTO> songList)
	{
		this.songList = songList;
	}
}