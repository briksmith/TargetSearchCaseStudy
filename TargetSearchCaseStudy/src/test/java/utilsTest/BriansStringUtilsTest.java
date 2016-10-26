package utilsTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import utils.BriansStringUtils;

@RunWith(JUnitParamsRunner.class)
public class BriansStringUtilsTest
{

	private final String pattern =  "ABC ABEPAT PATRICA";
	private int[] expectedValues = {-1,0,0,0,0,1,2,0,0,1,0,0,0,1,0,0,0,0};
	private Object[] stringsToSearch()
	{
		return new Object[] {new Object[]  { pattern, expectedValues }};
	}

	@Test
	@Parameters(method ="stringsToSearch")
	public void testFindPrefixMatchAtThisPoint(String inPattern, int[] inExpectedValues)
	{

		List<Integer> actual = BriansStringUtils.populatePartialMatchTable(inPattern);
		assertTrue("found " + actual.size() + " values and should have found " + expectedValues.length + " values.",inExpectedValues.length == actual.size());
		int i = 0;
		for ( int expected : inExpectedValues ){
			assertTrue("index " + i + " expected: " + expected + " actual: " + actual.get(i), expected == actual.get(i));
			i++;
		}
	}
}
