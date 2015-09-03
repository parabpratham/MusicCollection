package com.c2s.music.user.form;

import java.util.ArrayList;
import java.util.List;

import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.base.form.BaseForm;
import com.c2s.music.song.form.SongForm;

public class UserPlayListForm extends BaseForm
{
	private static final long		serialVersionUID	= -3414489438561451254L;

	private UserForm				userForm;

	private Integer					noOfTrancks;

	private List<PlaylistSongForm>	playlistSongForms;

	public UserForm getUserForm()
	{
		return userForm;
	}

	public void setUserForm(UserForm userForm)
	{
		this.userForm = userForm;
	}

	public List<PlaylistSongForm> getPlaylistSongForms()
	{
		return playlistSongForms;
	}

	public void setPlaylistSongForms(List<PlaylistSongForm> playlistSongForms)
	{
		this.playlistSongForms = playlistSongForms;
	}

	public UserPlayListForm()
	{
		playlistSongForms = new ArrayList<PlaylistSongForm>();
		noOfTrancks = 0;
	}

	public Integer getNoOfTrancks()
	{
		return noOfTrancks;
	}

	public void setNoOfTrancks(Integer noOfTrancks)
	{
		this.noOfTrancks = noOfTrancks;
	}

	public void addPlaylistSongDTOs(Integer id, SongForm song, Integer trackNo)
	{
		PlaylistSongForm playlistSongForm = new PlaylistSongForm();
		playlistSongForm.setId(id);
		playlistSongForm.setSong(song);
		playlistSongForm.setTranckNO(trackNo);
		getPlaylistSongForms().add(playlistSongForm);
		setNoOfTrancks(getNoOfTrancks() + 1);
	}

	public void removePlaylistSongForm(Integer id)
	{
		for (PlaylistSongForm playlistSongForm : getPlaylistSongForms())
		{
			if (id == playlistSongForm.getTranckNO())
			{
				playlistSongForm.setIsRemoved(true);
				setNoOfTrancks(getNoOfTrancks() - 1);
				break;
			}
		}
	}

	public class PlaylistSongForm
	{
		private Integer		id;

		private SongForm	song;

		private Integer		tranckNO;

		private Boolean		isRemoved;

		public Boolean getIsRemoved()
		{
			return isRemoved;
		}

		public void setIsRemoved(Boolean isRemoved)
		{
			this.isRemoved = isRemoved;
		}

		public SongForm getSong()
		{
			return song;
		}

		public void setSong(SongForm song)
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

	@Override
	public BaseDTO fillDTO(BaseDTO baseDTO)
	{
		// TODO Auto-generated method stub
		return super.fillDTO(baseDTO);
	}

	@Override
	public void fillForm(BaseDTO baseDTO)
	{
		// TODO Auto-generated method stub
		super.fillForm(baseDTO);
	}
	
	
}