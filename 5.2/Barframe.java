
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
  A class that implements an Observer object that displays a barchart view of
  a data model.
*/
public class Barframe extends JFrame implements ChangeListener 
{
	private static final int ICON_WIDTH = 50;
	private static final int ICON_HEIGHT = 200;
	
	private DataModel dataModel;
	private ArrayList<Integer> a;
	
	public Barframe(DataModel dataModel) 
	{
		this.dataModel = dataModel;
	    a = dataModel.getData();
		
		setLocation(0, 200);
		setLayout(new BorderLayout());
		
		Icon bars = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) 
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.blue);
				
				int index = 0;
				for (Integer i : a) {
					Rectangle2D.Double rectangle = new Rectangle2D.Double (0, ICON_WIDTH * index, i, ICON_WIDTH);
					index++;
					g2.fill(rectangle);
				}
			}
			public int getIconHeight() { return ICON_HEIGHT; }
			public int getIconWidth() { return ICON_WIDTH; }
		};
		
		MouseListener ml = new MouseListener() 
		{
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int index = 0;
				while (index * ICON_WIDTH < y) index++; //Find bar to change
				dataModel.update(--index, x);
			}
			public void mouseClicked(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
		};
		
		JLabel barGraph = new JLabel(bars);
		barGraph.addMouseListener(ml);
		add(barGraph);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	/**
    Called when the data in the model is changed.
    @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e) 
	{
		a = dataModel.getData();
		repaint();
	}
}