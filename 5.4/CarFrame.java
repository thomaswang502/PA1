
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates a car view
 * @author Cong Wang
 */
public class CarFrame extends JFrame implements ChangeListener {
	private static final double TOP_RATIO = 0.65;
	private static final double WHEEL_SPACING_RATIO = 0.75;
	private static final double WHEEL_SIZE_RATIO = 0.2;
	
	private CarModel car;
	private double[] data;
	
	/**
	 * Creates a car view with slider listener
	 * @param c The car model
	 */
	public CarFrame(CarModel c) {
		car = c;
		data = c.getData();
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 300));
		
		Icon carIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				
				double height = data[1] * data[0];
				double width = data[2] * data[0];
				double diameter = width * WHEEL_SIZE_RATIO;
				
				//Starting x coord
				double cTop = (width - (width * TOP_RATIO)) / 2;
				double w1 = (width - (width * WHEEL_SPACING_RATIO)) / 2;
				double w2 = (width * WHEEL_SPACING_RATIO) - w1 / 2;
				
				Rectangle2D.Double carTop = new Rectangle2D.Double (cTop, 0, width * TOP_RATIO, height);
				Rectangle2D.Double carBody = new Rectangle2D.Double (0, height, width, height);
				Ellipse2D.Double wheel1 = new Ellipse2D.Double (w1, height * 2, diameter, diameter);
				Ellipse2D.Double wheel2 = new Ellipse2D.Double (w2, height * 2, diameter, diameter);
		
				g2.draw(carTop);
				g2.draw(carBody);
				g2.draw(wheel1);
				g2.draw(wheel2);
			}
			public int getIconHeight() { return (int)(data[1] * data[0]); }
			public int getIconWidth() { return (int)(data[2] * data[0]); }
		};
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
		slider.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				double multiplier = slider.getValue() / 50.0;
				car.update(multiplier);
			}
		});
		slider.setMajorTickSpacing(1);
		
		JLabel car = new JLabel(carIcon);
		add(car);
		add(slider, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * ChangeListener stateChanged method
	 */
	public void stateChanged(ChangeEvent e)
	{
		data = car.getData();
		repaint();
	}
}