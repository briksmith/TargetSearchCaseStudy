package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
		if ( bufferedReader != null ) {
			try
			{
				return bufferedReader.readLine();
			}
			catch (Exception e)
			{
				BriansFileUtils.handleException(e);
				return Consts.FILE_READ_ERROR;
			}
		}
		return Consts.FILE_READ_ERROR;
	}

	public void initMembers(File inFile)
	{
		try
		{
			fileReader = new FileReader(inFile);
			bufferedReader = new BufferedReader(fileReader);
		}
		catch (FileNotFoundException e)
		{
			BriansFileUtils.handleException(e);
		}
	}
}
