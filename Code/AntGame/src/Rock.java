import processing.core.*;
public class Rock {
	
	PImage image;
	PApplet parent;

	public Rock(PApplet p){
		this.parent = p;
		image = parent.loadImage("Rock.png");
	}
	
	public void draw(PVector pos){
		//This needs to be changed to make rock central to position of the hexagon.. this is an approximation with current image
		parent.image(image,pos.x+ 8, pos.y-6);
	}
}

