package app.models;

import java.util.ArrayList;
import java.util.Iterator;
import app.services.*;

/**
 * A square in the simulation.
 */
public class Square {
  /**
   * A list of entities on this square.
   */
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
   * Null entities are not counted. So a square contain only a NullEntity has 0 entities.
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
   * Will silently fail if it was not possible to add the entity.
   * @param e the entity to add.
   */
  public void add(Entity e) {
    if (canHaveAdded(e))
      this.entities.add(e);
  }

  /**
   * Check if the given entity can be added.
   * @param e the entity to add.
   * @return if the entity can be added or not.
   */
  public boolean canHaveAdded(Entity e) {
    return new SquareMovementStrategy(this).allows(e);
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
   * This method is useful in views.
   * @return the current state of the square.
   */
  public SquareState getState() {
    if (containsUgle()) {
      if (containsNumberOfMice() != 0) {
        if (containsSten()) return SquareState.OWL_STONE_MOUSE;
        return SquareState.OWL_MOUSE;
      } else if (containsSten()) {
        return SquareState.OWL_STONE;
      } else {
        return SquareState.OWL;
      }
    } else if (containsSten()) {
      if (containsNumberOfMice() == 2) {
        return SquareState.STONE_TWO_MICE;
      } else if (containsNumberOfMice() != 0) {
        return SquareState.STONE_MOUSE;
      } else {
        return SquareState.STONE;
      }
    } else if (containsNumberOfMice() == 2) {
      return SquareState.TWO_MICE;
    } else if (containsNumberOfMice() != 0) {
      return SquareState.MOUSE;
    } else {
      return SquareState.EMPTY;
    }
  }

  /**
   * Get current number of mice.
   * @return the current number of mice.
   */
  public int containsNumberOfMice() {
    int counter = 0;

    for (Entity entity : this.entities) {
      if (entity.isMus()) {
        counter ++;
      }
    }

    return counter;
  }

  /**
   * Check if a square contains a mouse.
   * @return whether or not the square contains a mouse.
   */
  public boolean containsMus() {
    return containsNumberOfMice() > 0;
  }

  /**
   * Check if a square contains a stone.
   * @return whether or not the square contains a stone.
   */
  public boolean containsSten() {
    for (Entity entity : this.entities) {
      if (entity.isSten()) return true;
    }

    return false;
  }

  /**
   * Check if a square contains an owl.
   * @return whether or not the square contains an owl.
   */
  public boolean containsUgle() {
    for (Entity entity : this.entities) {
      if (entity.isUgle()) return true;
    }

    return false;
  }

  /**
   * Return the owl on the square, null if there is none.
   * @return an owl or null.
   */
  public Ugle getUgle() {
    for (Entity entity : this.entities) {
      if (entity.isUgle()) {
        Ugle owl = (Ugle) entity;
        return owl;
      }
    }

    return null;
  }

  /**
   * Get a list of the mice on the square.
   * If there are no mice it returns an empty list.
   * @return a list of mice on the square.
   */
  public ArrayList<Mus> getMice() {
    ArrayList<Mus> mice = new ArrayList<Mus>();

    for (Entity entity : this.entities) {
      if (entity.isMus()) {
        Mus mouse = (Mus) entity;
        mice.add(mouse);
      }
    }

    return mice;
  }

  /**
   * Remove a particular mouse from the square.
   * Will silently fail if the mouse is not on the square.
   * @param entity the entity to remove.
   */
  public void remove(Entity entity) {
    this.entities.remove(entity);
  }

  /**
   * Check if mice can reproduce on this square.
   * @return Whether or not mice can reproduce on this square.
   */
  public boolean reproductionCanHappenHere() {
    return containsNumberOfMice() == 2;
  }

  /**
   * Check if the square contains a mouse that can be eaten.
   * @return Whether or not the square contains a mouse that can be eaten.
   */
  public boolean containsEdibleMus() {
    return containsNumberOfMice() == 2 ||
           !containsSten() && containsMus();
  }

  /**
   * Return an iterator the iterating over the entities
   * on the square.
   * @return iterator.
   */
  public Iterator<Entity> iterator() {
    return this.entities.iterator();
  }
}
