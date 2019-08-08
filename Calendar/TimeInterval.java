import java.time.ZoneId;
import java.util.Date;

public class TimeInterval {

	private Date startDate;
	private Date endDate;
	private Date currentDate;
	public TimeInterval(Date startDate,Date endDate,Date currentDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.currentDate = currentDate;
	}

	public boolean in(){
		if(currentDate.before(endDate) && currentDate.after(startDate))
			return true;
		else 
			return false;
	}
	
	
}
