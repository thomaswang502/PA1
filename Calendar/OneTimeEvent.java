/**
 * this class encapsulates the one time event
 * 
 * @author Cong Wang
 *
 */
public class OneTimeEvent extends Event {

	/**
	 * @param eventTitle
	 * @param startTime
	 * @param endTime
	 * @param startDate
	 */
	public OneTimeEvent(String eventTitle, String startTime, String endTime, String startDate) {
		super(eventTitle, startTime, endTime, startDate);
	}
	
}
