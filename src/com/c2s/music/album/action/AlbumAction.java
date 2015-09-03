package com.c2s.music.album.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.c2s.music.album.dto.AlbumDTO;
import com.c2s.music.album.form.AlbumForm;
import com.c2s.music.base.action.BaseAction;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.song.bean.SongBO;
import com.c2s.music.song.dto.SongDTO;
import com.c2s.music.user.dto.UserDTO;
import com.c2s.music.user.dto.UserPlayListDTO;
import com.c2s.music.user.form.UserPlayListForm;

public class AlbumAction extends BaseAction
{

	public ActionForward viewAlbum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String albumId = "" + request.getParameter("albumid");
		List<AlbumDTO> listAlbums = AlbumDTO.listAlbums("id = " + albumId, "", true);
		AlbumDTO albumDTO = (AlbumDTO) listAlbums.get(0);
		AlbumForm albumForm = (AlbumForm) form;
		albumForm.fillForm(albumDTO, true);
		albumForm.setAlbumFilePath(request.getContextPath() + "/Images/AlbumImage/" + albumDTO.getAlbumTitle() + "/AlbumArtSmall.jpg");
		request.setAttribute("AlbumForm", albumForm);
		return mapping.findForward("view");
	}

	public ActionForward addAlbumToPlaylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		UserPlayListDTO playListDTO = null;
		if (user != null)
			playListDTO = user.getPlayListDTO();
		else
		{
			playListDTO = (UserPlayListDTO) request.getSession().getAttribute("playlist");
			if (playListDTO == null)
				playListDTO = new UserPlayListDTO();
		}

		String albumId = "" + request.getParameter("albumid");
		List<AlbumDTO> listAlbums = AlbumDTO.listAlbums("id = " + albumId, "", true);
		AlbumDTO albumDTO = (AlbumDTO) listAlbums.get(0);
		List<SongDTO> songList = albumDTO.getSongList();

		response.setContentType("text/xml");
		response.setHeader("cache-control", "no-cache");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<SONGS>");
		System.out.println("<SONGS>");
		for (SongDTO songDTO : songList)
		{
			playListDTO.addPlaylistSongDTOs(0, songDTO, playListDTO.getNoOfTrancks() + 1);
			out.println("<SONG>");
			out.println("<TITLE>");
			out.println(songDTO.getSongTitle());
			out.println("</TITLE>");
			out.println("<ARTIST>");
			out.println(songDTO.getArtist() == null ? "" : songDTO.getArtist());
			out.println("</ARTIST>");
			out.println("</SONG>");
			System.out.println("<SONG>" + "<TITLE>" + songDTO.getSongTitle() + "</TITLE>" + "<ARTIST>" + songDTO.getArtist() + "</ARTIST>" + "</SONG>");
		}
		System.out.println("</SONGS>");
		out.println("</SONGS>");
		out.println();
		out.flush();

		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("playlist", playListDTO);

		return null;
	}

	public ActionForward addSongToPlaylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		UserPlayListDTO playListDTO = user.getPlayListDTO();
		String songId = "" + request.getParameter("songId");
		SongDTO songDTO = new SongDTO();
		songDTO = (SongDTO) BaseService.findBaseDTOById(Integer.parseInt(songId), SongBO.class, songDTO);
		playListDTO.addPlaylistSongDTOs(0, songDTO, playListDTO.getNoOfTrancks() + 1);
		request.getSession().setAttribute("user", user);
		return null;
	}

	public ActionForward removeSongToPlaylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		UserPlayListDTO playListDTO = user.getPlayListDTO();
		String songId = "" + request.getParameter("songId");
		playListDTO.removePlaylistSongDTOs(Integer.parseInt(songId));
		request.getSession().setAttribute("user", user);
		return null;
	}
}