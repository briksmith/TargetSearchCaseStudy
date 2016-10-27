package model;

import java.io.File;

import utils.Consts;

public interface SearchStrategy
{
	public int timesSearchStringFound(String inSearchString, File inFile);
	
	public default boolean continueSearch(String stringToSearch)
	{
		return stringToSearch != null && stringToSearch != Consts.FILE_READ_ERROR;
	}
}
