package controller;

import java.io.Console;

import model.NotAStrategy;
import model.SearchStrategy;
import utils.ParseInput;

public class Searcher
{
	private String searchString;
	private SearchStrategy strategy;
	boolean promptUser;

	public static void main(String[] args)
	{
		Searcher searcher = new Searcher();
		searcher.getUserInput();
		searcher.processUserInput();
		printResults();
	}

	private Searcher()
	{
		promptUser = true;
		searchString = "";
		strategy = new NotAStrategy();
	}

	private void getUserInput()
	{
		while (promptUser)
		{
			this.promptUserForSearchString();
			this.promptUserForSearchMethod();
		}
	}

	private void promptUserForSearchString()
	{
		System.out.println("Enter string to search for, enclose in \"'s.  Enter Q\\q to quit.");
		Console console = System.console();
		searchString = console.readLine();
		checkForQuiting(searchString);
	}

	private void promptUserForSearchMethod()
	{
		if (promptUser)
		{
			printSelectStrategyInstructions();
			setStrategyTypeFromUserInput();
		}

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
		strategy = ParseInput.parseStrategyType(searchMethod);
	}

	private void checkForQuiting(String searchMethod)
	{
		if (searchMethod.equals("Q") || searchMethod.equals("q"))
		{
			promptUser = false;
		}

	}

	private static void printResults()
	{
		// TODO Auto-generated method stub

	}

	private void processUserInput()
	{
		// TODO Auto-generated method stub

	}
}
