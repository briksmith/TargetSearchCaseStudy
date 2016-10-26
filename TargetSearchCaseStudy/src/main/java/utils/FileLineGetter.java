package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLineGetter
{
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public FileLineGetter()
	{
		fileReader = null;
		bufferedReader = null;
	}

	public String getLine(File inFile)
	{
		initMembers(inFile);
		if ( bufferedReader != null ) {
			try
			{
				return bufferedReader.readLine();
			}
			catch (IOException e)
			{
				BriansFileUtils.handleException(e);
				return Consts.FILE_READ_ERROR;
			}
		}
		return "";
	}

	private void initMembers(File inFile)
	{
		try
		{
			if (fileReader == null)
			{
				fileReader = new FileReader(inFile);
			}
			if (bufferedReader == null)
			{
				bufferedReader = new BufferedReader(fileReader);
			}	
		}
		catch (FileNotFoundException e)
		{
			BriansFileUtils.handleException(e);
		}
	}
}
