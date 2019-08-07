import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;


/**
   A class for testing a clock icon by updating it each second
*/
public class TestClockIcon
{
   /**
      Creates a clock and a timer to update the clock every second.
      @param args unused
   */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      ClockIcon icon = new ClockIcon(1000);
      final JLabel label = new JLabel(icon);

      final int FIELD_WIDTH = 50;

      Container contentPane = frame.getContentPane();
      contentPane.setLayout(new FlowLayout());

      contentPane.add(label);
      
      ActionListener listener = new
    	         ActionListener()
    	         {
    	            public void actionPerformed(ActionEvent event)
    	            {
    	               label.repaint();
    	               Date now = new Date();

    	            }
    	         };

      final int DELAY = 1000; 
      
      Timer t = new Timer(DELAY, listener);
      t.start();


      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.show();
   }
}

