package syntax_check;

import java.util.Scanner;

import org.junit.* ;
import static org.junit.Assert.* ;

public class BrainVerifierTest
{
	final static String validInput1 = new String( "Flip 5 24 3\nFlip 5 24 4\nFlip 5 24 4 ;hello" );
	final static String invalidInput1 = new String( "Flip 5 24 3\nhello\nFlip 5 24 4 ;hello" );

	@Test
	public void test_checkValid1()
	{
		Scanner input = new Scanner( validInput1 );
		BrainVerifier bv = new BrainVerifier( input );
		System.out.println( "Test if BrainVerifier.check returns true..." );
		assertTrue( bv.check() );
	}

	@Test
	public void test_checkInvalid1()
	{
		Scanner input = new Scanner( invalidInput1 );
		BrainVerifier bv = new BrainVerifier( input );
		System.out.println( "Test if BrainVerifier.check returns false..." );
		assertFalse( bv.check() );
	}
}
