package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class BriansFileUtils
{
	public static List<File> getListOfTestFiles()
	{
		File testFilesDirectory = new File(Consts.TEST_FILES_LOCATION);
		List<File> listOfFiles = new ArrayList<>();
		if ( testFilesDirectory.isDirectory() ) {
			for ( File testFile : testFilesDirectory.listFiles() ){
				ifTextFileAddToList(listOfFiles, testFile);
			}
		}
		return listOfFiles;
	}

	private static void ifTextFileAddToList(List<File> listOfFiles, File testFile)
	{
		String fileName = testFile.getName();
		String extension = FilenameUtils.getExtension(fileName);
		if ( extension.equals("txt") ){
			listOfFiles.add(testFile);
		}
	}
}
