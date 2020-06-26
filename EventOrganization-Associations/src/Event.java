import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Event  {

	private List<Support> supports = new ArrayList<Support>(); // list of supports
	private List<Activity> allActs = new ArrayList<Activity>(); // list of all activities - stores all activities for not to avoid being used in different events
	private List<Activity> eventActs = new ArrayList<Activity>(); // list of all activities in the event

	private static List<String> knownTypes = new ArrayList<String>(Arrays.asList("Gala" , "Conference" , "Seminar", "Workshop", "Party", "Reunion"));
	public static List<Event> extent = new ArrayList<Event>();
	
	private int idEvent;
	private String nameEvent;
	private LocalDate releaseDate;
	private List<String> typeEvent = new ArrayList<String>(); 
	private EventCapacity capacity; // Only defined # of capacity is allowed here - enumaration
	private double budget;
	private Integer ticketPrice; // optional attribute
	private Address address;  // complex attribute
	private LocalDate dateEvent;
	

	
	public Event(int idEvent, String nameEvent, String newType , LocalDate releaseDate, EventCapacity capacity, double budget, Address address ) {
		super();
		this.idEvent = idEvent;
		this.budget = budget;
		setNameEvent(nameEvent);
		addTypeEvent(newType);
		setReleaseDate(releaseDate);
		setCapacity(capacity);
		setAddress(address);
		// Her yeni nesne eklendiginde buraya da kaydedilir. extent sinif
		extent.add(this);
		
	}

	public Event(int idEvent, String nameEvent, String newType, LocalDate releaseDate, EventCapacity capacity, double budget, Address address, Integer ticketPrice ) {
	
		this(idEvent, nameEvent, newType, releaseDate, capacity, budget, address);
		this.setTicketPrice(ticketPrice); //optional attribute
	}
	
	//printing the support list for a defined event
	public String listSupport(){
		String info = "Support list : ";
		for(Support sp : supports) {
			info += " " + sp.getHeading() + " amount " + sp.getAmount();
			}
		return info ;
	}
	//method to add support on one to many relation
	public void addSupport(Support support) {
		if(support == null) {
			throw new IllegalArgumentException("Support can not be null.");
		}
		if(!supports.contains(support)) {
			supports.add(support);
		}
		
		//reverse connection
		support.addSupportedEvent(this);

	}
	
	public void removeSupport(Support support) {
		if(support == null) {
			throw new IllegalArgumentException("Support can not be null.");
		}
		if(supports.contains(support)) {
			supports.remove(support);
		}
		
		//reverse connection
		support.removeSupportedEvent(this);
		
	}
	
	public LocalDate getDateEvent() {
		return dateEvent;
	}
	
	// when organize(class organizer) method is called we set the date for event - 
	public void setDateEvent(LocalDate dateEvent) {
		if(dateEvent == null) {
			throw new IllegalArgumentException("Event date can not be null");
		}
		this.dateEvent = dateEvent;
	}
	
	// composition
	public void addActivity(Activity act) {
		if(act == null) {
			throw new IllegalArgumentException("Activity can not be null");
		}
		if(!eventActs.contains(act)) {
			if(allActs.contains(act)) {
				throw new IllegalArgumentException("Activity is already exist in another event.");
			}
			eventActs.add(act);
			allActs.add(act);
		}
	}
	
	// removing activity if we remove event
	public void removeActivity(Activity act) {
		if(act == null) {
			throw new IllegalArgumentException("Activity can not be null");
		}
		if(eventActs.contains(act)) {
			eventActs.remove(act);
			allActs.remove(act);
			
			}
	}
	
	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		
		//checking if nameEvent is null or empty
		if(nameEvent == null || "".equals(nameEvent)) {
			throw new IllegalArgumentException("Event name can not be null or empty.");
		}
		this.nameEvent = nameEvent;
	}
	
	public List<String> getTypeEvent() {
		return new ArrayList<String>(typeEvent);
		
	}

	public void addTypeEvent(String newType) {
		
		if(newType == null || "".equals(newType.trim())) {
			throw new IllegalArgumentException("Event type can not be null or empty.");
		} 

		if(!knownTypes.contains(newType)) {
		throw new IllegalArgumentException("Event type is not known.");

	}
		this.typeEvent.add(newType);
		
	}
	
	public void removeTypeEvent(String type) {
		if(this.typeEvent.size() < 2 ) {
			throw new IllegalArgumentException("Event must have at least one type.");
		}
		this.typeEvent.remove(type);
	}
	

	public EventCapacity getCapacity() {
		return capacity;
	}

	public void setCapacity(EventCapacity capacity) {
		if(capacity == null) {
			throw new IllegalArgumentException("Capacity of event can not be null.");
		}
		this.capacity = capacity;
	}

	public Integer getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public static List<String> getKnownTypes() {
		return Collections.unmodifiableList(knownTypes);
	}

	public static void addKnownType(String newType) {
		if(newType == null || "".equals(newType.trim())) {
			throw new IllegalArgumentException("Event type can not be null or empty.");
		}
		knownTypes.add(newType);

	}
	public static void removeKnownType(String eventType) {
		if(knownTypes.size() < 2 ) {
			throw new IllegalArgumentException("There must be at least one type.");
		}
		knownTypes.remove(eventType);
	}
	
	public static List<Event> getExtent() {
		return new ArrayList<Event>(extent);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if(this.address == address || address == null) {
			throw new IllegalArgumentException("Address is null or dublicated");
		}
			this.address = address;
		
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		
		if(releaseDate == null) {
			throw new IllegalArgumentException("Date of release can not be null or empty.");
		}
		if(releaseDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Date of event can not be from the future.");
		}
		this.releaseDate = releaseDate;
	}
	
	
	
	public static void showExtent() {
		System.out.println(extent);
	}
	
	//updated - class method
	public static int totalBudget() {
		int sum = 0;
		for (Event bg : extent) {
			sum += bg.getBudget();
		}
		return sum;
	}
	
	
	@Override
	public String toString() {
		return " Event [Event id : " + idEvent + " , EventName : " + nameEvent + ", Event type : " + typeEvent + " Capacity : " + capacity + ", Budget : " + budget + ", Address : " + address.getStreet() +" " + address.getNumber()+ " - " +address.getCity() +" ]";
	}

	
}
