import java.io.Serializable;

public class Address  {

	
	private String street;
	private int number;
	private String city;
	
	public Address(String street, int number, String city) {
		this.setStreet(street);
		this.setNumber(number);
		this.setCity(city);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if(street == null || "".equals(street)) {
			throw new IllegalArgumentException("Street name can not be null or empty.");
		}
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if(city == null || "".equals(city)) {
			throw new IllegalArgumentException("City name can not be null or empty.");
		}
		this.city = city;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if(number == 0 || "".equals(number)) {
			throw new IllegalArgumentException("Building number can not be zero or empty.");
		}
		this.number = number;
	}
	
}
