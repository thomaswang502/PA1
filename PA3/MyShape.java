

import java.awt.*;
import java.awt.geom.*;

/**
   A car that can be moved around.
   @author Text Book Sample
*/
public class MyShape implements CompositeShape
{
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public MyShape(int x, int y, int width)
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
      Rectangle2D.Double body
            = new Rectangle2D.Double(x, y + width / 6, 
                  width - 1, width / 6);
      Ellipse2D.Double frontTire
            = new Ellipse2D.Double(x + width / 6, y + width / 3, 
                  width / 6, width / 6);
      Ellipse2D.Double rearTire
            = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,
                  width / 6, width / 6);


      
      g2.draw(body);
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

   
   
   
