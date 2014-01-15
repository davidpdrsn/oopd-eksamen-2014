package app.models;

/**
 * All the different states a square can be in.
 * This is useful for views that need to render a square.
 */
public enum SquareState {
  EMPTY,
  OWL,
  STONE,
  MOUSE,
  OWL_MOUSE,
  OWL_STONE,
  OWL_STONE_MOUSE,
  STONE_MOUSE,
  STONE_TWO_MICE,
  TWO_MICE
}
