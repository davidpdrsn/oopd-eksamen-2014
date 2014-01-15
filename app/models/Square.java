package app.models;

import java.util.ArrayList;

public class Square {
  private ArrayList<Entity> entities;

  /**
   * Create a new empty square.
   */
  public Square() {
    entities = new ArrayList<Entity>();

    entities.add(new NullEntity());
  }

  /**
   * Get the number of entities currently on the square.
   * @return the number of entities on this square.
   */
  public int getNumberOfEntities() {
    int notNullEntities = 0;

    for (Entity e : this.entities) {
      if (!e.isNullEntity()) notNullEntities++;
    }

    return notNullEntities;
  }

  /**
   * Check if a square is empty.
   * @return whether or not the square is empty.
   */
  public boolean isEmpty() {
    return getNumberOfEntities() == 0;
  }

  /**
   * Add an entity, if possible.
   * @param e the entity to add.
   */
  public void add(Entity e) {
    if (canHaveAdded(e)) this.entities.add(e);
  }

  /**
   * Check if the given entity can be added.
   * @param e the entity to add.
   * @return if the entity can be added or not.
   */
  public boolean canHaveAdded(Entity e) {
    if (e.isStone() && containsStone() ||
        e.isOwl() && containsOwl() ||
        e.isMouse() && containsNumberOfMice() >= 2 ||
        getNumberOfEntities() >= 3) {
      // You could just return !(that huge condition)
      // but this I think is more readable
      return false;
    } else {
      return true;
    }
  }

  /**
   * Check whether the square contains a given entity.
   * @param entity the entity to look for.
   * @return if the square contains the entity or not.
   */
  public boolean contains(Entity e) {
    for (Entity entity : this.entities) {
      if (entity.equals(e)) return true;
    }

    return false;
  }

  /**
   * Get the current state of the square.
   * @return the current state of the square.
   */
  public SquareState getState() {
    if (containsOwl()) {
      if (containsNumberOfMice() != 0) {
        if (containsStone()) return SquareState.OWL_STONE_MOUSE;
        return SquareState.OWL_MOUSE;
      }
      if (containsStone()) return SquareState.OWL_STONE;
      return SquareState.OWL;
    }
    if (containsStone()) {
      if (containsNumberOfMice() == 2) return SquareState.STONE_TWO_MICE;
      if (containsNumberOfMice() != 0) return SquareState.STONE_MOUSE;
      return SquareState.STONE;
    }
    if (containsNumberOfMice() == 2) return SquareState.TWO_MICE;
    if (containsNumberOfMice() != 0) return SquareState.MOUSE;

    return SquareState.EMPTY;
  }

  /**
   * Get current number of mice.
   * @return the current number of mice.
   */
  public int containsNumberOfMice() {
    int counter = 0;

    for (Entity entity : this.entities) {
      if (entity.isMouse()) {
        counter ++;
      }
    }

    return counter;
  }

  /**
   * Check if a square contains a mouse.
   * @return whether or not the square contains a mouse.
   */
  public boolean containsMouse() {
    return containsNumberOfMice() > 0;
  }

  /**
   * Check if a square contains a stone.
   * @return whether or not the square contains a stone.
   */
  public boolean containsStone() {
    for (Entity entity : this.entities) {
      if (entity.isStone()) return true;
    }

    return false;
  }

  /**
   * Check if a square contains an owl.
   * @return whether or not the square contains an owl.
   */
  public boolean containsOwl() {
    for (Entity entity : this.entities) {
      if (entity.isOwl()) return true;
    }

    return false;
  }

  public ArrayList<Mouse> getMice() {
    ArrayList<Mouse> mice = new ArrayList<Mouse>();

    for (Entity entity : this.entities) {
      if (entity.isMouse()) {
        Mouse mouse = (Mouse) entity;
        mice.add(mouse);
      }
    }

    return mice;
  }

  public void remove(Mouse mouse) {
    this.entities.remove(mouse);
  }

  public boolean reproductionCanHappenHere() {
    return containsNumberOfMice() == 2;
  }
}
