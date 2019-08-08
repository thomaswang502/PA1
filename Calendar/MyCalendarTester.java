import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;



/**
 * A class that reads user's input and display the menu
 * 
 * @author Cong Wang
 *
 */
public class MyCalendarTester {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String [] args) throws IOException, ParseException
    {
        MyCalendar calendar = new MyCalendar();    
		LocalDate cal = LocalDate.now(); // capture today
        calendar.printCurrentMonth(cal);
        calendar.loadEvents();

        System.out.println("Select one of the following options:");
    	System.out.println("[V]iew by, [C]reate, [G]o to, [E]vent list, [D]elete, [Q]uit");
    	while(sc.hasNextLine()){
    		
    		String option = sc.nextLine();
        	switch (option){
        		case "V":
        			calendar.viewBy();
        			break;
        		case "C":
        			calendar.creat();
        			break;
        		case "G":
        			calendar.goTo();
        			break;
        		case "E":
        			calendar.eventList();
        			break;
        		case "D":
        			calendar.delete();
        			break;
        		case "Q":
        			System.out.println("Good Bye!");
        			System.exit(0);
        			break;
        		default:
        			System.out.println("please enter the correct option...");
        			break;
        	}
        	System.out.println();
        	System.out.println("Select one of the following options:");
        	System.out.println("[V]iew by, [C]reate, [G]o to, [E]vent list, [D]elete, [Q]uit");
    	}
    }
    
}