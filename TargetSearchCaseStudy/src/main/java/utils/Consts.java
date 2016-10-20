package utils;

public class Consts
{
	public static enum StrategyEnum
	{
		NotAStrategy(-1), StringSearchStrategy(1), RegularExpressionSearchStrategy(2), PreProcessSearchStrategy(3);

		private int index;

		private StrategyEnum(int inIndex)
		{
			index = inIndex;
		}
		public int getIndex(){
			return index;
		}
	}
}
