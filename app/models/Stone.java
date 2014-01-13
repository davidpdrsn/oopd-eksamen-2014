package app.models;

public class Stone {
  public boolean equals(Object obj) {
    if ((obj == null) || (obj.getClass() != this.getClass())) {
      return false;
    } else {
      return true;
    }
  }
}
