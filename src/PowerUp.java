/**
 * Created by Jorik on 09/02/2017.
 */
import static game.CONSTANTS.*;

public class PowerUp extends Bullet {

  PowerUp(InvadersOfBoredom parent, float x, float y, int speed) {
    super(parent, x, y, speed);
    image = parent.loadImage(POWERUP_IMAGE);
  }


  public void checkCollide(Defender player) {
    if ( player != null
        && (getX() + image.width / 2) > player.getX() - player.image.width/2
        && (getX() - image.width / 2) < player.getX() + player.image.width/2
        && (getY() - image.width / 2) < player.getY() + player.image.width/2
        && (getY() + image.width / 2) > player.getY() - player.image.width/2) {
      player.powerUp();
      removable = true;
    }
  }
}
