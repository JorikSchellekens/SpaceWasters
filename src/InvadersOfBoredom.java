import processing.core.PApplet;
import static game.CONSTANTS.*;
import ddf.minim.*;
/**
 * Created by Jorik on 31/01/2017.
 */
public class InvadersOfBoredom extends PApplet {
  private InvasionFleet fleet;
  Defender player;
  BulletStream bulletStream;
  BulletStream powerUpStream;
  BulletStream bombStream;
  BarricadeCollection barricades;
  private boolean gameOver = false;
  private boolean victory = false;

  public static Minim minim;
  private AudioSample shot1;
  private AudioSample blowUp;
  private AudioSample break1;
  private AudioPlayer wobble;

  public static void main(String[] args) {
    PApplet.main("InvadersOfBoredom");
  }

  public void settings() {
    if (FULLSCREEN) {
      fullScreen();
    }
    else {
      size(WIDTH, HEIGHT);
    }
  }

  public void setup() {
    imageMode(CENTER);
    frameRate(FRAME_RATE);

    fleet = new InvasionFleet(this, 4, 12);
    player = new Defender(this);
    bulletStream = new BulletStream(this, BULLET_TYPE);
    powerUpStream = new BulletStream(this, POWER_TYPE);
    bombStream = new BulletStream(this, BOMB_TYPE);
    gameOver = false;
    barricades = new BarricadeCollection(this, height - 150);

    textFont(createFont("Impact", 80));
    textAlign(CENTER);
    minim = new Minim(this);
    shot1 = minim.loadSample(BULLET_SOUND);
    blowUp = minim.loadSample(BOMB_SOUND);
    blowUp.setGain(20);
    break1 = minim.loadSample(BREAK_SOUND);
    wobble = minim.loadFile(WOBBLE);
    wobble.setGain(-15);
    wobble.loop();
  }

  public void draw() {
    if (!gameOver) {
      background(0);
      bulletStream.move();
      powerUpStream.move();
      bombStream.move();
      fleet.move();

      bulletStream.checkCollisions(fleet.fleet);
      powerUpStream.checkCollisions(player);
      bombStream.checkCollisions(player);
      bulletStream.checkCollisions(barricades);
      powerUpStream.checkCollisions(barricades);
      bombStream.checkCollisions(barricades);

      player.moveToX(mouseX);

      fleet.draw();
      bulletStream.draw();
      powerUpStream.draw();
      bombStream.draw();
      player.draw();
      barricades.draw();
    } else {
      showGameOver();
    }
  }

  public void mousePressed() {
    if (!gameOver) {
      playShot();
      player.shoot();
    } else {
      setup();
    }
  }

  public void gameOver(boolean win) {
    gameOver = true;
    victory = win;
  }

  public void showGameOver() {
    background(0);
    text((victory)?WIN_MESSAGE:LOSS_MESSAGE, width / 2, height / 2);
  }

  public void playExplosion () {
    blowUp.trigger();
  }

  public void playShot() {
      shot1.trigger();
  }

  public void playBreak() {
    break1.trigger();
  }
}
