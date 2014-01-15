package app.models;

/**
 * An entity in the simulation.
 */
public abstract class Entity {
  /**
   * Check if the entity is a stone or not.
   * @return whether its a stone or not.
   */
  public boolean isStone() {
    return false;
  }

  /**
   * Check if the entity is a null entity or not.
   * @return whether its a null entity or not.
   */
  public boolean isNullEntity() {
    return false;
  }

  /**
   * Check if the entity is an owl or not.
   * @return whether its an owl or not.
   */
  public boolean isOwl() {
    return false;
  }


  /**
   * Check if the entity is a mouse or not.
   * @return whether its a mouse or not.
   */
  public boolean isMouse() {
    return false;
  }
}
