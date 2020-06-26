import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrganizersGroup {
	
	private Map<Integer, Organizer> idOrgQualifier = new HashMap<>();
	private ArrayList<Organizer> orgsList = new ArrayList<Organizer>();

	private int idOG;
	private String nameOG;
	private int capacity;
	
	public OrganizersGroup(int idOG, String nameOG, int capacity) {
		this.idOG = idOG;
		setNameOG(nameOG);
		setCapacity(capacity);
	}
	
	// when addOrganizer method is called, it is automatically creates a qualifier for it.
	public void addOrganizer(Organizer org) {
		if(org == null) {
			throw new IllegalArgumentException("Organizer can not be null");
		}
		if(!this.orgsList.contains(org)) {
			this.orgsList.add(org);
			this.addOrgQualifier(org);
		}
	}

	//qualifier add - called by addOrganizer method 
	public void addOrgQualifier(Organizer org) {
		if(!idOrgQualifier.containsKey(org.getIdOrg())) {
			idOrgQualifier.put(org.getIdOrg(), org);
			
			// reverse
			org.joinGroup(this);
			
		}
		
	}
	
	// remove organizer methods calls remove qualifier method too 
	public void removeOrganizer(Organizer org) {
		if(org == null) {
			throw new IllegalArgumentException("Organizer can not be null");
		}
		if(this.orgsList.contains(org)) {
			this.orgsList.remove(org);
			this.removeOrgQualifier(org);
		}
	}
	// qualifier remove
	public void removeOrgQualifier(Organizer org) {
		if(idOrgQualifier.containsKey(org.getIdOrg())) {
			idOrgQualifier.remove(org.getIdOrg());
			org.leaveGroup(this);
		}
	}
	
	// shows members of the group
	public String groupMembers() throws Exception {
		if(orgsList.isEmpty()) {
			throw new Exception("The group does not have any member.");
		}
		String info = "Group members : " ;
		for(Organizer o : orgsList) {
			info += o.getFullName() + " ";
		}
		return info;
	}
	

	public Organizer showGroupMemberByQualifier(int idOrg) throws Exception {
		if(!idOrgQualifier.containsKey(idOrg)) {
			throw new Exception("No organizer with the id in this group");
		}
		return idOrgQualifier.get(idOrg);
		
	}
	
	public ArrayList<Organizer> getOrgsList() {
		return orgsList;
	}
	
	public int getIdOG() {
		return idOG;
	}

	public String getNameOG() {
		return nameOG;
	}

	public void setNameOG(String nameOG) {
		if(nameOG == null || "".equals(nameOG)) {
			throw new IllegalArgumentException("Organizers group name can not be null or empty.");
		}
		this.nameOG = nameOG;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if(capacity < 0) {
			throw new IllegalArgumentException("Capacity of the group must be bigger than zero");
		}
		this.capacity = capacity;
	}

	
}
