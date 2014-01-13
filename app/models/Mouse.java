package app.models;

/**
 * A null or empty entity.
 */
public class Mouse extends Entity {
  public boolean isMouse() {
    return true;
  }

  // TODO: are all mice really the same?!
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Mouse)) return false;

    return true;
  }
}
