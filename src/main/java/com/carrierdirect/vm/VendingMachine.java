package com.carrierdirect.vm;

/**
 * API Functions for Vending Machine
 *
 */
public interface VendingMachine {

	/**  User Function :
	 * This is called when a user presses a button for a particular product. 
	 * This is used for both price checking and purchasing.     
	 * @return */    
	
	public Integer buttonPress(Integer productPosition);   
	
	/**  User Function :
	 *   This is called when the user adds money to the machine. The cents parameter represent the value   
	 *   of the particular currency added to the machine. 
	 *   For example, when the user adds a Nickel, this function will be  
	 *   * called with a value of 5.     * 
	 *   <p>     * Note: Only one coin will be added at a time. 
	 *    Only Nickels, Dimes, and Quarters will be added.     */  
	
	public void addUserMoney(Integer cents); 

	// Additional Functions added for easier collection of item and change.
    public Bucket<Item, Integer> collectItemAndChange(); 
	public Integer refund();
    
   // Administrative methods to add more products and manage inventory
    public void addMoreProduct(Integer productPosition, Integer productQty);
    public Integer checkProductQty(Integer productPosition);
    //Added for Tests (Master Reset) -- Administrative function
    public void reset();
}
