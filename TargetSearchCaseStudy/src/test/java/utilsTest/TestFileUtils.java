package utilsTest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import utils.BriansFileUtils;
import utils.Consts;

@RunWith(JUnitParamsRunner.class)
public class TestFileUtils
{

	private List<File> expectedFiles;

	@Before
	public void setUp()
	{
		expectedFiles = new ArrayList<>();
		expectedFiles.add(new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES));
		expectedFiles.add(new File(
				"C:\\Users\\Brian Smith\\git\\TargetSearchCaseStudy\\TargetSearchCaseStudy\\textFiles\\hitchhikers.txt"));
		expectedFiles.add(new File(
				"C:\\Users\\Brian Smith\\git\\TargetSearchCaseStudy\\TargetSearchCaseStudy\\textFiles\\warp_drive.txt"));
	}

	@Test
	public void testGetTestFiles()
	{
		List<File> actual = BriansFileUtils.getListOfTestFiles();
		assertTrue("Should have found " + expectedFiles.size() + " but found " + actual.size(),
				expectedFiles.size() == actual.size());
		for (File expectedFile : expectedFiles)
		{
			assertTrue("Should have contained: " + expectedFile.toString() + " and did not",
					actual.contains(expectedFile));
		}
	}

	public Object[] getErrorConditions()
	{
		return new Object[] { new Object[] { true, null }, 
				new Object[] { true, new File("C:\\") },
				new Object[] { true, new File("alkshfihnoqadn")},
				new Object[] { false, new File(Consts.TEST_FILES_LOCATION + "\\" + Consts.FRENCH_ARMED_FORCES) } };
	}

	@Test
	@Parameters(method = "getErrorConditions")
	public void checkErrorConditions(boolean expected, File inFile)
	{
		boolean isValidFile = BriansFileUtils.invalidFile(inFile);
		assertTrue("This conditions should have returned " + expected + " and returned " + isValidFile + " instead",
				expected == isValidFile);
	}

}
