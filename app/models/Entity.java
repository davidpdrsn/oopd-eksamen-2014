package app.models;

/**
 * An entity in the simulation.
 */
public abstract class Entity {
  public abstract boolean equals(Object obj);

  /**
   * Check if the entity is a stone or not.
   * @return whether its a stone or not.
   */
  public boolean isStone() {
    return false;
  }

  public boolean isNullEntity() {
    return false;
  }

  public boolean isOwl() {
    return false;
  }


  public boolean isMouse() {
    return false;
  }
}
