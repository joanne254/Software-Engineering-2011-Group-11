//import java.io.*;


public class Ant {
	
	public static enum color { Red, Black };
	
	//////////////////////////////////////////////////////////////////////////////////////
	// FIELDS
	public int id;
	public color c;
	public int state;
	public int resting;
	public int direction;
	public boolean has_food;
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	
	public Ant(int id, color c) {
		this.id = id;
		this.c = c;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	// SETTERS AND GETTERS
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getResting() {
		return resting;
	}

	public void setResting(int resting) {
		this.resting = resting;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isHas_food() {
		return has_food;
	}

	public void setHas_food(boolean has_food) {
		this.has_food = has_food;
	}

	public color getC() {
		return c;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	
	public static color other_color(color c)
	{
		switch(c)
		{
			case Black : return color.Red;
			case Red : return color.Black;
		}
		return c;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	// TESTING
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		int[] current_pos = { 10, 5 };
		Position.print_dir(0);
		Position.print_pos(Position.adjacent_cell(current_pos, 0));
	}
}
