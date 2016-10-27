package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import utils.BriansFileUtils;
import utils.Consts;

public class StringSearch implements SearchStrategy
{

	static Logger log = Logger.getLogger(StringSearch.class.getName());
	
	public int timesSearchStringFound(String inSearchString, File inFile) 
	{
		if ( BriansFileUtils.invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		int timesFoundInFile = 0;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try{
		reader = new FileReader(inFile);
		bufferedReader = new BufferedReader(reader);
		String readString = bufferedReader.readLine();
		
		while (continueSearch(readString) ){
			int timesFoundInSubstring = StringUtils.countMatches(readString, inSearchString);
			timesFoundInFile += timesFoundInSubstring;
			readString = bufferedReader.readLine();
		}
		}
		catch(Exception e){
			handleException(e);
		}
		finally{
			try
			{
				bufferedReader.close();
				reader.close();
			}
			catch (Exception e)
			{
				handleException(e);
			}
			
		}
		return timesFoundInFile;
	}

	private void handleException(Exception e)
	{
		System.out.println(e.getMessage());
		e.printStackTrace();
	}

}
