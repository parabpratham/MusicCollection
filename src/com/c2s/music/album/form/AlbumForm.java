package com.c2s.music.album.form;

import java.util.ArrayList;
import java.util.List;

import com.c2s.music.album.dto.AlbumDTO;
import com.c2s.music.base.form.BaseForm;
import com.c2s.music.song.dto.SongDTO;
import com.c2s.music.song.form.SongForm;

public class AlbumForm extends BaseForm
{
	private static final long	serialVersionUID	= 1243731122909100504L;

	private String				albumTitle;

	private String				yearReleased;

	private String				noOfSongs;

	private String				created;

	private String				noOfTimesPlayed;

	private String				noOfTimesDownloaded;

	private String				keyTagId;

	private String				albumCategoryId;

	private String				albumFilePath;

	private List<SongForm>		songList;

	public String getAlbumFilePath()
	{
		return albumFilePath;
	}

	public void setAlbumFilePath(String albumFilePath)
	{
		this.albumFilePath = albumFilePath;
	}

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

	public void fillForm(AlbumDTO albumDTO, Boolean songListReqd)
	{
		super.fillForm(albumDTO);
		setId(albumDTO.getId());
		if (songListReqd)
		{
			List<SongDTO> songList = albumDTO.getSongList();
			List<SongForm> songTempList = new ArrayList<SongForm>();
			for (SongDTO songDTO : songList)
			{
				SongForm form = new SongForm();
				form.fillForm(songDTO);
				songTempList.add(form);
			}
			setSongList(songTempList);
		}
	}

	public List<SongForm> getSongList()
	{
		return songList;
	}

	public void setSongList(List<SongForm> songList)
	{
		this.songList = songList;
	}

}