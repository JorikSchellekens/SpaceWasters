import java.util.Random;
import processing.core.PImage;
import static game.CONSTANTS.*;

/**
 * Created by Jorik on 31/01/2017.
 */
public class Invader extends PhysicsObject {
  InvadersOfBoredom parent;
  PImage invaderImage;
  boolean blownUp = false;
  boolean hasBomb = false;
  boolean removable = false;
  int blownOn;
  Random random = new Random();


  public Invader(InvadersOfBoredom p, int xStart, int yStart) {
    parent = p;
    invaderImage = parent.loadImage(INVADER_IMAGE_LOCATION);
    setPositionX(xStart);
    setPositionY(yStart);
    setVelocityX(SPEED);
    setVelocityY(SPEED);
  }

  public void draw() {
    if (!blownUp || parent.frameCount - blownOn < BLOWUP_TIME) {
        parent.image(invaderImage, getX(), getY());
    } else {
      removable = true;
    }
  }

  public void explode() {
    if (!blownUp) {
      invaderImage = parent.loadImage(EXPLODED_INVADER_IMAGE_LOCATION);
      blownUp = true;
      blownOn = parent.frameCount;
      if (random.nextInt(10) == 0 ) {
        parent.powerUpStream.newBullet(getX(), getY());
      }
    }
  }

  public void move(InvasionFleet observer) {
      super.move();
    if (getX() <= MARGIN + invaderImage.width / 2
        || getX() >=
        parent.width - MARGIN - invaderImage.width / 2) {
      observer.moveType = DOWN;
    }
  }

  public void bombGone() {
    hasBomb = false;
  }
}