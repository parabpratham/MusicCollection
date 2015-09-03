package com.c2s.music.utils;

import java.io.File;

import org.farng.mp3.TagException;

public class CopyOfSongsPk
{

	/**
	 * @param args
	 * @throws TagException
	 */
	public static void main(String[] args) throws TagException
	{
		String filePath = "H:\\Songs\\Hindi\\Movies";
		File root = new File(filePath);
		for (File file : root.listFiles())
		{
			String name = file.getName();
			name = "Hindi New -- " + name;
			if (file.isDirectory())
			{
				file.renameTo(new File(filePath+"\\" + name));
			}
		}
	}
}