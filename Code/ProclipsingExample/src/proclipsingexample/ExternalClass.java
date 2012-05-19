package proclipsingexample;

import processing.core.PApplet;

public class ExternalClass
{
	private PApplet p;
	
	ExternalClass( PApplet _p )
	{
		p = _p;
	}

	public void DrawRect()
	{
		p.rect(200, 200, 20, 20 );
	}
}
