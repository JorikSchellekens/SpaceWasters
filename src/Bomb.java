/**
 * Created by Jorik on 09/02/2017.
 */
import static game.CONSTANTS.*;

public class Bomb extends Bullet {

  Invader owner;
  Bomb(InvadersOfBoredom parent, float x, float y, int speed, Invader alien) {
    super(parent, x, y, speed);
    image = parent.loadImage(BOMB_IMAGE);
    assign(alien);
  }


  public void checkCollide(Defender player) {
    if ( player != null
        && (getX() + image.width / 2) > player.getX() - player.image.width / 2
        && (getX() - image.width / 2) < player.getX() + player.image.width / 2
        && (getY() - image.width / 2) < player.getY() + player.image.width / 2
        && (getY() + image.width / 2) > player.getY() - player.image.width / 2) {
      player.blowUp();
      removable = true;
    }
  }

  public void assign(Invader alien){
    owner = alien;
  }

  public void notifyAlien() {
    owner.bombGone();
  }

  @Override
  public boolean isOffScreen() {
    boolean isOffScreen = super.isOffScreen();
    if (isOffScreen) {
      notifyAlien();
    }
    return isOffScreen;
  }
}
