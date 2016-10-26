package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utils.BriansFileUtils;
import utils.BriansStringUtils;
import utils.Consts;
import utils.FileLineGetter;

public class KnuthMorrisPrattSearch implements SearchStrategy
{
	private FileLineGetter fileLineGetter;
	private List<Integer> suffixTable;
	private int stringToFindIndex;
	private int stringToSearchIndex;
	private int timesFoundInFile;
	
	public KnuthMorrisPrattSearch(){
		fileLineGetter = new FileLineGetter();
		initMembers();
	}

	private void initMembers()
	{
		suffixTable = new ArrayList<>();
		stringToFindIndex = 0;
		stringToSearchIndex = 0;
		timesFoundInFile = 0;
	}
	
	public int timesSearchStringFound(String inStringToFind, File inFile)
	{
		if ( BriansFileUtils.invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		
		initMembers();
		suffixTable = BriansStringUtils.populatePartialMatchTable(inStringToFind);
		
		String stringToSearch = fileLineGetter.getLine(inFile);
		resetIndecies();
		while ( stringToSearch != null){
			findOccurencesInString(inStringToFind, stringToSearch);
			stringToSearch = fileLineGetter.getLine(inFile);
			resetIndecies();
		}
		
		return timesFoundInFile;
	}

	private void findOccurencesInString(String inStringToFind, String stringToSearch)
	{
		while ( stringToSearchIndex + stringToFindIndex < stringToSearch.length()){
			if ( inStringToFind.charAt(stringToFindIndex) == stringToSearch.charAt(stringToFindIndex + stringToSearchIndex) ) {
				if ( stringToFindIndex == inStringToFind.length() - 1){
					timesFoundInFile++;
					incrementIndices();
				}else{
					stringToFindIndex++;
				}
			}
			else{
				incrementIndices();
			}
			
		}
	}

	private void resetIndecies()
	{
		stringToFindIndex = 0;
		stringToSearchIndex = 0;
	}

	private void incrementIndices()
	{
		if ( suffixTable.get(stringToFindIndex) > -1 ){
			stringToSearchIndex += ( stringToFindIndex - suffixTable.get(stringToFindIndex));
			stringToFindIndex = suffixTable.get(stringToFindIndex);
		}else{
			stringToSearchIndex++;
			stringToFindIndex = 0;
		}
	}

}
