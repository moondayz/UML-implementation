import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.time.temporal.ChronoUnit;


public class Event implements Serializable {

	private static List<String> knownTypes = new ArrayList<String>(Arrays.asList("Gala" , "Conference" , "Seminar", "Workshop", "Party", "Reunion"));
	public static List<Event> extent = new ArrayList<Event>();
	
	private int idEvent;
	private String nameEvent;
	private LocalDate dateEvent;
	private List<String> typeEvent = new ArrayList<String>();
	private EventCapacity capacity;
	private double budget;
	private Integer ticketPrice;
	private Address address;
	//private Period remainingDay; //derrived attribute
	private long remainingDay;
	
	ArrayList<Event> eventByCapacity = new ArrayList<Event>();
	
	public Event(int idEvent, String nameEvent, String newType, LocalDate dateEvent, EventCapacity capacity, double budget, Address address ) {
		super();
		this.idEvent = idEvent;
		this.budget = budget;
		setNameEvent(nameEvent);
		addTypeEvent(newType);
		setDateEvent(dateEvent);
		setCapacity(capacity);
		setAddress(address);
		// Her yeni nesne eklendiginde buraya da kaydedilir. extent sinif
		extent.add(this);
	}

	public Event(int idEvent, String nameEvent, String newType, LocalDate dateEvent, EventCapacity capacity, double budget, Address address, Integer ticketPrice ) {
	
		this(idEvent, nameEvent, newType, dateEvent, capacity, budget, address);
		this.setTicketPrice(ticketPrice);
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
			throw new IllegalArgumentException("Event must have at least one type.");
		}
		knownTypes.remove(eventType);
	}
	
	public static List<Event> getExtent() {
		return new ArrayList<Event>(extent);
	}

	//sinifmetodu - extent kulandim
	
	public static void showExtent(List<Event> extent) {
		System.out.println(extent);
	}
	
	public static void readExtent(){

		try{
		    FileInputStream readData = new FileInputStream("C:\\Users\\user\\Desktop\\mas_data.txt");
		    ObjectInputStream readStream = new ObjectInputStream(readData);

		    ArrayList<Event> readEvents = (ArrayList<Event>) readStream.readObject();
		    readStream.close();
		    System.out.println(readEvents.toString()); // print the new ArrayList to the console to see if all the data is loaded correctly.
		}catch (Exception e) {
		    e.printStackTrace();
		}
	
	}
	
	public static void writeExtent() {
		
		try{
		    FileOutputStream writeData = new FileOutputStream("C:\\Users\\user\\Desktop\\mas_data.txt");
		    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		    writeStream.writeObject(extent);
		    writeStream.flush();
		    writeStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
	} 
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public long getRemainingDay() {
		return remainingDay;
	}

	public long calculateRemainingDay(LocalDate dateEvent) {
		LocalDate today = LocalDate.now();
		remainingDay = ChronoUnit.DAYS.between(dateEvent, today);
		return remainingDay;
	}

	
	
	@Override
	public String toString() {
		return " Event [Event id : " + idEvent + " , EventName : " + nameEvent + ", Event type : " + typeEvent + ", Event date : " + dateEvent + ", Capacity : " + capacity + ", Budget : " + budget + ", Address : " + address.getStreet() +" " + address.getNumber()+ " - " +address.getCity() +" ]";
	}

	
	
	
	public static void main(String[] args) throws ParseException {

		LocalDate eventDate = LocalDate.of(2020, 12, 15);
		LocalDate eventDate1 = LocalDate.of(2021, 10, 5);
		LocalDate eventDate2 = LocalDate.of(2020, 5, 20);
		
		Address a1 = new Address("Kopernika", 8 , "Warsaw");
		Address a2 = new Address("Solec", 22 , "Gdansk");
		Address a3 = new Address("Afrikanski", 14 , "Krakow");

		Event event= new Event(1, "Culture Fest" , "Gala", eventDate, EventCapacity._300 , 7500 , a1);
		Event event1= new Event(2, "HighSchool Reunion" , "Reunion", eventDate1, EventCapacity._200 , 2500 , a2);
		Event event2= new Event(3, "Tech Summit2020" , "Conference", eventDate2, EventCapacity._500 , 4000 , a3);

		try {
			Address a4 = new Address(null, 14 , "Krakow");
			Event event3= new Event(4, "Stars Gala" , "Gala", eventDate2, EventCapacity._500 , 8000 , a4);

		} catch (Exception e) {
			System.err.println(e);
		}
		event.addTypeEvent("Party");
		event2.addTypeEvent("Seminar"); // Adding new event type
		event2.setTicketPrice(200);  // Setting value for optional attribute
		
		event.writeExtent();
		event.readExtent();
	
        event.showExtent(extent);
		System.out.println("Remaining date : " + event2.calculateRemainingDay(eventDate2));

	}	

}
