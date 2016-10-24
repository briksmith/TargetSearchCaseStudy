package utils;

public class Consts
{
	public static final int SEARCH_ERROR = -1;
	public static final String TEST_FILES_LOCATION = "C:\\Users\\Brian Smith\\git\\TargetSearchCaseStudy\\TargetSearchCaseStudy\\textFiles";
	public static final String FRENCH_ARMED_FORCES = "french_armed_forces.txt";
	public static final String HITCHHIKERS = "hitchhikers.txt";
	public static final String WARP_DRIVE = "warp_drive.txt";
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
