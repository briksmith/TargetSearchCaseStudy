package utils;

import java.util.ArrayList;
import java.util.List;

public class BriansStringUtils
{

	private static List<Integer> table;
	public static List<Integer> populatePartialMatchTable(String inString){
		table = new ArrayList<>();
		for ( int i = 0 ; i < inString.length(); i++){
			table.add(findPrefixMatchAtThisPoint(inString, i));
		}
		
		return table;
	}
	private static int findPrefixMatchAtThisPoint(String inString, int index){
		
		if ( index == 0) {
			return -1;
		}
		if ( index == 1){
			return 0;
		}
		String prefixOfCurrentIndex = getPrefixOfCurrentIndex(inString, index);
		String suffix = getSuffixOfString(prefixOfCurrentIndex, index);
		int numMatched = 0;
		int suffixLength = suffix.length();
		while( numMatched < suffixLength && ( inString.charAt(0 + numMatched) == suffix.charAt(0 + numMatched) ) ){
			numMatched++;
		}
		if ( numMatched == suffixLength){
			return numMatched;
		}
		else{
			return 0;
		}
	}

	private static String getSuffixOfString(String prefixOfCurrentIndex, int inIndex)
	{
		int lastIndex = prefixOfCurrentIndex.length();
		int previousMatch = table.get(inIndex - 1);
		return prefixOfCurrentIndex.substring(lastIndex - ( previousMatch + 1)  , lastIndex);
	}

	private static String getPrefixOfCurrentIndex(String inString, int index)
	{
		return inString.substring(0, index);
	}
	
}
