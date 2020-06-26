import java.time.LocalDate;

//Association Class
public class OrganizesWhen {
	
	private Organizer org;
	private Event event;
	private LocalDate dateEvent;
	
	public OrganizesWhen(Organizer org, Event event, LocalDate dateEvent) {
		setOrg(org);
		setEvent(event);
		setDateEvent(dateEvent);
	
	}

	public Organizer getOrg() {
		return org;
	}

	public void setOrg(Organizer org) {
		if(org == null || "".equals(org.getFullName() )) {
			throw new IllegalArgumentException("Organizer can not be null.");
		}
		this.org = org;
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

	public LocalDate getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(LocalDate dateEvent) {
		
		if(dateEvent == null) {
			throw new IllegalArgumentException("Date of event can not be null or empty.");
		}
		if(dateEvent.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Date of event can not be from the past.");
		}
		this.dateEvent = dateEvent;
	}
}
