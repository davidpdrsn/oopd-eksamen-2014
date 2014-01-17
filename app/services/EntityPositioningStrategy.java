package app.services;

import java.util.ArrayList;
import java.util.Iterator;
import app.models.*;

/**
 * A class that encapsulated the common logic required
 * for figuring out if a entity is allowed to be positioned
 * on a given square.
 */
public abstract class EntityPositioningStrategy {
  /**
   * The square we are looking at.
   */
  private Square square;

  /**
   * Construct a new positioning strategy for the given square.
   */
  public EntityPositioningStrategy(Square square) {
    this.square = square;
  }

  /**
   * Check if the given entity is allowed on the square.
   *
   * The order in which we add things cannot be of any significance
   * so we add all the things to a new list and check if
   * it contains illegal combinations
   *
   * @param entity the entity to check with.
   * @return if its allowed.
   */
  public boolean allows(Entity entity) {

    ArrayList<Entity> stateAfterAdding = new ArrayList<Entity>();

    Iterator<Entity> it = square.iterator();
    while (it.hasNext()) {
      stateAfterAdding.add(it.next());
    }
    stateAfterAdding.add(entity);

    int mouseCount = 0;
    int stoneCount = 0;
    int owlCount = 0;
    for (Entity e : stateAfterAdding) {
      if (e.isMus()) {
        mouseCount++;
      } else if (e.isSten()) {
        stoneCount++;
      } else if (e.isUgle()) {
        owlCount++;
      }
    }

    return allowedCounts(mouseCount, stoneCount, owlCount);
  }

  /**
   * Method to be implemented by the subclasses containing the actual rules.
   * @param mouseCount the number of mice.
   * @param stoneCount the number of stone.
   * @param owlCount the number of owls.
   * @return if its allowed.
   */
  public abstract boolean allowedCounts(int mouseCount, int stoneCount, int owlCount);
}
