package model;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.BriansFileUtils;
import utils.Consts;
import utils.FileLineGetter;

public class RegularExpressionSearch implements SearchStrategy
{
	
	private FileLineGetter fileLineGetter;
	
	public RegularExpressionSearch(){
		fileLineGetter = new FileLineGetter();
	}
	
	public int timesSearchStringFound(String inSearchString, File inFile)
	{
		if ( BriansFileUtils.invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		int timesFoundInFile = 0;
		Pattern searchPattern = Pattern.compile(inSearchString);
		
		String stringToSearch = fileLineGetter.getLine(inFile);
		while ( stringToSearch != null){
			Matcher matcher = searchPattern.matcher(stringToSearch);
			stringToSearch = fileLineGetter.getLine(inFile);
			while (matcher.find()){
				timesFoundInFile++;
			}
		}
		
		return timesFoundInFile;
	}

}
