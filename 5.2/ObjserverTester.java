import java.util.*;

/**
   A class for testing an implementation of the Observer pattern.
 */
public class ObjserverTester 
{
	/**
		Creates a DataModel and attaches barchart and textfield listeners
    	@param args unused
	 */
	public static void main(String[] args) 
	{
		ArrayList<Integer> data = new ArrayList<>();

		//add test numbers

		data.add(33);
		data.add(44);
		data.add(22);
		data.add(22);

		DataModel model = new DataModel(data);

		TextFrame frame = new TextFrame(model);
		Barframe barFrame = new Barframe(model);

		model.attach(barFrame);
		model.attach(frame);
	}
}