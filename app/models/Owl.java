package app.models;

import java.util.ArrayList;
import java.util.HashMap;

import app.services.*;

/**
 * An owl.
 */
public class Owl extends Entity {
  private Point location;
  private Environment env;

  /**
   * Check if the entity is an owl or not. This is always true.
   * @return whether its an owl or not.
   */
  public boolean isOwl() {
    return true;
  }

  /**
   * Find the point the owl wanna move to.
   * @param location the current location of the owl.
   * @param env the environment the owl is currently in.
   * @return the point the owl wanna move to.
   */
  public Point newLocation(Point location, Environment env) {
    this.location = location;
    this.env = env;

    return randomPossibleDestination();
  }

  private Point randomPossibleDestination() {
    int randomIndex = RandomGenerator.intBetween(0, possibleDestinations().size()-1);
    return possibleDestinations().get(randomIndex);
  }

  private ArrayList<Point> possibleDestinations() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      Square aSquare = env.getSquares().get(aPoint);

      if (aSquare.containsEdibleMouse() || aSquare.isEmpty()) {
        acc.add(aPoint);
      }
    }

    if (acc.isEmpty()) {
      acc.add(location);
    }

    return acc;
  }
}
