package com.c2s.music.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.media.Format;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;

public class MoviesDuplicate
{

	/**
	 * @param args
	 * @throws TagException
	 */
	public static Map<String, Integer>		movies			= null;
	public static Map<String, List<String>>	duplicateMovies	= null;

	public static void main(String[] args) throws TagException
	{

		movies = new HashMap<String, Integer>();
		duplicateMovies = new HashMap<String, List<String>>();

		// expandZip();
		// copyFromTitle();
		// listSongs();
		 listMovies();
		 displayDuplicates();
		// displayAllMoviesSorted();
		// listUncommonInFandH();

		//renameMoviesFromNewToFav();

	}

	private static void displayAllMoviesSorted()
	{
		String filePath = "F:\\Songs\\Hindi\\New Movies";
		Map<String, String> fMovies = new HashMap<String, String>();
		File root = new File(filePath);
		listMoviesAndFilePath(root, fMovies);
		filePath = "F:\\Songs\\Hindi\\Movies";
		root = new File(filePath);
		listMoviesAndFilePath(root, fMovies);
		displayList(fMovies);
	}

	private static void displayList(Map<String, String> fMovies)
	{
		// TODO Auto-generated method stub
		Set<String> movieNames = fMovies.keySet();
		List<String> movieList = new ArrayList<String>();
		movieList.addAll(movieNames);
		Collections.sort(movieList);
		for (String fileName : movieList)
		{
			System.out.println(fileName);
		}
	}

	private static void listMoviesAndFilePath(File directory, Map<String, String> fMovies)
	{
		// TODO Auto-generated method stub
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			String fileName = file.getName();
			if (file.isDirectory())
			{
				fileName = fileName.replace("Hindi New -- ", "");
				fileName = fileName.replace("Hindi Movies -- ", "");
				fMovies.put(fileName, file.getAbsolutePath());
				if (file.isDirectory())
					listMoviesAndFilePath(file, fMovies);
			}
		}
	}

	private static void listUncommonInFandH()
	{
		Map<String, String> fMovies = new HashMap<String, String>();
		Map<String, String> hMovies = new HashMap<String, String>();
		String filePath = "F:\\Songs\\Hindi\\";
		File root = new File(filePath);
		fillMapForFandH(root, fMovies);
		filePath = "H:\\Songs\\Hindi\\Movies";
		root = new File(filePath);
		fillMapForFandH(root, hMovies);

		compareUncommon(fMovies, hMovies);
	}

	private static void compareUncommon(Map<String, String> fMovies, Map<String, String> hMovies)
	{
		Set<String> keySet = hMovies.keySet();
		System.out.println("----------------  H   --------------------------------");
		for (String fileName : keySet)
		{
			if (!fMovies.containsKey(fileName))
			{
				System.out.println(hMovies.get(fileName));
			}
		}
	}

	private static void fillMapForFandH(File directory, Map<String, String> movies)
	{
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			String fileName = file.getName();
			if (file.isDirectory())
			{
				fileName = fileName.replace("Hindi New -- ", "");
				fileName = fileName.replace("Hindi Movies -- ", "");
				movies.put(fileName, file.getAbsolutePath());
				if (file.isDirectory())
					fillMapForFandH(file, movies);
			}
		}
	}

	private static void renameMoviesFromNewToFav()
	{
		String filePath = "F:\\Songs\\Hindi\\New Movies";
		File root = new File(filePath);
		File[] listFiles = root.listFiles();
		for (File file : listFiles)
		{
			FileCopy1 copy1 = new FileCopy1();
			try
			{
				System.out.println("----------------------------------------------------------------------");
				System.out.println(file.getAbsolutePath() + "  --  " + file.getAbsolutePath().replace("New", "FAV"));
				if (file.isDirectory())
				{
					File file2 = new File(file.getAbsolutePath().replace("New", "Fav"));
					file2.mkdir();
					copyAllSongs(file, file2);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	private static void copyAllSongs(File srcFolder, File destFolder)
	{
		File[] listFiles = srcFolder.listFiles();
		for (File file : listFiles)
		{
			FileCopy1 copy1 = new FileCopy1();
			try
			{
				System.out.println("Copying -- " + file.getAbsolutePath() + " -- " + destFolder + "\\" + file.getName());
				copy1.copy(file.getAbsolutePath(), destFolder + "\\" + file.getName());
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void displayDuplicates()
	{
		Set<String> movieNames = movies.keySet();
		List<String> movieList = new ArrayList<String>();
		movieList.addAll(movieNames);
		Collections.sort(movieList);
		for (String movieName : movieList)
		{
			int count = movies.get(movieName);
			if (count > 1)
			{

				System.out.println("----------------------------------------------------------------------");
				System.out.println(movieName + " -- " + count);
				List<String> pathLists = duplicateMovies.get(movieName);
				for (String path : pathLists)
				{
					System.out.println(path);
				}

			}
		}
	}

	private static void listMovies()
	{

		String filePath = "F:\\Songs\\Hindi\\";
		File root = new File(filePath);
		if (root.isDirectory())
			fillMap(root);

	}

	private static void fillMap(File directory)
	{
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			String fileName = file.getName();
			if (file.isDirectory())
			{
				fileName = fileName.replace("Hindi Fav -- ", "");
				fileName = fileName.replace("Hindi Movies -- ", "");
				Integer temp;
				temp = movies.get(fileName);
				// System.out.println("Read " + fileName + " " + temp);
				if (temp == null)
					movies.put(fileName, 1);
				else
					movies.put(fileName, ++temp);

				updateDuplicateMoviesMap(file);

				if (file.isDirectory())
					fillMap(file);
			}
		}
	}

	private static void updateDuplicateMoviesMap(File file)
	{

		String fileName = file.getName();
		fileName = fileName.replace("Hindi Fav -- ", "");
		fileName = fileName.replace("Hindi Movies -- ", "");

		List<String> list = duplicateMovies.get(fileName);
		if (list == null)
		{
			list = new ArrayList<String>();
		}
		list.add(file.getAbsolutePath());
		duplicateMovies.put(fileName, list);
	}

	private static void renameDirectoryAndTitles(File directory) throws TagException
	{

		File parentDirectory = getFileName(directory);
		System.out.println(parentDirectory.getName().trim());
		int renameSongs;
		// System.out.println(directory.getParent()+"\\"+directoryName.trim());y
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			if (file.getName().contains("Zip") || file.getName().contains("zip"))
				continue;
			{
				if (file.isDirectory())
					renameDirectoryAndTitles(file);
				else
					try
					{
						renameSongs = renameSongsAsTitle(file, parentDirectory);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		directory.delete();

	}

	private static int renameSongsAsTitle(File songs, File parentFolder) throws IOException, TagException
	{
		// TODO Auto-generated method stub
		if (!parentFolder.exists())
			parentFolder.mkdir();
		if (songs.getName().contains(".mp3"))
		{
			System.out.println(songs.getAbsolutePath() + " -- " + parentFolder.getAbsolutePath() + "\\" + getSongTitle(songs));
			/*
			 * try { new
			 * File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)).createNewFile(); }
			 * catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			// songs.renameTo(new
			// File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)));
			// FileCopy copy = new FileCopy(songs,new
			// File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)));
			// copy.Copy();
			FileCopy1 copy1 = new FileCopy1();
			copy1.copy(songs.getAbsolutePath(), parentFolder.getAbsolutePath() + "//" + getSongTitle(songs));
			songs.delete();
		}
		return -1;
	}

	private static String getSongTitle(File song) throws IOException, TagException
	{
		// TODO Auto-generated method stub
		File directory = new File("C:/Users/user/Desktop/songs/Sing is king");
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]
		{ input1, input2 }, new Format[]
		{ output }, PlugInManager.CODEC);
		MP3File mp3 = new MP3File(song.getAbsolutePath());
		AbstractID3v2 iD3v2Tag = mp3.getID3v2Tag();
		ID3v1 tag = mp3.getID3v1Tag();
		String songTitle = (tag != null ? tag.getSongTitle().replace("www.Songs.PK", "") : "");
		songTitle = songTitle.replace("- [Songs.PK]", "");
		songTitle = songTitle.replace("- []", "");
		songTitle = songTitle.replace(" - ", " ").trim();
		songTitle = songTitle.replace("www.Songs.mp3", "");
		songTitle = songTitle.replace("www.Songs", "");
		songTitle = songTitle.replace("www.So", "");
		songTitle = songTitle.replace("Songspk.name", "");
		songTitle = songTitle.replace("Songspk.", "");

		// System.out.println(songTitle + ".mp3");
		return songTitle + ".mp3";
	}

	private static void listSongs()
	{
		File directory = new File("C:/Users/user/Desktop/songs/Sing is king");
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]
		{ input1, input2 }, new Format[]
		{ output }, PlugInManager.CODEC);
		int count = 0;
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

						// System.out.println("Composer:" + (iD3v2Tag != null ?
						// iD3v2Tag.getAuthorComposer() : ""));
						try
						{
							String authorComposer = (iD3v2Tag != null ? iD3v2Tag.getAuthorComposer() : "");
							// System.out.println("Composer:" + authorComposer);
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

						// System.out.println("Id3v1Tag :" + (tag != null ?
						// tag.toString() : ""));

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

						// System.out.println("Year:" + (tag != null ?
						// tag.getYearReleased() : ""));

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

	private static void expandZip()
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		String filePath = "C:\\Users\\user\\Desktop\\songs\\";
		File root = new File(filePath);
		if (root.isDirectory())
			renameDirectory(root);
		else
			try
			{
				renameSongs(root, getFileName(root));
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	private static int renameSongs(File songs, File parentFolder) throws IOException
	{
		if (!parentFolder.exists())
			parentFolder.mkdir();
		System.out.println(songs.getAbsolutePath() + " -- " + parentFolder.getAbsolutePath() + "\\" + getSongName(songs));
		/*
		 * try { new
		 * File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)).createNewFile(); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// songs.renameTo(new
		// File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)));
		// FileCopy copy = new FileCopy(songs,new
		// File(parentFolder.getAbsolutePath()+"//"+getSongName(songs)));
		// copy.Copy();
		FileCopy1 copy1 = new FileCopy1();
		copy1.copy(songs.getAbsolutePath(), parentFolder.getAbsolutePath() + "//" + getSongName(songs));
		songs.delete();
		return 1;
	}

	private static String getSongName(File song)
	{
		String songName = song.getName();
		String[] split = songName.split("-");
		for (String string : split)
		{
			songName = string.trim();
		}
		return songName;
	}

	private static File getFileName(File directory)
	{
		String directoryName = directory.getName();
		directoryName = directoryName.replace("2013", "");
		directoryName = directoryName.replace("2014", "");
		directoryName = directoryName.replace("-", " ");
		directoryName = directoryName.replace("(Songs.PK)", "");
		directoryName = directoryName.replace("320Kbps", "");
		return new File(directory.getParent() + "\\" + "Hindi Movies -- " + directoryName.trim());
	}

	private static void renameDirectory(File directory)
	{
		File parentDirectory = getFileName(directory);
		System.out.println(parentDirectory.getName().trim());
		int renameSongs;
		// System.out.println(directory.getParent()+"\\"+directoryName.trim());y
		File[] listFiles = directory.listFiles();
		for (File file : listFiles)
		{
			if (file.getName().contains("Zip") || file.getName().contains("zip"))
				continue;
			{
				if (file.isDirectory())
					renameDirectory(file);
				else
					try
					{
						renameSongs = renameSongs(file, parentDirectory);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		directory.delete();
	}

}
