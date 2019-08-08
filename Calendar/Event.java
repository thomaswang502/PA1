/**
 * this class encapsulates and create the event
 * 
 * @author Cong Wang
 *
 */
public class Event {

	private String eventTitle;
	private String startTime;
	private String endTime;
	private String startDate;
	/**
	 * 
	 * @param eventTitle
	 * @param startTime
	 * @param endTime
	 * @param startDate
	 */
	public Event(String eventTitle, String startTime, String endTime, String startDate) {
		this.eventTitle = eventTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
	}
	/**
	 * 
	 * @return String eventTitle
	 */
	public String getEventTitle() {
		return eventTitle;
	}
	/**
	 * 
	 * @param eventTitle
	 */
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	/**
	 * 
	 * @return String startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 
	 * @return String endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 
	 * @return String startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
}
