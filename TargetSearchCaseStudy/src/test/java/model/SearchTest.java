package model;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import utils.Consts;

@RunWith(JUnitParamsRunner.class)
public class SearchTest
{

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
				new Object[] { "", new File("alkshfihnoqadn"), new RegularExpressionSearch()} 
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
				new Object[] { "again", 4, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "against", 3, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new StringSearch() },
				new Object[] { "again", 4, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() },
				new Object[] { "against", 3, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() },
				new Object[] { "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES),
						new RegularExpressionSearch() }};
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
}
