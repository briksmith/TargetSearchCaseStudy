package utils;

import model.NotAStrategy;
import model.PreprocessAndIndexSearch;
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
			return new PreprocessAndIndexSearch();
		}
		
		return new NotAStrategy();
	}
}
