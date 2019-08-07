

import java.awt.*;
import java.awt.geom.*;

/**
   A car that can be moved around.
   @author Text Book Sample
*/
public class SnowMan implements CompositeShape
{
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public SnowMan(int x, int y, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
   }

   public void translate(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

   public void draw(Graphics2D g2)
   {
      
      Ellipse2D.Double frontTire
            = new Ellipse2D.Double(x, y, 
                  width/2, width/2);
      Ellipse2D.Double rearTire
            = new Ellipse2D.Double(x, y + width/2,
                  width/2, width/2);

     
      g2.draw(frontTire);
      g2.draw(rearTire);

   }
   
   public CompositeShape getBounds() {
	   return this;
   }
   
   public int getWidth() {
	   return width;
   }
   
   public int getHeight() {
	   return 30;
   }
   
   private int x;
   private int y;
   private int width;
}
