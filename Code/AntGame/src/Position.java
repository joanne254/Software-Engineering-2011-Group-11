
public class Position {
	
	public static enum left_or_right { Left, Right };
	public static enum sense_dir { Here, Ahead, LeftAhead, RightAhead };

	//////////////////////////////////////////////////////////////////////////////////////
	// TOOLS
	
	private static boolean even(int n)
	{
		return ( n%2 == 0 )? true : false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	// DISPLAYING
	
	public static void print_pos(int[] pos)
	{
		System.out.println("Position is ("+pos[0]+", "+pos[1]+") :)");
	}
	
	public static void print_dir(int dir)
	{
		System.out.print("Direction is ");
		switch(dir)
		{
			case 0 : System.out.print("East"); break;
			case 1 : System.out.print("South-East"); break;
			case 2 : System.out.print("South-West"); break;
			case 3 : System.out.print("West"); break;
			case 4 : System.out.print("North-West"); break;
			case 5 : System.out.print("North-East"); break;
		}
		System.out.println(" :)");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	
	public static int[] adjacent_cell(int[] pos, int d)
	{
		int x = pos[0], y = pos[1] ;
		switch(d)
		{
			case 0 : return new int[] { x+1, y }; 
			case 1 : if( even(y) ) return new int[] { x, y+1 }; else return new int[] { x+1, y+1 }; 
			case 2 : if( even(y) ) return new int[] { x-1, y+1 }; else return new int[] { x, y+1 }; 
			case 3 : return new int[] { x-1, y };
			case 4 : if( even(y) ) return new int[] { x-1, y-1 }; else return new int[] { x, y-1 };
			case 5 : if( !even(y) ) return new int[] { x, y-1 }; else return new int[] { x+1, y-1 };
		}
		return pos;
	}
	
	
	public static int turn(left_or_right lr, int d)
	{
		switch(lr)
		{
			case Left : return (d+5)%6;
			case Right : return (d+1)%6; 
		}
		return d;
	}
	
	public static int[] sensed_cell(int[] pos, int dir, sense_dir sd)
	{
		switch(sd)
		{
			case Here : return pos;
			case Ahead : return adjacent_cell(pos, dir);
			case LeftAhead : return adjacent_cell(pos, turn(left_or_right.Left, dir));
			case RightAhead : return adjacent_cell(pos, turn(left_or_right.Right, dir));
		}
		return pos;
	}
	


}
