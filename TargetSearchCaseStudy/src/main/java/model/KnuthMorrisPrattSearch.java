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
	private int stringToFindIndex = 0;
	private int stringToSearchIndex = 0;
	
	public KnuthMorrisPrattSearch(){
		fileLineGetter = new FileLineGetter();
		suffixTable = new ArrayList<>();
	}
	
	public int timesSearchStringFound(String inStringToFind, File inFile)
	{
		if ( BriansFileUtils.invalidFile(inFile)){
			return Consts.SEARCH_ERROR;
		}
		
		suffixTable = BriansStringUtils.populatePartialMatchTable(inStringToFind);
		
		int timesFoundInFile = 0;
		
		String stringToSearch = fileLineGetter.getLine(inFile);
		stringToFindIndex = 0;
		stringToSearchIndex = 0;
		while ( stringToSearch != null){
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
			stringToSearch = fileLineGetter.getLine(inFile);
			stringToFindIndex = 0;
			stringToSearchIndex = 0;
		}
		
		
		return timesFoundInFile;
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
