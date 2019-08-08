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
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class manage the calendar
 * 
 * @author Cong Wang
 *
 */
public class MyCalendar {
	private static LocalDate currentDate = LocalDate.now();
	private static ArrayList<RegularEvent> regulars = new ArrayList<RegularEvent>();
	private static ArrayList<OneTimeEvent> oneTimes = new ArrayList<OneTimeEvent>();
	private static Scanner sc = new Scanner(System.in);
	
	/**
     * Method that load the input.txt
     * @throws IOException
     */
    public static void loadEvents() throws IOException{
    	FileReader file = new FileReader("input.txt");
    	BufferedReader reader = new BufferedReader(file);
    	String firstLine = "";
    	String secondLine = "";
    	while ((firstLine = reader.readLine()) != null){
    		secondLine = reader.readLine();
			String[] strs = secondLine.split(" ");
    		if(secondLine.startsWith("S") || secondLine.startsWith("M") 
    			|| secondLine.startsWith("T") || secondLine.startsWith("W")
    			|| secondLine.startsWith("R") || secondLine.startsWith("F")
    			|| secondLine.startsWith("A")){
    			RegularEvent regularEvent = new RegularEvent(firstLine,strs[0],strs[1],strs[2],strs[3],strs[4]);
    			regulars.add(regularEvent);
    		}else{
    			OneTimeEvent oneTimeEvent = new OneTimeEvent(firstLine,strs[1],strs[2],strs[0]);
    			oneTimes.add(oneTimeEvent);
    		}
    	}
    	System.out.println("Load completed");
    	reader.close();
    }
    /**
     * Method that print the current month
     * @param date the local date
     * 
     */
    public static void printCurrentMonth(LocalDate date){
    	int month = date.getMonthValue();
    	int today = date.getDayOfMonth();
    	date = date.minusDays(today-1);
    	int dayOfWeek = date.getDayOfWeek().getValue();
    	System.out.println("	"+ date.getMonth() + " " + date.getYear());
    	System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    	for(int i = 0; i < dayOfWeek; i++)
    		System.out.printf("    ");
    	
    	while(date.getMonthValue() == month){
			if (date.getDayOfMonth() == currentDate.getDayOfMonth() && date.getMonthValue() == currentDate.getMonthValue() && date.getYear() == currentDate.getYear()){
				System.out.print("["+ date.getDayOfMonth() + "]");
			}
			else
				System.out.printf("%3d", date.getDayOfMonth());
			System.out.print(" ");
			date = date.plusDays(1);
			if (date.getDayOfWeek().getValue() == 7)
				System.out.println();
		}
//		if (date.getDayOfWeek().getValue() != 1)
		System.out.println();
    }
    
    /**
     * Method that choose day view or month view
     * @throws ParseException
     */
    public static void viewBy() throws ParseException{
    	System.out.println("[D]ay view of [M]onth view");
    	LocalDate current = currentDate;
    	String currentView = "";
    	int flag = 0;
    	while(sc.hasNextLine()){
    		String option = sc.nextLine();
    		if(flag == 0){
    			if(option.equals("D")){
    				dayView(current);
    				currentView = "day";
	        	}else if(option.equals("M")){
	        		monthView(current);
	        		currentView = "month";
	        	}
    			flag = 1;
    		}else{
    			if(currentView.equals("day") && option.equals("P")){
    				current = current.minusDays(1);
    				dayView(current);
    			}
    			if(currentView.equals("day") && option.equals("N")){
    				current = current.plusDays(1);
    				dayView(current);
    			}
    			if(currentView.equals("month") && option.equals("P")){
    				current = current.minusMonths(1);
    				monthView(current);
    			}
    			if(currentView.equals("month") && option.equals("N")){
    				current = current.plusMonths(1);
    				monthView(current);
    			}
    			if(option.equals("G"))
    				break;
    		}
    		
    		System.out.println("[P]revious or [N]next or [G]o back to main menu ?");
    	}
    }
    /**
     * Method that display the day view
     * @param current
     * @throws ParseException
     */
    public static void dayView(LocalDate current) throws ParseException{
    	ArrayList<String> title = new ArrayList<String>();
    	ArrayList<String> stime = new ArrayList<String>();
    	ArrayList<String> timeInterval = new ArrayList<String>();
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	System.out.println(current.getDayOfWeek() + ", " + current.getMonth() + " " 
				+ current.getDayOfMonth() + ", " + current.getYear());
		for(int i = 0; i < regulars.size(); i++){
        	Date startDate = format.parse(regulars.get(i).getStartDate());
        	Date endDate = format.parse(regulars.get(i).getEndDate());
        	TimeInterval timeInter = new TimeInterval(startDate,endDate,Date.from(current.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        	if(timeInter.in()){
        		char[] weeks = regulars.get(i).getDayInWeek().toCharArray();
        		char[] abbrev = {'S','M','T','W','R','F','A'};
        		char curr;
        		if(current.getDayOfWeek().getValue() == 7)
        			curr = abbrev[0];
        		else
        			curr = abbrev[current.getDayOfWeek().getValue()];
        		for(int j = 0; j < weeks.length; j++){
        			if(weeks[j] == curr){
        				title.add(regulars.get(i).getEventTitle());
        				timeInterval.add(regulars.get(i).getStartTime() + "-" + regulars.get(i).getEndTime());
        				stime.add(regulars.get(i).getStartTime());
        			}
        		}
        	}
        }
        for(int i = 0; i < oneTimes.size(); i++){
        	String startDate = format.format(Date.from(current.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        	if(startDate.equals(oneTimes.get(i).getStartDate())){
        		title.add(oneTimes.get(i).getEventTitle());
        		timeInterval.add(oneTimes.get(i).getStartTime() + "-" + oneTimes.get(i).getEndTime());
        		stime.add(oneTimes.get(i).getStartTime());
        	}
        }
        Date tempDate = null;
		ArrayList<Date> timeList = new ArrayList<Date>();
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        for(int i = 0; i < stime.size(); i++){
        	timeList.add(time.parse(stime.get(i)));
        }
		for (int i = timeList.size()-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(timeList.get(j).after(timeList.get(j+1))){
					tempDate = timeList.get(j);
					timeList.set(j, timeList.get(j+1));
					timeList.set(j+1, tempDate);
					String t = title.get(j);
					title.set(j, title.get(j+1));
					title.set(j+1, t);
					String tm = timeInterval.get(j);
					timeInterval.set(j, timeInterval.get(j+1));
					timeInterval.set(j+1, tm);
				}
			}
		}
		for (int i = 0; i < timeList.size(); i++) {
			System.out.println(title.get(i) + " : " + timeInterval.get(i));
		}
    }
    /**
     * Method that display the month view
     * @param current
     * @throws ParseException
     */
    public static void monthView(LocalDate current) throws ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	int month = current.getMonthValue();
    	int today = current.getDayOfMonth();
    	current = current.minusDays(today-1);
    	int dayOfWeek = current.getDayOfWeek().getValue();
    	System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    	for(int i = 0; i < dayOfWeek; i++)
    		System.out.printf("    ");
    	
    	while(current.getMonthValue() == month){
    		String monthString = "";
    		String dayString = "";
    		if(month < 10)
    			monthString = "0" + String.valueOf(month);
    		else
    			monthString = String.valueOf(month);
    		if(current.getDayOfMonth() < 10)
    			dayString = "0" + String.valueOf(current.getDayOfMonth());
    		else
    			dayString = String.valueOf(current.getDayOfMonth());
    		Date monthviewdate = format.parse(monthString + "/" + dayString + "/" + String.valueOf(current.getYear()));
    		int flag = 0;
    		for(int i = 0; i < regulars.size(); i++){
    			Date startDate = format.parse(regulars.get(i).getStartDate());
            	Date endDate = format.parse(regulars.get(i).getEndDate());
            	TimeInterval timeInter = new TimeInterval(startDate,endDate,Date.from(current.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            	if(timeInter.in()){
            		char[] weeks = regulars.get(i).getDayInWeek().toCharArray();
            		char[] abbrev = {'S','M','T','W','R','F','A'};
            		for(int j = 0; j < weeks.length ; j++){
            			for(int k = 0; k < abbrev.length ; k++){
            				if(weeks[j] == abbrev[k]){
            					if(current.getDayOfWeek().getValue() == 7 && k == 0)
            						flag = 1;
            					else if(k == current.getDayOfWeek().getValue())
            						flag = 1;
            				}
            			}
            		}
            	}
    		}
    		 for(int i = 0; i < oneTimes.size(); i++){
             	String startDate = format.format(Date.from(current.atStartOfDay(ZoneId.systemDefault()).toInstant()));
             	if(startDate.equals(oneTimes.get(i).getStartDate())){
             		flag = 1;
             	}
             }
    		if(flag == 1)
    			System.out.print("{" + current.getDayOfMonth() + "}");
    		else
    			System.out.printf("%3d", current.getDayOfMonth());
			System.out.print(" ");
			current = current.plusDays(1);
			if (current.getDayOfWeek().getValue() == 7)
				System.out.println();
		}
//		if (date.getDayOfWeek().getValue() != 1)
		System.out.println();
    }
    /**
     * Method that create the events
     * @throws ParseException
     * @throws IOException 
     */
    public static void creat() throws ParseException, IOException{
    	FileWriter file = new FileWriter("D:\\output.txt");
    	BufferedWriter writer = new BufferedWriter(file);
    	System.out.println("Creat a one-time event");
    	System.out.print("Title:");
    	String title = sc.nextLine();
    	System.out.print("(format: MM/DD/YYYY)Date:");
    	String date = sc.nextLine();
    	System.out.print("(format: HH:mm)Start time:");
    	String startTime = sc.nextLine();
    	System.out.print("(format: HH:mm)Ending time:");
    	String endTime = sc.nextLine();
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		Date specDate = format.parse(date);
		Instant  instant = specDate.toInstant();
	    ZoneId zoneId  = ZoneId.systemDefault();
	    LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
		ArrayList<String> timeInterval = new ArrayList<String>(); 
    	for(int i = 0; i < regulars.size(); i++){
    		Date startDate = format.parse(regulars.get(i).getStartDate());
        	Date endDate = format.parse(regulars.get(i).getEndDate());
        	TimeInterval timeInter = new TimeInterval(startDate,endDate,Date.from(specDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        	if(timeInter.in()){
        		char[] weeks = regulars.get(i).getDayInWeek().toCharArray();
        		char[] abbrev = {'S','M','T','W','R','F','A'};
        		for(int j = 0; j < weeks.length ; j++){
        			for(int k = 0; k < abbrev.length ; k++){
        				if(weeks[j] == abbrev[k]){
        					if(specDateLocal.getDayOfWeek().getValue() == 7 && k == 0){
        						timeInterval.add(regulars.get(i).getStartTime() + "-" + regulars.get(i).getEndTime());
        					}
        					else if(k == specDateLocal.getDayOfWeek().getValue())
        						timeInterval.add(regulars.get(i).getStartTime() + "-" + regulars.get(i).getEndTime());
        				}
        			}
        		}
        	}
    	}
    	for(int i = 0; i < oneTimes.size(); i++){
         	String startDate = format.format(Date.from(specDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
         	if(startDate.equals(oneTimes.get(i).getStartDate())){
         		timeInterval.add(oneTimes.get(i).getStartTime() + "-" + oneTimes.get(i).getEndTime());
         	}
        }
    	Date startInput = time.parse(startTime);
    	Date endInput = time.parse(endTime);
    	int flag = 0;
    	for(int i = 0; i < timeInterval.size(); i++){
    		String[] temp = timeInterval.get(i).split("-");
    		Date start = time.parse(temp[0]);
    		Date end = time.parse(temp[1]);
    		if((startInput.after(start) && startInput.before(end)) || (endInput.after(start) && endInput.before(end)))
    			flag = 1;
    	}
    	if(flag == 0){
    		oneTimes.add(new OneTimeEvent(title,startTime,endTime,date));
    		System.out.println("Add event successfully");
    	}else
    		System.out.println("Time conflict");
    	for(int i = 0; i < regulars.size(); i++){
			writer.write(regulars.get(i).getEventTitle()+"\r\n");
			writer.write(regulars.get(i).getDayInWeek()+" "+regulars.get(i).getStartTime()+" "+regulars.get(i).getEndTime()+" "
					+regulars.get(i).getStartDate()+" "+regulars.get(i).getEndDate()+"\r\n");
		}
		for(int i = 0; i < oneTimes.size(); i++){
			writer.write(oneTimes.get(i).getEventTitle()+"\r\n");
			writer.write(oneTimes.get(i).getStartDate()+" "+oneTimes.get(i).getStartTime()+" "+oneTimes.get(i).getEndTime()+"\r\n");
		}
		writer.flush();
		writer.close();
    }
    /**
     * Method that see the day's event
     * @throws ParseException
     */
    public static void goTo() throws ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	System.out.println("(format: MM/DD/YYYY)Enter the date you want to go:");
    	String date = sc.nextLine();
    	Date specDate = format.parse(date);
		Instant  instant = specDate.toInstant();
	    ZoneId zoneId  = ZoneId.systemDefault();
	    LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
	    dayView(specDateLocal);
    }
    /**
     * Method that show all the events
     * @throws ParseException
     */
    public static void eventList() throws ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    	ArrayList<String> title = new ArrayList<String>();
    	ArrayList<String> dayOfWeek = new ArrayList<String>();
    	ArrayList<String> date = new ArrayList<String>();
    	ArrayList<String> month = new ArrayList<String>();
    	ArrayList<String> yearall = new ArrayList<String>();
    	ArrayList<String> dayOfMonth = new ArrayList<String>();
    	ArrayList<String> startTime = new ArrayList<String>();
    	ArrayList<String> endTime = new ArrayList<String>();
		for (int i = 0; i < regulars.size(); i++) {
			Date startDate = format.parse(regulars.get(i).getStartDate());
			Date endDate = format.parse(regulars.get(i).getEndDate());
			int year = Integer.valueOf(regulars.get(i).getStartDate().split("/")[2]);
			int Month = Integer.valueOf(regulars.get(i).getStartDate().split("/")[0]);
			int day = Integer.valueOf(regulars.get(i).getStartDate().split("/")[1]);
			Instant instant = startDate.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
//			LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
			LocalDate specDateLocal = LocalDate.of(year,Month,day);
//			LocalDate specDateLocal = LocalDate.of(2019,2,13);
			
			while (Date.from(specDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()).before(endDate)) {
				int specmonth = specDateLocal.getMonthValue();
				String monthString = "";
	    		String dayString = "";
	    		if(specmonth < 10)
	    			monthString = "0" + String.valueOf(specmonth);
	    		else
	    			monthString = String.valueOf(specmonth);
	    		if(specDateLocal.getDayOfMonth() < 10)
	    			dayString = "0" + String.valueOf(specDateLocal.getDayOfMonth());
	    		else
	    			dayString = String.valueOf(specDateLocal.getDayOfMonth());
				char[] weeks = regulars.get(i).getDayInWeek().toCharArray();
				char[] abbrev = { 'S', 'M', 'T', 'W', 'R', 'F', 'A' };
				for (int j = 0; j < weeks.length; j++) {
					for (int k = 0; k < abbrev.length; k++) {
						if (weeks[j] == abbrev[k]) {
							if (specDateLocal.getDayOfWeek().getValue() == 7 && k == 0) {
								title.add(regulars.get(i).getEventTitle());
								dayOfWeek.add(specDateLocal.getDayOfWeek().toString());
								date.add(monthString + "/" + dayString + "/" + String.valueOf(specDateLocal.getYear()));
								month.add(String.valueOf(specDateLocal.getMonth()));
								yearall.add(String.valueOf(specDateLocal.getYear()));
								dayOfMonth.add(String.valueOf(specDateLocal.getDayOfMonth()));
								startTime.add(regulars.get(i).getStartTime());
								endTime.add(regulars.get(i).getEndTime());
							} else if (k == specDateLocal.getDayOfWeek().getValue()) {
								title.add(regulars.get(i).getEventTitle());
								dayOfWeek.add(specDateLocal.getDayOfWeek().toString());
								date.add(monthString + "/" + dayString + "/" + String.valueOf(specDateLocal.getYear()));
								month.add(String.valueOf(specDateLocal.getMonth()));
								yearall.add(String.valueOf(specDateLocal.getYear()));
								dayOfMonth.add(String.valueOf(specDateLocal.getDayOfMonth()));
								startTime.add(regulars.get(i).getStartTime());
								endTime.add(regulars.get(i).getEndTime());
							}
						}
					}
				}
				specDateLocal = specDateLocal.plusDays(1);
				
			}
		}
		for (int i = 0; i < oneTimes.size(); i++) {
			Date startDate = format.parse(oneTimes.get(i).getStartDate());
			Instant instant = startDate.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
			int specmonth = specDateLocal.getMonthValue();
			String monthString = "";
    		String dayString = "";
    		if(specmonth < 10)
    			monthString = "0" + String.valueOf(specmonth);
    		else
    			monthString = String.valueOf(specmonth);
    		if(specDateLocal.getDayOfMonth() < 10)
    			dayString = "0" + String.valueOf(specDateLocal.getDayOfMonth());
    		else
    			dayString = String.valueOf(specDateLocal.getDayOfMonth());
			title.add(oneTimes.get(i).getEventTitle());
			date.add(monthString + "/" + dayString + "/" + String.valueOf(specDateLocal.getYear()));
			dayOfWeek.add(specDateLocal.getDayOfWeek().toString());
			month.add(String.valueOf(specDateLocal.getMonth()));
			yearall.add(String.valueOf(specDateLocal.getYear()));
			dayOfMonth.add(String.valueOf(specDateLocal.getDayOfMonth()));
			startTime.add(oneTimes.get(i).getStartTime());
			endTime.add(oneTimes.get(i).getEndTime());
		}
		Date tempDate = null;
		ArrayList<Date> timeList = new ArrayList<Date>();
		for(int i = 0; i < startTime.size(); i++){
//			System.out.println(date.get(i));
        	timeList.add(time.parse(date.get(i)+" "+startTime.get(i)));
        }
		for (int i = timeList.size()-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(timeList.get(j).after(timeList.get(j+1))){
					tempDate = timeList.get(j);
					timeList.set(j, timeList.get(j+1));
					timeList.set(j+1, tempDate);
					String t = title.get(j);
					title.set(j, title.get(j+1));
					title.set(j+1, t);
					String datetemp = date.get(j);
					date.set(j, date.get(j+1));
					date.set(j+1, datetemp);
					String dayOfWeektemp = dayOfWeek.get(j);
					dayOfWeek.set(j, dayOfWeek.get(j+1));
					dayOfWeek.set(j+1, dayOfWeektemp);
					String monthtemp = month.get(j);
					month.set(j, month.get(j+1));
					month.set(j+1, monthtemp);
					String startTimetemp = startTime.get(j);
					startTime.set(j, startTime.get(j+1));
					startTime.set(j+1, startTimetemp);
					String endTimetemp = endTime.get(j);
					endTime.set(j, endTime.get(j+1));
					endTime.set(j+1, endTimetemp);
					String dayOfMonthtemp = dayOfMonth.get(j);
					dayOfMonth.set(j, dayOfMonth.get(j+1));
					dayOfMonth.set(j+1, dayOfMonthtemp);
					String yeartemp = yearall.get(j);
					yearall.set(j, yearall.get(j+1));
					yearall.set(j+1, yeartemp);
				}
			}
		}
//		for(int i = 0; i < timeList.size(); i++){
//        	System.out.println(timeList.get(i));
//        }
		int oldYear = Integer.valueOf(yearall.get(0));
		System.out.println(oldYear);
		int newYear;
		for(int i = 0; i < timeList.size(); i++){
			newYear = Integer.valueOf(yearall.get(i));
			if(newYear == oldYear){
				System.out.println("   " + dayOfWeek.get(i) + " " + month.get(i) + " " + dayOfMonth.get(i) + " " 
						+ startTime.get(i) + " - " + endTime.get(i) + " " + title.get(i));
			}else{
				oldYear = newYear;
				System.out.println(newYear);
				System.out.println("   " + dayOfWeek.get(i) + " " + month.get(i) + " " + dayOfMonth.get(i) + " " 
						+ startTime.get(i) + " - " + endTime.get(i) + " " + title.get(i));
			}
        }
    }
    /**
     * Method that delete the events
     * @throws ParseException
     */
    public static void delete() throws ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	SimpleDateFormat time = new SimpleDateFormat("HH:mm");
    	System.out.println("[S]elected or [A]ll or [DR]?");
    	String option = sc.nextLine();
    	if(option.equals("S")){
    		System.out.println("Enter the date [mm/dd/yyyy]");
    		String date = sc.nextLine();
    		Date specDate = format.parse(date);
    		Instant  instant = specDate.toInstant();
    	    ZoneId zoneId  = ZoneId.systemDefault();
    	    LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
    	    ArrayList<String> title = new ArrayList<String>();
        	ArrayList<String> stime = new ArrayList<String>();
        	ArrayList<String> timeInterval = new ArrayList<String>();
        	System.out.println(date);
            for(int i = 0; i < oneTimes.size(); i++){
            	String startDate = format.format(Date.from(specDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            	if(startDate.equals(oneTimes.get(i).getStartDate())){
            		title.add(oneTimes.get(i).getEventTitle());
            		timeInterval.add(oneTimes.get(i).getStartTime() + " - " + oneTimes.get(i).getEndTime());
            		stime.add(oneTimes.get(i).getStartTime());
            	}
            }
            Date tempDate = null;
    		ArrayList<Date> timeList = new ArrayList<Date>();
            for(int i = 0; i < stime.size(); i++){
            	timeList.add(time.parse(stime.get(i)));
            }
    		for (int i = timeList.size()-1; i > 0; i--) {
    			for (int j = 0; j < i; j++) {
    				if(timeList.get(j).after(timeList.get(j+1))){
    					tempDate = timeList.get(j);
    					timeList.set(j, timeList.get(j+1));
    					timeList.set(j+1, tempDate);
    					String t = title.get(j);
    					title.set(j, title.get(j+1));
    					title.set(j+1, t);
    					String tm = timeInterval.get(j);
    					timeInterval.set(j, timeInterval.get(j+1));
    					timeInterval.set(j+1, tm);
    				}
    			}
    		}
    		for (int i = 0; i < timeList.size(); i++) {
    			System.out.println("   " + timeInterval.get(i) + " "+ title.get(i));
    		}
    		System.out.print("Enter the name of the event to delete: ");
    		String name = sc.nextLine();
    		for(int i = 0;i < oneTimes.size(); i++){
    			if(oneTimes.get(i).getEventTitle().equals(name)){
    				oneTimes.remove(i);
    			}
    				
    		}
    		for(int i = 0;i < timeInterval.size(); i++){
    			if(title.get(i).equals(name)){
    				timeInterval.remove(i);
    				title.remove(i);
    			}
    				
    		}
    		System.out.println("The event is deleted. Here is the current scheduled event.");
    		System.out.println(date);
    		for (int i = 0; i < timeInterval.size(); i++) {
    			System.out.println("   " + timeInterval.get(i) + " "+ title.get(i));
    		}
    	}else if(option.equals("A")){
    		System.out.println("Enter the date [mm/dd/yyyy]");
    		String date = sc.nextLine();
    		Date specDate = format.parse(date);
    		Instant  instant = specDate.toInstant();
    	    ZoneId zoneId  = ZoneId.systemDefault();
    	    LocalDate specDateLocal = instant.atZone(zoneId).toLocalDate();
    	    ArrayList<String> title = new ArrayList<String>();
        	ArrayList<String> stime = new ArrayList<String>();
        	ArrayList<String> timeInterval = new ArrayList<String>();
        	System.out.println(date);
            for(int i = 0; i < oneTimes.size(); i++){
            	String startDate = format.format(Date.from(specDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            	if(startDate.equals(oneTimes.get(i).getStartDate())){
            		title.add(oneTimes.get(i).getEventTitle());
            		timeInterval.add(oneTimes.get(i).getStartTime() + " - " + oneTimes.get(i).getEndTime());
            		stime.add(oneTimes.get(i).getStartTime());
            	}
            }
            Date tempDate = null;
    		ArrayList<Date> timeList = new ArrayList<Date>();
            for(int i = 0; i < stime.size(); i++){
            	timeList.add(time.parse(stime.get(i)));
            }
    		for (int i = timeList.size()-1; i > 0; i--) {
    			for (int j = 0; j < i; j++) {
    				if(timeList.get(j).after(timeList.get(j+1))){
    					tempDate = timeList.get(j);
    					timeList.set(j, timeList.get(j+1));
    					timeList.set(j+1, tempDate);
    					String t = title.get(j);
    					title.set(j, title.get(j+1));
    					title.set(j+1, t);
    					String tm = timeInterval.get(j);
    					timeInterval.set(j, timeInterval.get(j+1));
    					timeInterval.set(j+1, tm);
    				}
    			}
    		}
    		for (int i = 0; i < timeList.size(); i++) {
    			System.out.println("   " + timeInterval.get(i) + " "+ title.get(i));
    		}
    		System.out.println("Delete all the events ? [Y]es or [N]o");
    		String input = sc.nextLine();
    		if(input.equals("Y")){
    			for(int i = 0;i < oneTimes.size(); i++){
        			if(oneTimes.get(i).getStartDate().equals(date)){
        				oneTimes.remove(i);
        				i--;
        			}
        		}
    			System.out.println("Delete completed");
    		}else if(input.equals("N")){
    			System.out.println("Operate canceled");
    		}
    	}else if(option.equals("DR")){
    		System.out.println("All regular events");
    		for(int i = 0;i < regulars.size(); i++){
    				System.out.println(regulars.get(i).getStartTime() + " - " + regulars.get(i).getEndTime() + " " + regulars.get(i).getEventTitle());
    		}
    		System.out.println("Enter the name");
    		String name = sc.nextLine();
    		for(int i = 0;i < regulars.size(); i++){
    			if(regulars.get(i).getEventTitle().equals(name)){
    				regulars.remove(i);
    				i--;
    			}
    		}
    		System.out.println("Deleted complete");
    	}
    }
}
