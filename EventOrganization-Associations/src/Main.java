import java.time.LocalDate;

public class Main {

	public static void main(String[] args) throws Exception {
		

		LocalDate rd = LocalDate.of(2019, 4, 12); // Release Date
		LocalDate rd1 = LocalDate.of(2019, 6, 24); // Release Date
		LocalDate d1 = LocalDate.of(1978, 10, 10); // Organizer date of birth	
		LocalDate ed1 = LocalDate.of(2021, 10, 10); // Event date

		
		Address a1 = new Address("Kopernika", 8 , "Warsaw");
		Address a2 = new Address("Solec", 22 , "Gdansk");
		Address a3 = new Address("Afrikanski", 14 , "Krakow");

		Event event= new Event(1, "Culture Fest" , "Gala", rd, EventCapacity._300 , 7500 , a1);
		Event event1= new Event(2, "HighSchool Reunion" , "Reunion", rd, EventCapacity._200 , 2500 , a2);
		Event event2= new Event(3, "Tech Summit2020" , "Conference",  rd1 , EventCapacity._500 , 4000 , a3);

		
		event.addTypeEvent("Seminar"); // Adding new event type
		event1.setTicketPrice(200);  // Setting value for optional attribute
		event2.setTicketPrice(100);  // Setting value for optional attribute

		// basic many-to-many
		Support sp1 = new Support(101, "Marketing" , 15000.0 , "Michael Jordan");
		Support sp2 = new Support(202, "Promotion" , 7000.0, "Jackie Chan");

		event1.addSupport(sp1);
		event2.addSupport(sp2);
		
		
		//print support list for the event
		System.out.println(event1.listSupport());
		System.out.println(sp1.getNameSponsor() +" supports " + sp1.getSupportedEvent());
		// Event.showExtent();
		
		//Accessing to a private constructor
		Activity act1 = Activity.createActivity("Games", event2);
		

		Organizer org1 = new Organizer(11, "Sara Nora" , a1 , d1);
		Organizer org2 = new Organizer(22, "Ansel Elgort" , a2 , d1);

		org1.organize(event1, ed1); // organize an event - this method creates association class
		
		OrganizersGroup og1 = new OrganizersGroup(111, "Stars" , 50); 
		OrganizersGroup og2 = new OrganizersGroup(222, "Stars" , 20); 

		// Adding organizer to the group and it will create qualifier.
		og1.addOrganizer(org1);
		og1.addOrganizer(org2);
		
		// Show group members
	    // System.out.println(og1.showGroupMember(org1.getIdOrg()));
		System.out.println(org1.showGroups());
		
		// Show members of the group
		System.out.println(og1.groupMembers());
		//Shows the group member of defined group by specified id
		System.out.println("Group 1 member by specified id : " + og1.showGroupMemberByQualifier(org2.getIdOrg()));
		
		// check if date is set for event1
		System.out.println("Event date : " + event1.getDateEvent());
		
		System.out.println("Total budget for all events is : " + Event.totalBudget());
		System.out.println(act1.toString());
	
	}

}
