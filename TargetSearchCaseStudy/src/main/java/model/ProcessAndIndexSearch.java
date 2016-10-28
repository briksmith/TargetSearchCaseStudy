package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import utils.BriansFileUtils;
import utils.Consts;
import utils.FileLineGetter;

public class ProcessAndIndexSearch implements SearchStrategy
{

	Map<String, Integer> foundWords;

	FileLineGetter fileLineGetter;

	public ProcessAndIndexSearch()
	{
		foundWords = new HashMap<>();
		fileLineGetter = new FileLineGetter();
	}

	@Override
	public int timesSearchStringFound(String inSearchString, File inFile)
	{
		if (BriansFileUtils.invalidFile(inFile))
		{
			return Consts.SEARCH_ERROR;
		}

		foundWords = new HashMap<>();

		String stringToSearch = fileLineGetter.getLine(inFile);

		while (stringToSearch != null)
		{
			String[] tokens = stringToSearch.split(" ");
			for (String s : tokens)
			{
				int timesFound = foundWords.get(s) != null ? (Integer) foundWords.get(s) + 1 : 1;
				foundWords.put(s, timesFound);
			}
			stringToSearch = fileLineGetter.getLine(inFile);
		}
		Integer timesFound = foundWords.get(inSearchString);
		if (timesFound == null)
		{
			return 0;
		}
		else
		{
			return timesFound;
		}
	}

}
