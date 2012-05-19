
public class Subscription
{
	   private int price ; // subscription total price in euro-cent
	   private int length ; // length of subscription in months

	   // constructor :
	   public Subscription(int p, int n) {
	     price = p ;
	     length = n ;
	   }

	  /**
	   * Calculate the monthly subscription price in euro,
	   * rounded up to the nearest cent.
	   */
	   public double pricePerMonth() {
	     double r = (double) price / (double) length ;
	      return r ;
	   }

	  /**
	   * Call this to cancel/nulify this subscription.
	   */
	   public void cancel() { length = 0 ; }
	   
	   public static void main(String[] args)
	   {
		   Subscription s = new Subscription( 1000, 2 );
		   System.out.println( "Price per month = " + s.pricePerMonth() );
	   }
}
