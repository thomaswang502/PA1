import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Tests the Filter interface type
 * 
 */
public class FilterTester {
	public static void main (String[] args){
		
		String[] test = {"abc", "def1", "ghi", "jkl3", "Cong", "Wang", "Ice"};
		for (String s : test) {
			System.out.print(s + " ");
		}
		
		String[] filtered = FilterTester.filter(test, new Filter() {
			public boolean accept(String x) {
				final int limit = 3;
				if (x.length() > limit) return false;
				return true;
			}
		});
		System.out.println();
		for (String s : filtered) {
			System.out.print(s + " ");
		}	
	}
	public static String[] filter(String[] a, Filter f){
		ArrayList b = new ArrayList();
		for(String toCheck : a){
			if(f.accept(toCheck))
				b.add(toCheck);
		}
		return (String[]) b.toArray(new String[b.size()]);
	}
	
	public interface Filter{
		public boolean accept(String x);
	}
}
