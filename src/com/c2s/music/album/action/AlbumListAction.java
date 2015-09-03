package com.c2s.music.album.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.c2s.music.album.dto.AlbumDTO;
import com.c2s.music.album.form.AlbumForm;
import com.c2s.music.base.action.BaseListAction;
import com.c2s.music.base.dto.BaseDTO;
import com.c2s.music.user.dto.UserDTO;
import com.c2s.music.user.form.UserForm;

public class AlbumListAction extends BaseListAction
{

	public static Map<String, AlbumForm>	latestAlbums		= null;
	public static Map<String, AlbumForm>	mostViewedAlbums	= null;

	public ActionForward homePageList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String path = request.getContextPath();
		if (mostViewedAlbums == null)
			setLatestAlbums(path);

		if (mostViewedAlbums == null)
			setMostViewedAlbums(path);
		request.setAttribute("latestAlbums", latestAlbums);
		request.setAttribute("mostViewedAlbums", mostViewedAlbums);
		return mapping.findForward("success");
	}

	private void setLatestAlbums(String path)
	{

		List<AlbumDTO> listLatestAlbums = AlbumDTO.listLatestAlbums();
		latestAlbums = new HashMap<String, AlbumForm>();
		for (AlbumDTO albumDTO : listLatestAlbums)
		{
			AlbumForm albumForm = new AlbumForm();
			albumForm.fillForm(albumDTO, false);
			albumForm.setAlbumFilePath(path + "/Images/AlbumImage/" + albumDTO.getAlbumTitle() + "/AlbumArtSmall.jpg");
			latestAlbums.put(albumDTO.getAlbumTitle(), albumForm);
		}
	}

	private void setMostViewedAlbums(String path)
	{

		List<AlbumDTO> listLatestAlbums = AlbumDTO.listMostViewedAlbums();
		mostViewedAlbums = new HashMap<String, AlbumForm>();
		for (AlbumDTO albumDTO : listLatestAlbums)
		{
			AlbumForm albumForm = new AlbumForm();
			albumForm.fillForm(albumDTO, false);
			albumForm.setAlbumFilePath(path + "/Images/AlbumImage/" + albumDTO.getAlbumTitle() + "/AlbumArtSmall.jpg");
			mostViewedAlbums.put(albumDTO.getAlbumTitle(), albumForm);
		}
	}
}
