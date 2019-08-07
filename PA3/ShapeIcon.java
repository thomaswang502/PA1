import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
   An icon that is painted by drawing a Shape
*/
public class ShapeIcon implements Icon
{    private CompositeShape shape;
     private int width;
     private int height;
   /**
      Construct a ShapeIcon.
      @param aShape the shape to use when painting
      @param aWidth the width of the icon
      @param aHeight the height of the icon
   */
   public ShapeIcon(CompositeShape aShape, int aWidth, int aHeight)
   {
      shape = aShape;
      width = aWidth;
      height = aHeight;
   }

   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      double shapeWidth = shape.getBounds().getWidth();
      double shapeHeight = shape.getBounds().getHeight();

      double scaleX = Math.max(1, shapeWidth / width);
      double scaleY = Math.max(1, shapeHeight / height);
      double scale = 1 / Math.max(scaleX, scaleY);

      Graphics2D g2 = (Graphics2D)g;

      AffineTransform oldTransform = g2.getTransform();
      g2.translate(x, y);
      g2.scale(scale, scale);
      g2.setColor(Color.black);
      shape.draw(g2);
      g2.setTransform(oldTransform);
   }


}








