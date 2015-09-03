package com.c2s.music.album.bean;

import java.util.List;

import org.apache.struts.util.ModuleException;

import com.c2s.music.album.dto.AlbumDTO;
import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.song.bean.SongBO;
import com.c2s.music.song.dto.SongDTO;

public class AlbumBO extends BaseBO
{

	public AlbumBO()
	{
	}

	private String			albumTitle;

	private String			yearReleased;

	private Integer			noOfSongs;

	private Integer			noOfTimesPlayed;

	private Integer			noOfTimesDownloaded;

	private Integer			keyTagId;

	private Integer			albumCategoryId;

	private List<SongBO>	songList;

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

	public Integer getNoOfSongs()
	{
		return noOfSongs;
	}

	public void setNoOfSongs(Integer noOfSongs)
	{
		this.noOfSongs = noOfSongs;
	}

	public Integer getNoOfTimesPlayed()
	{
		return noOfTimesPlayed;
	}

	public void setNoOfTimesPlayed(Integer noOfTimesPlayed)
	{
		this.noOfTimesPlayed = noOfTimesPlayed;
	}

	public Integer getNoOfTimesDownloaded()
	{
		return noOfTimesDownloaded;
	}

	public void setNoOfTimesDownloaded(Integer noOfTimesDownloaded)
	{
		this.noOfTimesDownloaded = noOfTimesDownloaded;
	}

	public Integer getKeyTagId()
	{
		return keyTagId;
	}

	public void setKeyTagId(Integer keyTagId)
	{
		this.keyTagId = keyTagId;
	}

	public Integer getAlbumCategoryId()
	{
		return albumCategoryId;
	}

	public void setAlbumCategoryId(Integer albumCategoryId)
	{
		this.albumCategoryId = albumCategoryId;
	}

	public List<SongBO> getSongList()
	{
		return songList;
	}

	public void setSongList(List<SongBO> songList)
	{
		this.songList = songList;
	}

	public AlbumDTO toDTO(AlbumDTO dto, Boolean isSongsRequired)
	{
		dto = new AlbumDTO();
		dto = (AlbumDTO) super.toDTO(dto);
		if (isSongsRequired)
		{
			try
			{
				List<SongDTO> findSongDTOList = (List<SongDTO>) BaseService.findBaseDTOList(SongBO.class.getName(), SongDTO.class.getName(), " albumId = " + dto.getId(), "order by trackNoInAlbum");
				dto.setSongList(findSongDTOList);
			}
			catch (ModuleException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

}