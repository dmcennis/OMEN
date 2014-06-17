/**
 * 
 */
package jaudio.library;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author mcennis
 *
 */
public class Time {

	private int day;
	
	private int hour;

	private int minute;
	
	private boolean type;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
	public int getDistance(Calendar c){
		int thisMinuteCount = minute+hour*60+(day-1)*60*24;
		int currentMinuteCount = c.get(Calendar.MINUTE)+c.get(Calendar.HOUR_OF_DAY)*60+(c.get(Calendar.DAY_OF_WEEK)-1)*24*60;
		if(thisMinuteCount>currentMinuteCount){
			thisMinuteCount -= 60*24*7;
		}
		
		return currentMinuteCount - thisMinuteCount;
	}
	
	static public boolean isOn(Time[] times){
		boolean state = true;
		GregorianCalendar cal = new GregorianCalendar();
		
		// find closest previous state
		int smallestDifference = times[0].getDistance(cal);
		int index = 0;
		for(int i=1;i<times.length;++i){
			if(times[i].getDistance(cal)<smallestDifference){
				smallestDifference = times[i].getDistance(cal);
				index = i;
			}
		}
		
		return times[index].isType();
		
		
	}
	
}
