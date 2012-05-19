import java.awt.Color;

import processing.core.*;
public class Hexagon {
	
	PApplet parent;
	float hexagonSize;
	float a;
	float b;
	float c;
	float height;
	PVector position;
	public enum Type {rock,clear,banthill,ranthill};
	public Type type;
	public Color color;
	
	
	public Hexagon(PApplet parent, float hexagonSize)
	{
		this.color = new Color(127,0,0);
		this.parent = parent;
		this.hexagonSize = hexagonSize;
		c = hexagonSize;
		a = c/2;
		b = parent.sin(parent.radians(60))*c;
		height = a+c;
	}
	
	public void drawMe()
	{
		parent.beginShape();
		parent.fill(color.getRed(), color.getGreen(), color.getBlue());
		parent.vertex(b, 0);
		parent.vertex(2*b, -a);
		parent.vertex(3*b, 0);
		parent.vertex(3*b, c);
		parent.vertex(2*b, c+a);
		parent.vertex(b,c);
		parent.vertex(b,0);
		parent.endShape();
		
	}
	public void setPosition(PVector position){
		this.position = position;
	}
	public PVector getPosition()
	{
		return this.position;
	}
	public void setState(Integer state) throws Exception
	{
		switch(state){
			case(0):
				type = Type.rock;
				break;
			case(1):
				type = Type.clear;
				break;
			case(2):
				type = Type.ranthill;
				break;
			case(3):
				type=Type.banthill;
				break;
			default:  
		        throw new Exception("Unknown enum value found."); 
		}
	}
	public void setColor(Color color)
	{
		this.color = color;
		System.out.println(this.color);
	}
}
			
		

