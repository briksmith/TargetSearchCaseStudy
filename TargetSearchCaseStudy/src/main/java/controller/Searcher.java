package controller;

import java.io.Console;
import java.io.File;
import java.util.List;


import model.NotAStrategy;
import model.SearchStrategy;
import utils.BriansFileUtils;
import utils.ParseInput;

public class Searcher
{
	private String searchString;
	private SearchStrategy strategy;
	boolean promptUser;
	private boolean foundValidSearchString;
	private boolean foundValidSearchMethod;

	public static void main(String[] args)
	{
		Searcher searcher = new Searcher();
		while (searcher.promptUser)
		{
		searcher.getUserInput();
		searcher.processUserInput();
		printResults();
		}
	}

	private Searcher()
	{
		foundValidSearchString = false;
		foundValidSearchMethod = false;
		promptUser = true;
		searchString = "";
		strategy = new NotAStrategy();
	}

	private void getUserInput()
	{
		this.promptUserForSearchString();
		this.promptUserForSearchMethod();
	}

	private void promptUserForSearchString()
	{
		while(promptForSearchString()){
		System.out.println("Enter string to search for, enclose in \"'s.  Enter Q\\q with no quotes to quit.");
		readSearchStringFromCommandLine();
		checkForQuiting(searchString);
		if ( promptUser){
			checkForMatchingQuotes(searchString);
		}
		}
	}

	private void readSearchStringFromCommandLine()
	{
		Console console = System.console();
		searchString = console.readLine();
	}

	private boolean promptForSearchString()
	{
		return promptUser && !foundValidSearchString;
	}

	private void checkForMatchingQuotes(String inSearchString)
	{
		if ( inSearchString.startsWith("\"") && inSearchString.endsWith("\"") ){
			inSearchString = inSearchString.substring(1, inSearchString.length() - 1);
			foundValidSearchString = true;
		}
		else{
			System.out.println("Search string must being and end with \"");
			foundValidSearchString = false;
		}
		
	}

	private void promptUserForSearchMethod()
	{
		while (notGivenSearchMethodAndNotQuit())
		{
			printSelectStrategyInstructions();
			setStrategyTypeFromUserInput();
		}

	}

	private boolean notGivenSearchMethodAndNotQuit()
	{
		return promptUser && !foundValidSearchMethod;
	}

	private void printSelectStrategyInstructions()
	{
		System.out.println("Select search method by number.  Enter Q\\q to quit.");
		System.out.println("1)  Simple string matching.");
		System.out.println("2)  Regular expression search.");
		System.out.println("3)  Preprocess content and search index.");
	}

	private void setStrategyTypeFromUserInput()
	{
		Console console = System.console();
		String searchMethod = console.readLine();
		checkForQuiting(searchMethod);
		setStrategyIfValidStrategyFound(searchMethod);
	}

	private void setStrategyIfValidStrategyFound(String searchMethod)
	{
		SearchStrategy userSearchStrategy = ParseInput.parseStrategyType(searchMethod);
		if( userSearchStrategy.getClass().equals(NotAStrategy.class)) {
			System.out.println("Invalid choice of search strategy");
		}
		else{
			strategy = userSearchStrategy;
			foundValidSearchMethod = true;
		}
	}

	private void checkForQuiting(String inString)
	{
		if (inString.equals("Q") || inString.equals("q"))
		{
			System.exit(0);
		}

	}

	private static void printResults()
	{
		List<File> listOfFiles = BriansFileUtils.getListOfTestFiles();

	}

	

	private void processUserInput()
	{
		// TODO Auto-generated method stub

	}
}
