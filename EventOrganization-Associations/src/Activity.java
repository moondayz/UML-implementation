
public class Activity {

	public String nameActivity;
	private Event event;
	
	// private constructor to prevent from creating activit without creating event
	private Activity(String nameActivity, Event event) {
		this.nameActivity = nameActivity;
		this.setEvent(event);
	}
	
	
	public static Activity createActivity(String nameActivity, Event event) throws Exception {
		if(event == null || nameActivity == null) {
		throw new Exception("The given event does not exist!");
		}
		// Create a new activity
		Activity activity = new Activity(nameActivity, event);
		//reverse connection
		event.addActivity(activity);
		return activity;
		}



	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		if(event == null) {
			throw new IllegalArgumentException("Event can not be null.");
			}
		this.event = event;
	}
	
	@Override
	public String toString() {
		return "Activity name : " +  nameActivity ;
	}
}
