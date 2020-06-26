import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Organizer {

	// association class
	private ArrayList<OrganizesWhen> organizesWhen = new ArrayList<OrganizesWhen>() ;
	// organizers group list
	private ArrayList<OrganizersGroup> orgGroup = new ArrayList<OrganizersGroup>();
	
	private int idOrg;
	private String fullName;
	private Address address;
	private LocalDate dob;

	
	public Organizer(int idOrg, String fullName, Address address, LocalDate dob) {
		super();
		this.idOrg =idOrg;
		setDob(dob);
		setAddress(address);
		setFullName(fullName);
	}
	 
	//adding a association class - here it creates an OrganizeWhen table automatically
	public void organize(Event event, LocalDate dateEvent) {
		if(event == null || dateEvent == null) {
			throw new IllegalArgumentException("Event or date event can not be null");
		}
		if(!organizesWhen.contains(event)) {
			organizesWhen.add(new OrganizesWhen(this, event, dateEvent));
			// reverse connection
			event.setDateEvent(dateEvent);
		}
		
	}
	
	// reverse - joining a group - qualified association
	public void joinGroup(OrganizersGroup og) {
		if(og == null || orgGroup.contains(og)) {
			throw new IllegalArgumentException("Group can not be null or Organizer is already a member of this group");
		}
		orgGroup.add(og);
		
	}
	//reverse - leaving the group
	public void leaveGroup(OrganizersGroup og) {
		if(og == null || !orgGroup.contains(og)) {
			throw new IllegalArgumentException("Group can not be null or Organizer is not a member of this group");
		}
		orgGroup.remove(og);
	}
	
	public String showGroups() {
		String info = "Group list : ";
		for(OrganizersGroup og : orgGroup) {
			info += " " + og.getNameOG() ;
			}
		return info ;
	}
	
	public int getIdOrg() {
		return idOrg;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		if(fullName == null || "".equals(fullName)) {
			throw new IllegalArgumentException("Organizer name can not be null or empty.");
		}
		this.fullName = fullName;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		int age = LocalDate.now().getYear() - dob.getYear();
		if(age < 18 || dob == null) {
			throw new IllegalArgumentException(" Organizer must be older than 18 and Birth date can not be null. ");
		}
		this.dob = dob;
	}
	

	@Override
	public String toString() {
		return "Organizer : " + fullName + " dob " + dob + " address " +  address.getStreet() +" " + address.getNumber()+ " - " +address.getCity() ;
	}
}
