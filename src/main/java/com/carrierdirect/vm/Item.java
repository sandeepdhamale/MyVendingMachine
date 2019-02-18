package com.carrierdirect.vm;

/**
 * Items supported in current Vending Machine
 */
public enum Item {
    COKE(1,"Coke", 25), PEPSI(2,"Pepsi", 35), CHIPS(3,"Chips", 45);

    private int location;
    private String name;
    private int price;

    private Item(int location, String name, int price) {
        this.location = location;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}

