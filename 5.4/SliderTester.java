import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Class for testing slider
 * @author Cong Wang
 */
public class SliderTester {

	public static void main(String[] args) {
		CarModel car = new CarModel(1, 25, 100);
		CarFrame frame = new CarFrame(car);
		car.attach(frame);
	}
}
