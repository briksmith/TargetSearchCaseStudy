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
	private boolean foundValidSearchString;
	private boolean foundValidSearchMethod;

	public static void main(String[] args)
	{
		Searcher searcher = new Searcher();
		while (true)
		{
			searcher.getUserInput();
			searcher.processUserInput();
			searcher.printResults();
		}
	}

	private Searcher()
	{
		foundValidSearchString = false;
		foundValidSearchMethod = false;
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
		while (notFoundSearchString())
		{
			System.out.println("Enter string to search for, enclose in \"'s.  Enter Q\\q with no quotes to quit.");
			System.out.println("Note: searches ARE case sensitive");
			readSearchStringFromCommandLine();
			checkForQuiting(searchString);
			checkForMatchingQuotes(searchString);
			searchString = ParseInput.stripBeginingAndEndQuotes(searchString);
		}
	}

	private boolean notFoundSearchString()
	{
		return !foundValidSearchString;
	}

	private void readSearchStringFromCommandLine()
	{
		Console console = System.console();
		searchString = console.readLine();
	}

	private void checkForQuiting(String inString)
	{
		if (inString.equals("Q") || inString.equals("q"))
		{
			System.exit(0);
		}

	}

	private void checkForMatchingQuotes(String inSearchString)
	{
		if (inSearchString.startsWith("\"") && inSearchString.endsWith("\""))
		{
			inSearchString = inSearchString.substring(1, inSearchString.length() - 1);
			foundValidSearchString = true;
		}
		else
		{
			System.out.println("Search string must being and end with \"");
			foundValidSearchString = false;
		}

	}

	private void promptUserForSearchMethod()
	{
		while (notFoundValidSearchMethod())
		{
			printSelectStrategyInstructions();
			setStrategyTypeFromUserInput();
		}

	}

	private boolean notFoundValidSearchMethod()
	{
		return !foundValidSearchMethod;
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
		if (foundValidSearchStrategy(userSearchStrategy))
		{
			System.out.println("Invalid choice of search strategy");
		}
		else
		{
			strategy = userSearchStrategy;
			foundValidSearchMethod = true;
		}
	}

	private boolean foundValidSearchStrategy(SearchStrategy userSearchStrategy)
	{
		Class foundSearchStrategyClass = userSearchStrategy.getClass();
		return foundSearchStrategyClass.equals(NotAStrategy.class);
	}

	private void printResults()
	{
		List<File> filesToSearch = BriansFileUtils.getListOfTestFiles();
		for (File fileToSearch : filesToSearch)
		{
			int timesSearchStringFound = this.strategy.timesSearchStringFound(this.searchString, fileToSearch);
			System.out.println("String " + this.searchString + " found " + timesSearchStringFound + " times in file "
					+ fileToSearch.getName());
		}
		resetSearch();
	}

	private void resetSearch()
	{
		this.foundValidSearchString = false;
		this.foundValidSearchMethod = false;
	}

	private void processUserInput()
	{
		// TODO Auto-generated method stub

	}
}
