package utils;

import model.NotAStrategy;
import model.KnuthMorrisPrattSearch;
import model.RegularExpressionSearch;
import model.SearchStrategy;
import model.StringSearch;
import utils.Consts.StrategyEnum;

public class ParseInput
{
	private ParseInput()
	{
	}

	public static SearchStrategy parseStrategyType(String inStrategy)
	{

		int strategy;
		try
		{
			strategy = Integer.parseInt(inStrategy);
		}
		catch (NumberFormatException e)
		{
			return new NotAStrategy();
		}
		return getStrategyForInt(strategy);
	}

	private static SearchStrategy getStrategyForInt(int strategy)
	{
		if ( strategy == StrategyEnum.StringSearchStrategy.getIndex()) {
			return new StringSearch();
		} else if ( strategy == StrategyEnum.RegularExpressionSearchStrategy.getIndex()){
			return new RegularExpressionSearch();
		} else if ( strategy == StrategyEnum.PreProcessSearchStrategy.getIndex()){
			return new KnuthMorrisPrattSearch();
		}
		
		return new NotAStrategy();
	}
	
	public static String stripBeginingAndEndQuotes(String inString){
		inString = removeLeadingQuote(inString);
		inString = removeTrailingQuote(inString);
		return inString;
	}

	private static String removeLeadingQuote(String inString)
	{
		if(inString.charAt(0) == '\"'){
			inString = inString.substring(1);
		}
		return inString;
	}

	private static String removeTrailingQuote(String inString)
	{
		int inStringLength = inString.length();
		if (inString.charAt(inStringLength-1) == '\"'){
			inString = inString.substring(0, inStringLength  - 1);
		}
		return inString;
	}
}
