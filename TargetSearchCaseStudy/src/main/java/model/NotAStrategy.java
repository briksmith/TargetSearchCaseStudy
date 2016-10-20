package model;

import java.io.File;

public class NotAStrategy implements SearchStrategy
{

	@Override
	public int timesSearchStringFound(String inSearchString, File inFile)
	{
		
		return 0;
	}

}
