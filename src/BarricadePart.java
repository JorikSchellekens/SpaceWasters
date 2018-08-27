import processing.core.PImage;

/**
 * Created by Jorik on 15/02/2017.
 */
public class BarricadePart extends PhysicsObject {
  InvadersOfBoredom parent;
  PImage image;

  BarricadePart(InvadersOfBoredom parent, float x, float y, String image) {
    this.parent = parent;
    this.image = parent.loadImage(image);
    setPosition(x, y);
  }

  public void draw() {
    parent.image(this.image, getX(), getY());
  }
}
