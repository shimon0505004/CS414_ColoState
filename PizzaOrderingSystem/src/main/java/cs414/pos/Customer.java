package cs414.pos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Customer implements Serializable {

	private String firstName;
	private String lastName;
	private String customerPhoneNumber;
	private int memberShipNumber;
	private int rewardsPoint;
	private Set<Order> customerOrders;
	private Set<Address> customerAddresses;
	
	public Customer(String firstName, String lastName, int memberShipNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setMemberShipNumber(memberShipNumber);
		setCustomerPhoneNumber("000-000-0000");
		initializeOrdersPointAddress();
	}

	public Customer(String firstName, String lastName, int memberShipNumber, String phoneNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setMemberShipNumber(memberShipNumber);
		setCustomerPhoneNumber(phoneNumber);
		initializeOrdersPointAddress();
	}

	private void initializeOrdersPointAddress(){
		setRewardsPoint(0);
		setCustomerOrders(new HashSet<Order>());
		setCustomerAddresses(new HashSet<Address>());
	}
	


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		if(firstName!=null){
			this.firstName = firstName;			
		}else{
			this.firstName = "";
		}
		
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		if(lastName!=null){
			this.lastName = lastName;			
		}else{
			this.lastName = "";
		}
	}

	/**
	 * @return the rewardsPoint
	 */
	public int getRewardsPoint() {
		return rewardsPoint;
	}

	/**
	 * @param rewardsPoint the rewardsPoint to set
	 */
	public void setRewardsPoint(int rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}

	/**
	 * @return the memberShipNumber
	 */
	public int getMemberShipNumber() {
		return memberShipNumber;
	}

	/**
	 * @param memberShipNumber the memberShipNumber to set
	 */
	public void setMemberShipNumber(int memberShipNumber) {
		this.memberShipNumber = memberShipNumber;
	}

	/**
	 * @return the customerPhoneNumber
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * @param customerPhoneNumber the customerPhoneNumber to set
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		if(customerPhoneNumber!=null){
			this.customerPhoneNumber = customerPhoneNumber;			
		}else{
			this.customerPhoneNumber = "000-000-0000";			
		}
	}

	/**
	 * @return the customerOrders
	 */
	public Set<Order> getCustomerOrders() {
		return customerOrders;
	}

	/**
	 * @param customerOrders the customerOrders to set
	 */
	public void setCustomerOrders(Set<Order> customerOrders) {
		this.customerOrders = customerOrders;
	}

	/**
	 * @return the customerAddresses
	 */
	public Set<Address> getCustomerAddresses() {
		return customerAddresses;
	}

	/**
	 * @param customerAddresses the customerAddresses to set
	 */
	public void setCustomerAddresses(Set<Address> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	/**
	 * 
	 * @param address adds a new customer address
	 */
	public boolean addNewAddress(Address address){
		int previousSize = getCustomerAddresses().size();
		getCustomerAddresses().add(address);
		if(previousSize == getCustomerAddresses().size()-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * 
	 * @param address removes a new customer address
	 */
	public boolean removeAddress(Address address){
		int previousSize = getCustomerAddresses().size();
		getCustomerAddresses().remove(address);
		if(previousSize == getCustomerAddresses().size()+1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addOrder(Order order){
		int previousSize = getCustomerOrders().size();
		getCustomerOrders().add(order);
		calculateCustomerRewardPoints();
		if(previousSize == getCustomerOrders().size()-1){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	public boolean removeOrder(Order order){
		int previousSize = getCustomerOrders().size();
		getCustomerOrders().remove(order);
		calculateCustomerRewardPoints();
		if(previousSize == getCustomerOrders().size()+1){
			return true;
		}
		else{
			return false;
		}		
	}
	
	private void calculateCustomerRewardPoints(){
		int accumulatedPoints = 0;
		for(Order customerOrder:getCustomerOrders()){
			accumulatedPoints+=customerOrder.getRewardPointGenerated();
		}
		setRewardsPoint(accumulatedPoints);
	}
	
}
