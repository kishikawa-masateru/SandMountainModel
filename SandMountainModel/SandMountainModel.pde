import mountain.*;
import util.*;

MountainSquare mountain;
int COLUMNS = 40;
int ROWS = 40;

void setup() {
  try {
    mountain = new MountainSquare(COLUMNS, ROWS);
  }  
  catch(InvalidArgumentException e) {
  }

  size(800, 800);
}

void draw() {
  background(255);
  int randomIndex_X = int(random(COLUMNS));
  int randomIndex_Y = int(random(ROWS));
  mountain.increase(randomIndex_X, randomIndex_Y);
  drawSquare(mountain.getSquare());
}

void drawSquare(int[][] square) {
  for (int y = 0; y < square.length; y++) {
    for (int x = 0; x < square[0].length; x++) {
      fill(0, square[y][x]/3.0 * 255);
      rect(COLUMNS*x, ROWS*y, COLUMNS, ROWS);
    }
  }
}
