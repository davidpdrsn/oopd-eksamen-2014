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
    if (e.isStone() && containsStone()) return false;
    if (e.isOwl() && containsOwl()) return false;
    if (e.isMouse() && containsNumberOfMice() >= 2) return false;
    if (getNumberOfEntities() >= 3) return false;

    return true;
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

  private int containsNumberOfMice() {
    int counter = 0;

    for (Entity entity : this.entities) {
      if (entity.equals(new Mouse())) counter++;
    }

    return counter;
  }

  private boolean containsStone() {
    return contains(new Stone());
  }

  private boolean containsOwl() {
    return contains(new Owl());
  }
}

