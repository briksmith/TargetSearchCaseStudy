package controller;

import java.io.Console;

import utils.Consts.Strategy;
import utils.ParseInput;

public class Searcher 
{
	private static String searchString = "";
    public static void main( String[] args )
    {
    	Searcher searcher = new Searcher();
        promptUserForSearchString();
        promptUserForSearchMethod();
        searcher.processUserInput();
        printResults();
    }

	private static void promptUserForSearchString()
	{
		System.out.println("Enter string to search for, enclose in \"'s.  Enter Q\\q to quit.");
		Console console = System.console();
		searchString = console.readLine();
		
	}

	private static void promptUserForSearchMethod()
	{
		System.out.println("Select search method by number.  Enter Q\\q to quit.");
		System.out.println("1)  Simple string matching.");
		System.out.println("2)  Regular expression search.");
		System.out.println("3)  Preprocess content and search index.");
		Console console = System.console();
		String searchMethod = console.readLine();
		Strategy selectedStrategy = ParseInput.parseStrategyType(searchMethod);
		
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
