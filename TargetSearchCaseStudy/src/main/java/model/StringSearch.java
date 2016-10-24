package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import utils.Consts;

public class StringSearch implements SearchStrategy
{

	public int timesSearchStringFound(String inSearchString, File inFile) 
	{
		if ( invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		int timesFoundInFile = 0;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try{
		reader = new FileReader(inFile);
		bufferedReader = new BufferedReader(reader);
		String readString = bufferedReader.readLine();
		
		while (readString != null ){
			int timesFoundInSubstring = StringUtils.countMatches(readString, inSearchString);
			timesFoundInFile += timesFoundInSubstring;
			readString = bufferedReader.readLine();
		}
		}
		catch(IOException e){
			handleException(e);
		}
		finally{
			try
			{
				bufferedReader.close();
				reader.close();
			}
			catch (IOException e)
			{
				handleException(e);
			}
			
		}
		return timesFoundInFile;
	}

	private void handleException(IOException e)
	{
		System.out.println(e.getMessage());
		e.printStackTrace();
	}

	private boolean invalidFile(File inFile)
	{
		if ( inFile == null){
			return true;
		}
		if ( !inFile.exists()){
			return true;
		}
		if ( inFile.isDirectory()){
			return true;
		}
		return false;
	}

}
