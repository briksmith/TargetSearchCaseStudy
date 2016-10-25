package model;

import java.io.File;

public interface SearchStrategy
{
	public int timesSearchStringFound(String inSearchString, File inFile);
	
}
