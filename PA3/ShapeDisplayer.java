import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class ShapeDisplayer 
{
   public static void main(String[] args)
   {
      ShapeFrame frame = new ShapeFrame();


      frame.addShape(new SnowMan(0, 0, 20));
      frame.addShape(new CarShape(0, 0, 50));
      frame.addShape(new MyShape(0, 0, 50));
      
      frame.setSize(300, 400);
      frame.setTitle("Shape Displayer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

   }
}