
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Models a basic car
 * @author Cong Wang
 */
public class CarModel {
	
	private ArrayList<ChangeListener> listeners;
	private double multiplier;
	private final double height;
	private final double width;
	
	/**
	 * Creates a car model
	 * @param m Multiplier
	 * @param h Height relative to car body
	 * @param w Width rel to car body
	 */
	public CarModel(double m, double h, double w)
	{
		multiplier = m;
		height = h;
		width = w;
		listeners = new ArrayList<>();
	}
	
	/**
	 * Gets the CarModel data
	 * @return data [mult, h, w]
	 */
	public double[] getData() 
	{
		return new double[]{multiplier, height, width};
	}
	
	/**
	 * Attaches a ChangeListener to the model
	 * @param cl The listener to attach
	 */
	public void attach(ChangeListener cl) {
		listeners.add(cl);
	}
	
	/**
	 * Updates the size of the multiplier
	 * @param m The new multiplier
	 */
	public void update(double m) 
	{
		multiplier = m;
		for (ChangeListener cl : listeners) cl.stateChanged(new ChangeEvent(this));
	}
	
}