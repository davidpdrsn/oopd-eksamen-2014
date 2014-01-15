package app.models;

import java.util.HashMap;

public interface IEnvironment {
  public HashMap<Point, Square> getSquares();
  public int size();
  public int numberOfStones();
  public int numberOfMice();
  public int numberOfOwls();
  public HashMap<Point, Square> getNeighborSquares(Point point);
  public void update();
}
