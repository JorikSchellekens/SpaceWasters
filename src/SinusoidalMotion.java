import static game.CONSTANTS.RADIUS;

/**
 * Created by Jorik on 02/02/2017.
 */
// This is a very bad implementation.

public class SinusoidalMotion {
  private float revolution = 0;
  private float dx;
  private float x;
  private float dy;
  private float y;

  public SinusoidalMotion() {
    tick();
  }

  public void applyTo(PhysicsObject target) {
    applyToX(target, 1);
    applyToY(target, 1);
  }

  public void applyToX(PhysicsObject target, float magnitude) {
    target.setPositionX(target.getX() + dx * magnitude);
  }

  public void applyToY(PhysicsObject target, float magnitude) {
    target.setPositionY(target.getY() + dy * magnitude);
  }


  public void tick() {
    if (revolution % 2 == 0) {
      revolution = 0;
    }
    float previousX = x;
    x = (float) Math.sin(revolution * Math.PI) * RADIUS;
    dx = x - previousX;

    float previousY = y;
    y = (float) Math.sin(revolution * Math.PI) * RADIUS;
    dy = y - previousY;

    revolution += 0.05;
  }
}
