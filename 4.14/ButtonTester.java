import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Program that shows a frame with three buttons of varying color
 * and a label that contains a red circle 
 * Changes the color of the circle when either button is clicked
 */
public class ButtonTester extends JPanel implements ActionListener {
	JButton green = new JButton("Green");
	JButton blue = new JButton("Blue");
	JButton red = new JButton("Red");
	
	private Color color = Color.red; 

	public ButtonTester() {
		add(green);
		add(blue);
		add(red);

		green.addActionListener(this);
		blue.addActionListener(this);
		red.addActionListener(this);
	}
	
	/**
	 * Creates the circle and initializes its color as red
	 * @param g the graphics that will be the circle
	 */
	public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillOval(100, 100, 100, 100);
    }
	
	/**
	 * Determines which color the circle changes depending on what 
	 * button is pressed
	 * @param evt the action of which button is pressed
	 */
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == green) {
			color = Color.green;
    }
		else if (source == blue) {
			color = Color.blue;
    }
		else if (source == red) {
			color = Color.red;
    }
		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Change The Color of The Circle!");
		frame.setSize(300, 300);
		
		//Creates window where program will run
		//Closes program when exited and restarts when run again
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// adds program into the window
		Container contentPane = frame.getContentPane();
		contentPane.add(new ButtonTester());

		frame.show();
	}
}