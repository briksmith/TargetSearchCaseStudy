package utilsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.NotAStrategy;
import model.PreprocessAndIndexSearch;
import model.RegularExpressionSearch;
import model.SearchStrategy;
import model.StringSearch;
import utils.ParseInput;

@RunWith(JUnitParamsRunner.class)
public class TestParseStrategyType
{
	
	private static final Object[] inputsForTestParse(){
		return  new Object[] {
			 new Object[]{ "1w", NotAStrategy.class},
			 new Object[]{ "", NotAStrategy.class},
			 new Object[]{" ", NotAStrategy.class},
			 new Object[]{"  ", NotAStrategy.class},
			 new Object[]{"  \n", NotAStrategy.class},
			 new Object[]{"dsfgrt", NotAStrategy.class},
			 new Object[]{"1", StringSearch.class},
			 new Object[]{"2", RegularExpressionSearch.class},
			 new Object[]{"3", PreprocessAndIndexSearch.class},
			 new Object[]{"4", NotAStrategy.class},
			 new Object[]{"5", NotAStrategy.class}
		};
	}
	
	@Test
	@Parameters(method="inputsForTestParse")
	public void testNonParseableString(String input, Class<?> expected)
	{
		SearchStrategy result =ParseInput.parseStrategyType(input);
		assertTrue("Expected " + expected.toString() + " was: " + result.toString(),
				expected.equals(result.getClass()));
	}
	
	private static final Object[] inputsForStripQuotes() {
		return new Object[] {
				new Object[] { "test", "test"},
				new Object[] { "\"test\"", "test"},
				new Object[] { "\"test", "test"},
				new Object[] { "test\"", "test"},
				new Object[] {"\"\"test\"\"\"", "\"test\"\""},
				new Object[] {"test\"test", "test\"test"}
		};
	}
	
	
	@Test
	@Parameters(method="inputsForStripQuotes")
	public void testStripQuotes(String input, String expected){
		
		
		input = ParseInput.stripBeginingAndEndQuotes(input);
		assertTrue("Expected: " + expected +  " Was: " + input, expected.equals(input));
	}
	
}
