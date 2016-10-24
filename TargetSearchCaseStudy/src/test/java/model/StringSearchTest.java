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
public class StringSearchTest
{

	StringSearch systemUnderTest;
	File french;

	@Before
	public void setUp()
	{
		systemUnderTest = new StringSearch();
		french = new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES);
	}

	public Object[] getErrorConditions()
	{
		return new Object[]
		{ new Object[]
				{ "", null }, new Object[]
				{ "", new File("C:\\") }, new Object[]
				{ "", new File("alkshfihnoqadn") } };
	}

	@Test
	@Parameters(method = "getErrorConditions")
	public void checkErrorConditions(String inSearch, File inFile)
	{
		int timesFound = systemUnderTest.timesSearchStringFound(inSearch, inFile);
		assertTrue("This conditions should have returned the error conditon " + Consts.SEARCH_ERROR + " and returned "
				+ timesFound + " instead", timesFound == Consts.SEARCH_ERROR);
	}

	public Object[] testSearchStrings()
	{
		return new Object[]
		{ new Object[]  { "again", 4, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES) }, 
		  new Object[]	{ "against", 3, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES) },
		  new Object[]	{ "sdfd", 0, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES) }};
	}

	@Test
	@Parameters(method = "testSearchStrings")
	public void testFindingInFrenchArmedForces(String searchString, int expected, File fileToSearch)
	{
		int timesFound = systemUnderTest.timesSearchStringFound(searchString, fileToSearch);
		assertTrue("Should have found them " + expected + " times.  Found " + timesFound + " times.",
				timesFound == expected);
	}
}
