package utilsTest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.BriansFileUtils;
import utils.Consts;

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
}
