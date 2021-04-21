import mountain.*;
import util.*;

MountainSquare mountain;
int COLUMNS = 20;
int ROWS = 20;
int HEIGHT = 10;


void setup() {
  try {
    mountain = new MountainSquare(COLUMNS, ROWS);
  }  
  catch(InvalidArgumentException e) {
  }
  frameRate(30);
  size(1300, 600, P3D);
}

void draw() {
  background(255);
  int randomIndex_X = int(random(COLUMNS));
  int randomIndex_Y = int(random(ROWS));
  mountain.increase(randomIndex_X, randomIndex_Y);
  rotateX(radians(50));
  drawSquare(mountain.getSquare());
}

void drawSquare(int[][] square) {
  translate(width/3, height/5);
  for (int y = 0; y < square.length; y++) {
    pushMatrix();
    for (int x = 0; x < square[0].length; x++) {
      fill(square[y][x]/3.0 * 100);
      translate(ROWS, 0);
      box(ROWS, COLUMNS, square[y][x]*HEIGHT);
      //rect(COLUMNS*x, ROWS*y, COLUMNS, ROWS);
    }
    popMatrix();
    translate(0, COLUMNS);
  }
}
