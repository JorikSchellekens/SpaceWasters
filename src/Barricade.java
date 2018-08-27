import static game.BarricadeConstants.*;

public class Barricade extends PhysicsObject {
  BarricadePart[] parts = new BarricadePart[6];
  InvadersOfBoredom parent;


  Barricade(InvadersOfBoredom parent, float x, float y) {
    this.parent = parent;
    setPosition(x, y);
    setup();
  }

  public void setup() {
    parts[0] = new BarricadePart(parent, getX(), getY(), TOP);
    parts[1] = new BarricadePart(parent, getX(), getY() + Y_OFFSET_MID, MIDDLE);
    parts[2] = new BarricadePart(parent, getX() - X_OFFSET_TOP, getY() + Y_OFFSET_TOP, TOP_LEFT);
    parts[3] = new BarricadePart(parent, getX() + X_OFFSET_TOP, getY() + Y_OFFSET_TOP, TOP_RIGHT);
    parts[4] = new BarricadePart(parent, getX() - X_OFFSET_BOTTOM, getY() + Y_OFFSET_BOTTOM, BOTTOM_LEFT);
    parts[5] = new BarricadePart(parent, getX() + X_OFFSET_BOTTOM + 1, getY() + Y_OFFSET_BOTTOM, BOTTOM_RIGHT);
  }

  public void draw() {
    for (int part = 0; part < parts.length; part++) {
      if (parts[part] != null) {
        parts[part].draw();
      }
    }
  }
}
