package app.models;

/**
 * A null or empty entity.
 *
 * A null entity represents the "nothing" that is on a square when it is empty.
 * As per the null object pattern. Instead of having "null" flow around
 * through the system its better to extract a object with a name and behavior
 * required for when there is "nothing" of something.
 * This way we wont accidentally get NullPointerExceptions in weird places.
 */
public class NullEntity extends Entity {
  /**
   * Check if an entity is a null entity.
   */
  public boolean isNullEntity() {
    return true;
  }
}
