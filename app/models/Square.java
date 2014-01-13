package app.models;

public class Square {
  private Object state;

  public Square() {
  }

  public Square(Object obj) {
    this.state = obj;
  }

  public boolean isEmpty() {
    return this.state == null;
  }
}
