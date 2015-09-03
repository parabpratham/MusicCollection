package com.c2s.music.song.bean;

import java.io.IOException;
import java.util.Date;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;

import com.c2s.music.album.bean.AlbumBO;
import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.service.BaseService;

public class SongBO extends BaseBO
{

	public String	songTitle;

	public String	songFilePath;

	public AlbumBO	album;

	public Integer	albumId;

	public String	albumTitle;

	public String	artist;

	public String	composer;

	public String	yearReleased;

	public GenreBO	genre;

	public String	lyrics;

	public Integer	trackNoInAlbum;

	public Integer	frameCount;

	public Integer	fileSize;

	public Integer	bitRate;

	public Double	frequency;

	public String	id3v1Tag;

	public String	id3v2Tag;

	public Integer	noOfTimesPlayed;

	public Integer	keyTagId;

	public Integer	songCategoryId;

	public SongBO(String filePath)
	{
		try
		{
			MP3File mp3 = new MP3File(filePath);
			AbstractID3v2 iD3v2Tag = mp3.getID3v2Tag();
			ID3v1 tag = mp3.getID3v1Tag();

			setSongFilePath(filePath);
			setSongTitle((tag != null ? tag.getSongTitle() : ""));
			AlbumBO albumBO = BaseService.getAlbumBO(tag != null ? tag.getAlbumTitle() : "Untitled", tag.getYearReleased());
			setAlbum(albumBO);
			setArtist((tag != null ? tag.getLeadArtist() : ""));
			try
			{
				String authorComposer = (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : "").replace("\\s", "");
				if (authorComposer.length() > 100)
					setComposer("");
				else
					setComposer(authorComposer);
			}
			catch (Exception e)
			{
				setComposer("");
			}

			setFileSize((tag != null ? tag.getSize() : 0));
			setFrameCount((iD3v2Tag != null ? iD3v2Tag.getFrameCount() : 0));
			setBitRate(mp3.getBitRate());
			setFrequency(mp3.getFrequency());

			try
			{
				GenreBO genreBO = BaseService.getGenreBO(tag.getSongGenre());
				setGenre(genreBO);
			}
			catch (Exception e)
			{
				setGenre(BaseService.getDefaultGenreBO());
			}

			setId3v1Tag((tag != null ? tag.toString() : ""));

			setKeyTagId(1);

			setLyrics((iD3v2Tag != null ? iD3v2Tag.getSongLyric() : ""));

			setNoOfTimesPlayed(0);

			setSongCategoryId(1);

			String trackNumberOnAlbum = (tag != null ? tag.getTrackNumberOnAlbum() : "0");
			System.out.println("trackNumberOnAlbum:" + trackNumberOnAlbum);
			try
			{
				setTrackNoInAlbum(Integer.parseInt(trackNumberOnAlbum));
			}
			catch (Exception e)
			{
				setTrackNoInAlbum(0);
			}
			setYearReleased((tag != null ? tag.getYearReleased() : ""));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (TagException e)
		{
			e.printStackTrace();
		}

	}

	public SongBO()
	{
	}

	public String getSongTitle()
	{
		return songTitle;
	}

	public void setSongTitle(String songTitle)
	{
		this.songTitle = songTitle;
	}

	public Integer getAlbumId()
	{
		return getAlbum().getId();
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getComposer()
	{
		return composer;
	}

	public void setComposer(String composer)
	{
		this.composer = composer;
	}

	public String getYearReleased()
	{
		return yearReleased;
	}

	public void setYearReleased(String yearReleased)
	{
		this.yearReleased = yearReleased;
	}

	public GenreBO getGenre()
	{
		return genre;
	}

	public void setGenre(GenreBO genre)
	{
		this.genre = genre;
	}

	public String getLyrics()
	{
		return lyrics;
	}

	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Integer getTrackNoInAlbum()
	{
		return trackNoInAlbum;
	}

	public void setTrackNoInAlbum(Integer trackNoInAlbum)
	{
		this.trackNoInAlbum = trackNoInAlbum;
	}

	public Integer getFrameCount()
	{
		return frameCount;
	}

	public void setFrameCount(Integer frameCount)
	{
		this.frameCount = frameCount;
	}

	public Integer getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(Integer fileSize)
	{
		this.fileSize = fileSize;
	}

	public Integer getBitRate()
	{
		return bitRate;
	}

	public void setBitRate(Integer bitRate)
	{
		this.bitRate = bitRate;
	}

	public Double getFrequency()
	{
		return frequency;
	}

	public void setFrequency(double frequency)
	{
		this.frequency = frequency;
	}

	public String getId3v1Tag()
	{
		return id3v1Tag;
	}

	public void setId3v1Tag(String id3v1Tag)
	{
		this.id3v1Tag = id3v1Tag;
	}

	public String getId3v2Tag()
	{
		return id3v2Tag;
	}

	public void setId3v2Tag(String id3v2Tag)
	{
		this.id3v2Tag = id3v2Tag;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Integer getNoOfTimesPlayed()
	{
		return noOfTimesPlayed;
	}

	public void setNoOfTimesPlayed(Integer noOfTimesPlayed)
	{
		this.noOfTimesPlayed = noOfTimesPlayed;
	}

	public Integer getKeyTagId()
	{
		return keyTagId;
	}

	public void setKeyTagId(Integer keyTagId)
	{
		this.keyTagId = keyTagId;
	}

	public Integer getSongCategoryId()
	{
		return songCategoryId;
	}

	public void setSongCategoryId(Integer songCategoryId)
	{
		this.songCategoryId = songCategoryId;
	}

	public AlbumBO getAlbum()
	{
		return album;
	}

	public void setAlbum(AlbumBO album)
	{
		this.album = album;
	}

	public String getAlbumTitle()
	{
		return getAlbum().getAlbumTitle();
	}

	public String getSongFilePath()
	{
		return songFilePath;
	}

	public void setSongFilePath(String songFilePath)
	{
		this.songFilePath = songFilePath;
	}

	public void setFrequency(Double frequency)
	{
		this.frequency = frequency;
	}
}