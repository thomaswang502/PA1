import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextModel {
	private ArrayList<String> strings;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Creates a new TextModel
	 */
	public TextModel() {
		strings = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	/**
	 * Attaches a ChangeListener to the model
	 * @param cl The ChangeListener to attach
	 */
	public void attach(ChangeListener cl) {
		listeners.add(cl);
	}
	
	/**
	 * Gets the data
	 * @return A copy of the data
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getData() {
		return (ArrayList<String>)strings.clone();
	}
	
	/**
	 * Adds a String to the list and lets the listeners know
	 * @param s The string to add
	 */
	public void update(String s) {
		strings.add(s);
		for (ChangeListener cl : listeners) cl.stateChanged(new ChangeEvent(this));
	}
}