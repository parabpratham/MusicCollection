package com.c2s.music.user.bean;

import java.util.List;

import org.apache.struts.util.ModuleException;

import com.c2s.music.base.bean.BaseBO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.song.bean.SongBO;
import com.c2s.music.song.dto.SongDTO;
import com.c2s.music.user.dto.UserPlayListDTO;
import com.c2s.music.user.dto.UserPlayListDTO.PlaylistSongDTO;

public class UserPlayListBO extends BaseBO
{

	private UserBO					userBO;

	private SongBO					songBO;

	private Integer					tranckNO;

	private List<PlaylistSongBO>	playlistSongBOs;

	private Integer					noOfTrancks;

	private String					isAvailable	= "N";

	public UserBO getUserBO()
	{
		return userBO;
	}

	public void setUserBO(UserBO userBO)
	{
		this.userBO = userBO;
	}

	private class PlaylistSongBO
	{

		private Integer	id;

		private SongBO	song;

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

		public SongBO getSong()
		{
			return song;
		}

		public void setSong(SongBO song)
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

	public List<PlaylistSongBO> getPlaylistSongBOs()
	{
		return playlistSongBOs;
	}

	public void setPlaylistSongBOs(List<PlaylistSongBO> playlistSongBOs)
	{
		this.playlistSongBOs = playlistSongBOs;
	}

	private SongBO getSongBO()
	{
		return songBO;
	}

	private void setSongBO(SongBO songBO)
	{
		this.songBO = songBO;
	}

	private Integer getTranckNO()
	{
		return tranckNO;
	}

	private void setTranckNO(Integer tranckNO)
	{
		this.tranckNO = tranckNO;
	}

	public void saveOrUpdate() throws Exception
	{
		for (PlaylistSongBO playlistSong : getPlaylistSongBOs())
		{
			UserPlayListBO listBO = new UserPlayListBO();
			listBO.setId(playlistSong.getId());
			listBO.setUserBO(getUserBO());
			listBO.setSongBO(playlistSong.getSong());
			listBO.setTranckNO(playlistSong.getTranckNO());
			if (playlistSong.getIsRemoved())
				listBO.setIsAvailable("Y");
			BaseService.saveOrUpdate(listBO);
		}
	}

	public void saveOrUpdate(UserPlayListDTO userPlayListDto) throws Exception
	{
		setUserBO((UserBO) userPlayListDto.getUserDTO().toBO(new UserBO()));
		List<PlaylistSongDTO> playlistSongDTOs = userPlayListDto.getPlaylistSongDTOs();
		for (PlaylistSongDTO playlistSongDTO : playlistSongDTOs)
		{
			PlaylistSongBO playlistSongBO = new PlaylistSongBO();
			playlistSongBO.setId(playlistSongDTO.getId());
			playlistSongBO.setSong((SongBO) (playlistSongDTO.getSong().toBO(new SongBO())));
			playlistSongBO.setTranckNO(playlistSongDTO.getTranckNO());
			playlistSongBO.setIsRemoved(playlistSongDTO.getIsRemoved());
			getPlaylistSongBOs().add(playlistSongBO);
		}
	}

	public UserPlayListDTO toDTO(UserPlayListDTO dto)
	{
		dto = new UserPlayListDTO();
		dto = (UserPlayListDTO) super.toDTO(dto);
		try
		{
			String whereSpec = " userId = " + dto.getUserDTO().getId() + " and IsAvailable = 'N' " + " order by tranckNO";
			List findBaseBOsBySpecification = BaseService.findBaseBOsBySpecification(whereSpec, UserPlayListBO.class);
			for (Object object : findBaseBOsBySpecification)
			{
				UserPlayListBO listBO = (UserPlayListBO) object;
				dto.addPlaylistSongDTOs(listBO.getId(), (SongDTO) listBO.getSongBO().toDTO(new SongDTO()), listBO.getTranckNO());
				dto.setMaxNoOfTrack(dto.getMaxNoOfTrack() + 1);
			}
		}
		catch (ModuleException e)
		{
			e.printStackTrace();
		}
		return dto;
	}

	public Integer getNoOfTrancks()
	{
		return noOfTrancks;
	}

	public void setNoOfTrancks(Integer noOfTrancks)
	{
		this.noOfTrancks = noOfTrancks;
	}

	public String getIsAvailable()
	{
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable)
	{
		this.isAvailable = isAvailable;
	}
}
