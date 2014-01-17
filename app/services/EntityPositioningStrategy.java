package app.services;

import java.util.*;
import app.models.*;

public abstract class EntityPositioningStrategy {
  private Square square;

  public EntityPositioningStrategy(Square square) {
    this.square = square;
  }

  public boolean allows(Entity entity) {
    // The order in which we add things cannot be of any significance
    // so we add all the things to a new list and check if
    // it contains illegal combinations

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
      if (e.isMouse()) {
        mouseCount++;
      } else if (e.isStone()) {
        stoneCount++;
      } else if (e.isOwl()) {
        owlCount++;
      }
    }

    return allowedCounts(mouseCount, stoneCount, owlCount);
  }

  public abstract boolean allowedCounts(int mouseCount, int stoneCount, int owlCount);
}
