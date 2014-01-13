package app.models;

/**
 * A null or empty entity.
 */
public class NullEntity extends Entity {
  public boolean isNullEntity() {
    return true;
  }

  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof NullEntity)) return false;

    return true;
  }
}
