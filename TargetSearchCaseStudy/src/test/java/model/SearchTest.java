package model;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import utils.Consts;

@RunWith(JUnitParamsRunner.class)
public class SearchTest
{

	private static final int TIMES_TO_FIND_RADIO_HITCHHIKERS = 2;

	private static final int TIMES_TO_FIND_AGAINST_FRENCH = 3;

	private static final int TIMES_TO_FIND_AGAIN_FRENCH = 4;

	private static final int numTimeTest = 250000;
	
	private static Logger log = Logger.getLogger(SearchTest.class);

	SearchStrategy systemUnderTest;

	File french;

	@Before
	public void setUp()
	{
		french = new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES);
	}

	public Object[] getErrorConditions()
	{
		return new Object[] { 
				new Object[] { "", null, new StringSearch() },
				new Object[] { "", new File("C:\\"), new StringSearch() },
				new Object[] { "", new File("alkshfihnoqadn"), new StringSearch()},
				new Object[] { "", null, new RegularExpressionSearch() },
				new Object[] { "", new File("C:\\"), new RegularExpressionSearch() },
				new Object[] { "", new File("alkshfihnoqadn"), new RegularExpressionSearch() },
				new Object[] { "", null, new KnuthMorrisPrattSearch() },
				new Object[] { "", new File("C:\\"), new KnuthMorrisPrattSearch() },
				new Object[] { "", new File("alkshfihnoqadn"), new KnuthMorrisPrattSearch() },
				new Object[] { "", null, new ProcessAndIndexSearch() },
				new Object[] { "", new File("C:\\"), new ProcessAndIndexSearch() },
				new Object[] { "", new File("alkshfihnoqadn"), new ProcessAndIndexSearch()		
				}		
				
		};
	}

	@Test
	@Parameters(method = "getErrorConditions")
	public void checkErrorConditions(String inSearch, File inFile, SearchStrategy inStrategy)
	{
		systemUnderTest = inStrategy;
		int timesFound = systemUnderTest.timesSearchStringFound(inSearch, inFile);
		assertTrue("This conditions should have returned the error conditon " + Consts.SEARCH_ERROR + " and returned "
				+ timesFound + " instead", timesFound == Consts.SEARCH_ERROR);
	}

	public Object[] testSearchStrings()
	{
		return new Object[] {
				new Object[] { "again", TIMES_TO_FIND_AGAIN_FRENCH, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "against", TIMES_TO_FIND_AGAINST_FRENCH, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "radio", TIMES_TO_FIND_RADIO_HITCHHIKERS, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.HITCHHIKERS),
						new StringSearch() },
				new Object[] { "again", 4, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() },
				new Object[] { "against", 3, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() },
				new Object[] { "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() },
				new Object[] { "radio", TIMES_TO_FIND_RADIO_HITCHHIKERS, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.HITCHHIKERS),
						new RegularExpressionSearch() },
				new Object[] { "again", TIMES_TO_FIND_AGAIN_FRENCH, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new KnuthMorrisPrattSearch() },
				new Object[] { "against", TIMES_TO_FIND_AGAINST_FRENCH, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new KnuthMorrisPrattSearch() },
				new Object[] { "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new KnuthMorrisPrattSearch()},
						new Object[] { "radio", TIMES_TO_FIND_RADIO_HITCHHIKERS, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.HITCHHIKERS),
								new KnuthMorrisPrattSearch() }
		
		};
	}

	@Test
	@Parameters(method = "testSearchStrings")
	public void testFindingInFrenchArmedForces(String searchString, int expected, File fileToSearch,
			SearchStrategy inSearchStrategy)
	{
		systemUnderTest = inSearchStrategy;
		int timesFound = systemUnderTest.timesSearchStringFound(searchString, fileToSearch);
		assertTrue("Should have found them " + expected + " times.  Found " + timesFound + " times.",
				timesFound == expected);
	}
	
	private Object[] randomSearchTerms(){
		return new Object[] {
				new Object[] { "Roman", new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES)},
				new Object[] { "ion", new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES)}
		};
	}
	
	
	@Test
	@Parameters(method ="randomSearchTerms")
	public void timeMethods(String inSearchTerm, File fileToSearch)
	{
		SearchStrategy stringSearch = new StringSearch();
		SearchStrategy regularExpressionSearch = new RegularExpressionSearch();
		SearchStrategy knuthMorrisPrattSearch = new KnuthMorrisPrattSearch();
		int stringFound = 0;
		int regularExpressionFound = 0;
		int knuthMorrisFound = 0;
		
		stringFound = searchStringWithStrategy(inSearchTerm, fileToSearch, stringSearch);
		regularExpressionFound = searchStringWithStrategy(inSearchTerm, fileToSearch, regularExpressionSearch);
		knuthMorrisFound = searchStringWithStrategy(inSearchTerm, fileToSearch, knuthMorrisPrattSearch);
		
		assertTrue("stringSearch found: " + stringFound + " regular expression found: " + regularExpressionFound, stringFound == regularExpressionFound);
		assertTrue("stringSearch found: " + stringFound + " knuthMorris found: " + knuthMorrisFound, stringFound == knuthMorrisFound);
		assertTrue("regular expression found: " + regularExpressionFound + " knuthMorris found: " + knuthMorrisFound, regularExpressionFound == knuthMorrisFound);
	}

	private int searchStringWithStrategy(String inSearchTerm, File fileToSearch, SearchStrategy inStrategy)
	{
		int timesFound = 0;
		long timeBefore =new Date().getTime();
		for ( int i = 0; i < numTimeTest; i++){
			timesFound = inStrategy.timesSearchStringFound(inSearchTerm, fileToSearch);
		}
		long timeAfter = new Date().getTime();
		long millis = timeAfter - timeBefore;
		System.out.println(inStrategy.getClass().getName() + " took " + millis + " milliseconds");
		return timesFound;
	}
	
}
