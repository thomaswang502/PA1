
/**
 * Class for MVCTester
 * @author Cong Wang
 */
public class MVCTester {
	public static void main(String[] args) {
		TextModel tm = new TextModel();
		ApplicationView av = new ApplicationView(tm);
		
		tm.attach(av);
	}
}