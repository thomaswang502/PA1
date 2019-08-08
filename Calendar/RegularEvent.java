
/**
 * this class encapsulates the regular event
 * 
 * @author Cong Wang
 *
 */
public class RegularEvent extends Event {

	private String dayInWeek;
	private String endDate;
	
	/**
	 * @param eventTitle
	 * @param startTime
	 * @param endTime
	 * @param startDate
	 */
	public RegularEvent(String eventTitle, String dayInWeek, String startTime, String endTime, String startDate, String endDate) {
		super(eventTitle, startTime, endTime, startDate);
		this.dayInWeek = dayInWeek;
		this.endDate = endDate;
	}
	/**
	 * 
	 * @return String dayInWeek
	 */
	public String getDayInWeek() {
		return dayInWeek;
	}
	/**
	 * 
	 * @param dayInWeek
	 */
	public void setDayInWeek(String dayInWeek) {
		this.dayInWeek = dayInWeek;
	}
	/**
	 * 
	 * @return String endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
