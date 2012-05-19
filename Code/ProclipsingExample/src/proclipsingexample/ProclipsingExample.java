package proclipsingexample;

import processing.core.PApplet;


public class ProclipsingExample extends PApplet
{
	private static final long serialVersionUID = 1L;

	public void setup()
	{
		size( 400, 400 );
	}

	public void draw()
	{
		ellipse( 100, 100, 100, 100 );
		ExternalClass ec = new ExternalClass( this );
		ec.DrawRect();
	}
}
