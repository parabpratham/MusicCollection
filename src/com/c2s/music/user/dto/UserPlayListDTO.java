package com.c2s.music.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.song.dto.SongDTO;

public class UserPlayListDTO extends BaseDTO
{
	private UserDTO					userDTO;

	private Integer					noOfTrancks;

	private Integer					maxNoOfTrack;

	private List<PlaylistSongDTO>	playlistSongDTOs;

	public UserPlayListDTO()
	{
		playlistSongDTOs = new ArrayList<PlaylistSongDTO>();
		noOfTrancks = 0;
		maxNoOfTrack = 0;
	}

	public UserDTO getUserDTO()
	{
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO)
	{
		this.userDTO = userDTO;
	}

	public Integer getNoOfTrancks()
	{
		return noOfTrancks;
	}

	public void setNoOfTrancks(Integer noOfTrancks)
	{
		this.noOfTrancks = noOfTrancks;
	}

	public List<PlaylistSongDTO> getPlaylistSongDTOs()
	{
		return playlistSongDTOs;
	}

	public void setPlaylistSongDTOs(List<PlaylistSongDTO> playlistSongDTOs)
	{
		this.playlistSongDTOs = playlistSongDTOs;
	}

	public void addPlaylistSongDTOs(Integer id, SongDTO song, Integer trackNo)
	{
		PlaylistSongDTO playlistSongDTO = new PlaylistSongDTO();
		playlistSongDTO.setId(id);
		playlistSongDTO.setSong(song);
		playlistSongDTO.setTranckNO(trackNo);
		getPlaylistSongDTOs().add(playlistSongDTO);
		setNoOfTrancks(getNoOfTrancks() + 1);
	}

	public void removePlaylistSongDTOs(Integer id)
	{
		for (PlaylistSongDTO playlistSongDTO : getPlaylistSongDTOs())
		{
			if (id == playlistSongDTO.getTranckNO())
			{
				playlistSongDTO.setIsRemoved(true);
				setNoOfTrancks(getNoOfTrancks() - 1);
				break;
			}
		}
	}

	public class PlaylistSongDTO
	{
		private Integer	id;

		private SongDTO	song;

		private Integer	tranckNO;

		private Boolean	isRemoved;

		public Boolean getIsRemoved()
		{
			return isRemoved;
		}

		public void setIsRemoved(Boolean isRemoved)
		{
			this.isRemoved = isRemoved;
		}

		public SongDTO getSong()
		{
			return song;
		}

		public void setSong(SongDTO song)
		{
			this.song = song;
		}

		public Integer getTranckNO()
		{
			return tranckNO;
		}

		public void setTranckNO(Integer tranckNO)
		{
			this.tranckNO = tranckNO;
		}

		public Integer getId()
		{
			return id;
		}

		public void setId(Integer id)
		{
			this.id = id;
		}
	}

	public Integer getMaxNoOfTrack()
	{
		return maxNoOfTrack;
	}

	public void setMaxNoOfTrack(Integer maxNoOfTrack)
	{
		this.maxNoOfTrack = maxNoOfTrack;
	}

}
