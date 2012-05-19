//2D array of hexagons
Hexagon[][] grid;
//size of world
int cols = 600;
int rows = 600;

void setup()
{
  size(300, 300);
  background(255);
  smooth();
  noFill();
  stroke(0);
  grid = new Hexagon[cols][rows];
  for (int i =10; i<cols;i+=20){
    for (int j=10; j<rows;j+=20){
      grid[i][j] = new Hexagon(6,i,j,10);
      }
    }
}

class Hexagon{
  
Hexagon(int n, float cx, float cy, float r)
{
  float angle = 360.0 / n;

  beginShape();
  for (int i = 0; i < n; i++)
  {
    vertex(cx + r * cos(radians(angle * i)),
      cy + r * sin(radians(angle * i)));
  }
  endShape(CLOSE);
}
}

