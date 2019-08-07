import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   This program implements an animation that moves
   a plane shape.
 */
public class AnimationTester
{
	/**
      Construct a frame and animate a plane in it.
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		final MoveableShape shape = new PlaneShape(0, 0, CAR_WIDTH);

		final ShapeIcon icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);

		final JLabel label = new JLabel(icon);
		frame.setLayout(new FlowLayout());
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int DELAY = 1;
		// milliseconds between timer ticks
		Timer t = new Timer(DELAY, new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				PlaneShape temp = (PlaneShape)shape;
            	if (frame.getContentPane().getSize().getWidth() < temp.getX()) {
            		shape.translate((int)(-1 * frame.getContentPane().getSize().getWidth()), 0);
            		temp.setX(-1 * CAR_WIDTH);
            	}else{
					shape.translate(1, 0);
            	}
					label.repaint();
			}
			});
		t.start();
		}

		private static final int ICON_WIDTH = 400;
		private static final int ICON_HEIGHT = 100;
		private static final int CAR_WIDTH = 100;
	}
