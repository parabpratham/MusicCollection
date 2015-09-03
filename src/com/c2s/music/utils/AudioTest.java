package com.c2s.music.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.media.Format;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.c2s.music.album.bean.AlbumBO;
import com.c2s.music.base.service.BaseService;
import com.c2s.music.song.bean.GenreBO;
import com.c2s.music.song.bean.SongBO;

public class AudioTest
{
	public static int					count		= 1;

	public static Map<String, AlbumBO>	albumMap	= new HashMap<String, AlbumBO>();
	public static Map<String, GenreBO>	genreMap	= new HashMap<String, GenreBO>();
	public static List<File>			songList	= new LinkedList<File>();

	public static void main(String[] args)
	{

		ListSongs();

		// FillSongs();
	}

	private static void FillSongs()
	{

		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]
		{ input1, input2 }, new Format[]
		{ output }, PlugInManager.CODEC);
		try
		{
			// Player player = Manager.createPlayer(new MediaLocator(new
			// File("F:/Songs/English/2046485.mp3").toURI().toURL()));
			// player.start();
			File directory = new File("F:/Songs/Hindi/New Movies/");

			fillGenreMap();
			listSongDetails(directory);
			count = 1;
			System.out.println("===============");
			Set<String> albumKeySet = albumMap.keySet();
			for (String albumTitle : albumKeySet)
			{
				try
				{
					AlbumBO albumBO = albumMap.get(albumTitle);
					// BaseService.saveOrUpdate(albumBO);
					File file = new File("G:/Projects/MusicCollection/WebRoot/Images/AlbumImage/" + albumTitle);
					file.mkdir();
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
			System.out.println("===============");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.exit(0);
	}

	private static void ListSongs()
	{
		File directory = new File("C:/Users/user/Desktop/songs/Sing is king");
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]
		{ input1, input2 }, new Format[]
		{ output }, PlugInManager.CODEC);
		try
		{
			for (File file : directory.listFiles())
			{
				if (file.getName().contains(".mp3"))
				{
					MP3File mp3 = new MP3File(file.getAbsolutePath());
					System.out.println(count++ + "> " + file.getName());
					String songTitle = file.getName();
					try
					{
						AbstractID3v2 iD3v2Tag = mp3.getID3v2Tag();
						ID3v1 tag = mp3.getID3v1Tag();

						System.out.println("FilePath : " + file.getAbsolutePath());

						System.out.println("Title:" + (tag != null ? tag.getSongTitle().replace("- www.Songs.PK", "") : ""));

						System.out.println("Album : " + tag.getAlbumTitle());

						System.out.println("Artist : " + (tag != null ? tag.getLeadArtist() : ""));

					//	System.out.println("Composer:" + (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : ""));
						try
						{
							String authorComposer = (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : "");
							//System.out.println("Composer:" + authorComposer);
						}
						catch (Exception e)
						{
						}

						System.out.println("Created :" + DateUtils.now());

						System.out.println("Size : " + (tag != null ? tag.getSize() : 0));

						System.out.println("FrameCount :" + (iD3v2Tag != null ? iD3v2Tag.getFrameCount() : 0));

						System.out.println("BitRate :" + mp3.getBitRate());

						System.out.println("Frequency :" + mp3.getFrequency());
						System.out.println("Frequency :" + tag.getSongGenre());
						try
						{
							GenreBO genreBO = genreMap.get(tag.getSongGenre());
							if (genreBO == null)
							{
								genreBO = genreMap.get("-1");
							}
						}
						catch (Exception e)
						{
						}

				//		System.out.println("Id3v1Tag :" + (tag != null ? tag.toString() : ""));

						// utf16_asccii(iD3v2Tag.toString());
						// System.out.println("Id3v2Tag :" + (iD3v2Tag !=
						// null ? utf16_asccii(iD3v2Tag.toString()) : ""));
						// songBO.setId3v2Tag((iD3v2Tag != null ?
						// iD3v2Tag.toString() : ""));

						System.out.println("KeyTagId :" + 1);

						System.out.println("Lyric :" + (iD3v2Tag != null ? iD3v2Tag.getSongLyric() : ""));

						System.out.println("NoOfTimesPlayed :" + 1);

						System.out.println("SongCategoryId:" + 1);

						String trackNumberOnAlbum = (tag != null ? tag.getTrackNumberOnAlbum() : "0");
						System.out.println("trackNumberOnAlbum:" + trackNumberOnAlbum);

					//	System.out.println("Year:" + (tag != null ? tag.getYearReleased() : ""));

						// BaseService.saveOrUpdate(songBO);
						System.out.println("-------------");
					}
					catch (Exception e)
					{
						System.out.println("Song " + songTitle + " not found");
						e.printStackTrace();
					}

				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static void fillGenreMap()
	{
		Session openSession = null;
		try
		{
			openSession = BaseService.getSession();
			Query createQuery = openSession.createQuery("from " + GenreBO.class.getName());
			List<GenreBO> list = createQuery.list();
			for (GenreBO genreBO : list)
			{
				genreMap.put("" + genreBO.getCode(), genreBO);
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
	}

	public static AlbumBO getAlbumBO(String albumTitle, String yearReleased)
	{
		AlbumBO albumBO = albumMap.get(albumTitle);
		if (albumBO == null)
		{
			try
			{
				albumBO = (AlbumBO) BaseService.findBaseBOBySpecification(" albumTitle = '" + albumTitle + "'", AlbumBO.class);
				if (albumBO != null)
				{
					albumBO.setNoOfSongs(albumBO.getNoOfSongs() + 1);
					albumMap.put(albumTitle, albumBO);
				}
				else
				{
					// create AlbumBO
					albumBO = new AlbumBO();
					albumBO.setAlbumTitle(albumTitle);
					albumBO.setAlbumCategoryId(1);
					albumBO.setCreated(DateUtils.now());
					albumBO.setKeyTagId(1);
					albumBO.setYearReleased(yearReleased);
					albumBO.setNoOfTimesDownloaded(0);
					albumBO.setNoOfTimesPlayed(0);
					albumBO.setNoOfSongs(1);
					BaseService.saveOrUpdate(albumBO);
					albumBO = (AlbumBO) BaseService.findBaseBOBySpecification(" albumTitle = '" + albumTitle + "'", AlbumBO.class);
					albumMap.put(albumTitle, albumBO);
				}
			}
			catch (Exception e)
			{
				System.out.println("Album " + albumTitle + " not found");
				e.printStackTrace();
			}
		}
		return albumBO;
	}

	/**
	 * @param directory
	 */
	public static void listSongDetails(File directory)
	{
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			if (file.isDirectory())
			{
				listSongDetails(file);
			}
			else
			{
				try
				{
					if (file.getName().contains("AlbumArtSmall.jpg"))
					{
						String filePath = file.getAbsolutePath().replace("\\", "-");
						filePath = filePath.replace(":", "");
						String[] split = filePath.split("-");
						// System.out.println(file.getAbsolutePath().replace("F:.Song.Hindi.New
						// Movies",
						// "G:\\Projects\\MusicCollection\\WebRoot\\Images\\AlbumImage"));
						System.out.println("G:\\Projects\\MusicCollection\\WebRoot\\Images\\AlbumImage\\" + split[4] + "\\AlbumArtSmall.jpg");
						FileCopy1 copy1 = new FileCopy1();
						copy1.copy(file.getAbsolutePath(), "G:\\Projects\\MusicCollection\\WebRoot\\Images\\AlbumImage\\" + split[4] + "\\AlbumArtSmall.jpg");
						// System.out.println("G:/Projects/MusicCollection/WebRoot/Images/AlbumImage/");

					}

					else if (false && file.getName().contains(".mp3"))
					{
						MP3File mp3 = new MP3File(file.getAbsolutePath());
						System.out.println(count++ + "> " + file.getName());
						songList.add(file);
						String songTitle = file.getName();
						try
						{
							AbstractID3v2 iD3v2Tag = mp3.getID3v2Tag();
							ID3v1 tag = mp3.getID3v1Tag();
							SongBO songBO = new SongBO();

							System.out.println("FilePath : " + file.getAbsolutePath());
							songBO.setSongFilePath(file.getAbsolutePath());

							System.out.println("Title:" + (tag != null ? tag.getSongTitle().replace("- www.Songs.PK", "") : ""));
							songBO.setSongTitle((tag != null ? tag.getSongTitle() : ""));

							AlbumBO albumBO = getAlbumBO(tag != null ? tag.getAlbumTitle() : "Untitled", tag.getYearReleased());
							System.out.println("Album : " + albumBO.getAlbumTitle());
							songBO.setAlbum(albumBO);

							System.out.println("Artist : " + (tag != null ? tag.getLeadArtist() : ""));
							songBO.setArtist((tag != null ? tag.getLeadArtist() : ""));

							System.out.println("Composer:" + (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : ""));
							try
							{
								String authorComposer = (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : "");
								if (authorComposer.length() > 100)
									songBO.setComposer("");
								else
									songBO.setComposer(authorComposer);
							}
							catch (Exception e)
							{
								// TODO: handle exception
								songBO.setComposer("");
							}

							System.out.println("Created :" + DateUtils.now());
							songBO.setCreated(DateUtils.now());

							System.out.println("Size : " + (tag != null ? tag.getSize() : 0));
							songBO.setFileSize((tag != null ? tag.getSize() : 0));

							System.out.println("FrameCount :" + (iD3v2Tag != null ? iD3v2Tag.getFrameCount() : 0));
							songBO.setFrameCount((iD3v2Tag != null ? iD3v2Tag.getFrameCount() : 0));

							System.out.println("BitRate :" + mp3.getBitRate());
							songBO.setBitRate(mp3.getBitRate());

							System.out.println("Frequency :" + mp3.getFrequency());
							songBO.setFrequency(mp3.getFrequency());

							System.out.println("Frequency :" + tag.getSongGenre());
							try
							{
								GenreBO genreBO = genreMap.get(tag.getSongGenre());
								if (genreBO == null)
								{
									genreBO = genreMap.get("-1");
								}
								songBO.setGenre(genreBO);
							}
							catch (Exception e)
							{
								songBO.setGenre(genreMap.get("-1"));
							}

							System.out.println("Id3v1Tag :" + (tag != null ? tag.toString() : ""));
							songBO.setId3v1Tag((tag != null ? tag.toString() : ""));

							// utf16_asccii(iD3v2Tag.toString());
							// System.out.println("Id3v2Tag :" + (iD3v2Tag !=
							// null ? utf16_asccii(iD3v2Tag.toString()) : ""));
							// songBO.setId3v2Tag((iD3v2Tag != null ?
							// iD3v2Tag.toString() : ""));

							System.out.println("KeyTagId :" + 1);
							songBO.setKeyTagId(1);

							System.out.println("Lyric :" + (iD3v2Tag != null ? iD3v2Tag.getSongLyric() : ""));
							songBO.setLyrics((iD3v2Tag != null ? iD3v2Tag.getSongLyric() : ""));

							System.out.println("NoOfTimesPlayed :" + 1);
							songBO.setNoOfTimesPlayed(0);

							System.out.println("SongCategoryId:" + 1);
							songBO.setSongCategoryId(1);

							String trackNumberOnAlbum = (tag != null ? tag.getTrackNumberOnAlbum() : "0");
							System.out.println("trackNumberOnAlbum:" + trackNumberOnAlbum);
							try
							{
								songBO.setTrackNoInAlbum(Integer.parseInt(trackNumberOnAlbum));
							}
							catch (Exception e)
							{
								songBO.setTrackNoInAlbum(0);
							}

							System.out.println("Year:" + (tag != null ? tag.getYearReleased() : ""));
							songBO.setYearReleased((tag != null ? tag.getYearReleased() : ""));

							// BaseService.saveOrUpdate(songBO);
							System.out.println("-------------");
						}
						catch (Exception e)
						{
							System.out.println("Song " + songTitle + " not found");
							e.printStackTrace();
						}

					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
	}

	public static String utf16_asccii(String utfString)
	{
		StringBuilder sb = new StringBuilder(utfString.length());
		String decoded = "";
		for (int i = 0; i < utfString.length(); i++)
		{
			char ch = utfString.charAt(i);
			if (ch <= 0xFF)
			{
				sb.append(ch);
			}
		}

		try
		{
			byte[] ascii = sb.toString().getBytes("ISO-8859-1"); // aka
			// LATIN-1
			decoded = new String(ascii);
			System.out.println("decoded -- " + decoded);
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // aka LATIN-1
		return decoded;
	}
}

/*
 * 1. Song Title : file.getName() 2. AlbumId : mp3.getID3v1Tag().getAlbumTitle()
 * 3. LeadArtist : mp3.getID3v1Tag().getLeadArtist() 4. Composer :
 * mp3.getID3v2Tag().getAuthorComposer() 5. Year Released :
 * mp3.getID3v1Tag().getYearReleased() 6. Genre :
 * mp3.getID3v1Tag().getSongGenre() 7. Lyrics : mp3.getID3v1Tag().getSongLyric()
 * 8. Song Title : mp3.getID3v1Tag().getSongTitle() 9. Track No on Album :
 * mp3.getID3v1Tag().getTrackNumberOnAlbum() 10. Frame count :
 * mp3.getID3v2Tag().getFrameCount() 11. Size : mp3.getID3v2Tag().getSize() 12.
 * BitRate : mp3.getBitRate() 13. Frequency : mp3.getFrequency() 14. ID3v1tag :
 * mp3.getID3v1Tag() 15. ID3v2tag :mp3.getID3v2Tag() 16. Id 17. crated 18.
 * noplayed 19. enum keytag
 */
