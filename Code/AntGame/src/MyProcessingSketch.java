import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ant_files.AntWorld;

import processing.core.*; 

public class MyProcessingSketch extends PApplet {

float lengthSize = 10;
Hexagon[][] hexagons;
AntWorld world;
AntWorldGenerator worldgen;
String[][] worldObjects;
float a, b, c;
File f;


public void setup(){
  setSize(screenWidth,screenHeight);
  background(255);
  try {
	worldgen = new AntWorldGenerator();
} catch (Exception e) {
	System.out.println("problem creating world generator");
}
  File f = new File("world.txt");
  try {
	this.world = new AntWorld(f);
} catch (IOException e) {
	System.out.println("problem creating ant world");
}
  worldObjects = this.world.getAntWorld();
  hexagons = new Hexagon[150][150];
  System.out.println(hexagons.toString());
  for(int i=0;i<hexagons[0].length;i++){
	  for(int j=0; j<hexagons[1].length;j++){
		  hexagons[i][j] = new Hexagon(this,lengthSize);
		}
  }
}
public void draw() 
{
	try {
		setHex();
	} 
	catch (Exception e) {
		System.out.println("error setting state of each hexagon");
	}
	
	drawHexagons();
	drawObjects();
	
	
}
public void setHex()
{
	try {
	for(int i=0; i<this.worldObjects[0].length;i++){
		for(int j = 0;j<this.worldObjects[1].length;j++){
			char c = worldObjects[i][j].charAt(0);
			switch(c){
			case('#'):
				hexagons[i][j].setState(0);
				break;
			case('.'):
				hexagons[i][j].setState(1);
				break;
			case('+'):
				hexagons[i][j].setState(2);
				break;
			case('-'):
				hexagons[i][j].setState(3);
				break;
			}
		}
	}
	}catch (Exception e) {
		System.out.println("hexagons doesnt exist");
		e.printStackTrace();
	}
}
public void drawHexagons()
{
	for(int i = 0; i< hexagons[0].length; i++){
		for(int j = 0; j<hexagons[1].length;j++){
			if(i%2 == 0){
				pushMatrix();
				translate(j*(2*hexagons[i][j].b), i*(hexagons[i][j].height));
				hexagons[i][j].drawMe();
				hexagons[i][j].setPosition(new PVector(j*(2*hexagons[i][j].b),i*(hexagons[i][j].height)));
				popMatrix();
			}
			else{
				pushMatrix();
				translate(((j)*(2*hexagons[i][j].b))+hexagons[i][j].b, i*(hexagons[i][j].height));
				hexagons[i][j].drawMe();
				hexagons[i][j].setPosition(new PVector((j)*(2*hexagons[i][j].b)+hexagons[i][j].b, i*(hexagons[i][j].height)));
				popMatrix();
			}
		}
	}
}

public void drawObjects()
{
	for(int i = 0; i<hexagons[0].length;i++){
		for(int j=0;j<hexagons[1].length;j++){
			switch(hexagons[i][j].type){
			case rock:
				Rock rock = new Rock(this);
				rock.draw(hexagons[i][j].position);
				hexagons[i][j].setColor(Color.orange);
				break;
			case banthill:
				hexagons[i][j].setColor(Color.black);
				break;
			case ranthill:
				hexagons[i][j].setColor(Color.red);
				break;
			default:
				hexagons[i][j].setColor(Color.yellow);
			}
		}
	}
}

static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#D4D0C8", "hexagonMouse" });
  }
}