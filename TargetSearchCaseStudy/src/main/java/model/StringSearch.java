package model;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import utils.BriansFileUtils;
import utils.Consts;
import utils.FileLineGetter;

public class StringSearch implements SearchStrategy
{

	FileLineGetter fileLineGetter;
	static Logger log = Logger.getLogger(StringSearch.class.getName());
	
	public StringSearch() {
		fileLineGetter = new FileLineGetter();
	}
	
	public int timesSearchStringFound(String inSearchString, File inFile) 
	{
		if ( BriansFileUtils.invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		int timesFoundInFile = 0;
		
		fileLineGetter.initMembers(inFile);
		String readString = fileLineGetter.getLine(inFile);
		
		while (continueSearch(readString) ){
			int timesFoundInSubstring = StringUtils.countMatches(readString, inSearchString);
			timesFoundInFile += timesFoundInSubstring;
			readString = fileLineGetter.getLine(inFile);
		}
		return timesFoundInFile;
	}

}
