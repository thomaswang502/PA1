import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
   A shape that is composed of several individual shapes.
*/
public interface CompositeShapeInt
{
   /**
      Add a shape to this composite shape.
      @param aShape the shape to add
   */
   void add(Shape aShape);

   /** 
     Returns an integer Rectangle that completely encloses the Shape. 
     Note that there is no guarantee that the returned Rectangle is the 
     smallest bounding box that encloses the Shape, only that the Shape 
     lies entirely within the indicated Rectangle.
   */
   Rectangle getBounds();

   /**
      Draws this CompositeShape at the relative position to the top left corner of the bounding box.
      @param g2 the graphics context
   */
   void draw (Graphics2D g);
	   
}