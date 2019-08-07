
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Use the static method "maximum" to find the longest String
 */
public class MaximumTester {
	public static void main(String [] args){
		ArrayList<String> sObj = new ArrayList<String>();
		sObj.add("Thomas");
		sObj.add("Cong");
		sObj.add("aa");
		sObj.add("rrrrrrrrr");
		sObj.add("sdasdvcxv561");

		Comparator<String> cObj = new Comparator<String>(){
			public int compare(String s1, String s2){
				return s1.length() - s2.length();
			}
		};

		String max = maximum(sObj, cObj);
		System.out.println("The Array is" + sObj);
		System.out.println("The longest string in the array list is :" + max);


	}
	/**
	 * 
	 * @param a ArrayList of Strings
	 * @param find the longest String
	 * @return the largest string
	 */
	public static String maximum(ArrayList<String> a, Comparator<String> c){

		String max = (String) a.get(0);

		for (String test : a){
			if (c.compare(test, max) > 0)
				max = test;
		}		
		return max;
	}

}
