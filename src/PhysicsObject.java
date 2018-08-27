import processing.core.PVector;

/**
 * Created by Jorik on 25/01/2017.
 */
public class PhysicsObject {
  public PVector position = new PVector();
  public PVector velocity = new PVector();
  public float acceleration = (float) 0.1;

  //setters
  public void setPosition(float x, float y){position.set(x, y);}
  public void setPositionX(float x){position.x = x;}
  public void setPositionY(float y){position.y = y;}

  public void setVelocity(float x, float y){velocity.set(x, y);}
  public void setVelocityX(float x){velocity.x = x;}
  public void setVelocityY(float y){velocity.y = y;}

  public void setAcceleration(float a){acceleration = a;}

  //getters
  public float getX(){return position.x;}
  public float getY(){return position.y;}
  public float getVelX(){return velocity.x;}
  public float getVelY(){return velocity.y;}
  public float getAccell(){return acceleration;}

  //methods
  public void accelerate(){velocity.mult(acceleration);}
  public void move(){position.add(velocity);}

  public void inflectX(){velocity.x *= -1;}
  public void inflectY(){velocity.y *= -1;}
}
