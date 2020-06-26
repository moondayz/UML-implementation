import java.awt.List;
import java.util.ArrayList;

public class Support {
	
	private ArrayList<Event> supportedEvent = new ArrayList<Event>();
	//private Event supportedEvent;
	private int idSupport;
	private String heading;
	private double amount;
	private String nameSponsor;
		
	public Support(int idSupport, String heading, double amount, String nameSponsor) {
		super();
		this.setIdSupport(idSupport);
		setHeading(heading);
		setAmount(amount);
		setNameSponsor(nameSponsor);
	}

	public int getIdSupport() {
		return idSupport;
	}

	public void setIdSupport(int idSupport) {
		this.idSupport = idSupport;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		
	//checking if nameEvent is null or empty
		if(heading == null || "".equals(heading)) {
			throw new IllegalArgumentException("Heading can not be null or empty.");
		}
		this.heading = heading;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		if(amount == 0) {
			throw new IllegalArgumentException("Support amount can not be zero.");
		}
		this.amount = amount;
	}
	
	//many-to-many reverse 
	public void addSupportedEvent(Event event) {
		if(event == null) {
			throw new IllegalArgumentException("Event can not be null.");
		}
		if(!supportedEvent.contains(event)) {
			supportedEvent.add(event);
		}
	}
	
	//many-to-many reverse 
	public void removeSupportedEvent(Event event) {
		if(event == null) {
			throw new IllegalArgumentException("Event can not be null.");
		}
		if(supportedEvent.contains(event)) {
			supportedEvent.remove(event);
		}
	}
	
	public String getSupportedEvent() {
		String eventName ="";
		for(Event ev : supportedEvent) {
			eventName += ev.getNameEvent() +  " event " ;
		}
		return eventName;
	}

	public String getNameSponsor() {
		return nameSponsor;
	}

	public void setNameSponsor(String nameSponsor) {
		if(nameSponsor == null || "".equals(nameSponsor)) {
			throw new IllegalArgumentException("Sponsor name can not be null or empty.");
		}
		this.nameSponsor = nameSponsor;
	}
	

	@Override
	public String toString() {
		return "Support : " + idSupport + " heading : " + heading + " Sponsor : " + nameSponsor + " amount : " + amount ;
	}
}
