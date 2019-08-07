
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
A Subject class for the observer pattern.
*/
public class DataModel {
	ArrayList<Integer> data;
	ArrayList<ChangeListener> listeners;
	
	/**
    Constructs a DataModel object
    @param d the data to model
	 */
	public DataModel(ArrayList<Integer> d) {
		data = d;
	    listeners = new ArrayList<ChangeListener>();
	}
	/**
    Constructs a DataModel object
    @return the data in an ArrayList
	 */
	public ArrayList<Integer> getData() {
		return (ArrayList<Integer>)(data.clone());
	}
	
	/**
	 * Attaches a Listener to the Model
	 * @param c The listener
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	
	/**
	 * Change the data in the model at a particular location
      @param location the index of the field to change
      @param value the new value
	 */
	public void update(int location, int value) {
		data.set(location, value);
		for (ChangeListener l : listeners) 
			l.stateChanged(new ChangeEvent(this));
	}
}