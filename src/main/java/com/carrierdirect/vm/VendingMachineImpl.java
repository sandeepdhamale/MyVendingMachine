package com.carrierdirect.vm;

// 
// TODO : Add Cash Inventory for managing coins and providing exact change

public class VendingMachineImpl implements VendingMachine,VendingMachineHardwareFunctions {
    private Inventory<Item> itemInventory = new Inventory<Item>();
//    private Inventory<Coin> cashInventory = new Inventory<Coin>();

    private long totalSales;
    private Item currentItem;
    private int currentBalance;

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize() {
        //initialize Vending Machine with 
        //  5 cans/packets of each Item
        for (Item i : Item.values()) {
            itemInventory.put(i, 5); 
        }
    }

    @Override
    public Integer buttonPress(Integer productPosition)
    {
    	int price=0;
    	Item item = lookupProductAtPostion(productPosition);
    	price=selectItemAndGetPrice(item);
    	this.showMessage("The price of "+ item.toString() +" is : " + price + " cents" );
 		return price;
    }
    
    public Integer selectItemAndGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        this.showMessage("Sold Out, Please buy another item");
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    @Override
    public void addUserMoney(Integer cents) {
        currentBalance = currentBalance + cents;
    }

    public Bucket<Item, Integer> collectItemAndChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        Integer change = collectChange();
        return new Bucket<Item, Integer>(item, change);
    }

    private Item collectItem() throws   NotFullyPaidException {
        if (isFullPaid()) {
                itemInventory.deduct(currentItem);
                dispenseProduct(currentItem.getLocation(),currentItem.getName()); // Default method from VendingMachineHardwareFunction
                return currentItem;
        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullyPaidException("Price not paid in Full, remaining : ", remainingBalance);
    }

    private Integer collectChange() {
        int changeAmount = currentBalance - currentItem.getPrice();
        dispenseChange(changeAmount); // Default method from VendingMachineHardwareFunction
        currentBalance = 0;
        currentItem = null;
        return changeAmount;
    }


    private boolean isFullPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        }
        return false;
    }

	@Override
	public void addMoreProduct(Integer productPosition, Integer productQty) {
		Item item = lookupProductAtPostion(productPosition);
		for (int i = 0; i < productQty; i++) {itemInventory.add(item);};
	}

	@Override
	public Integer checkProductQty(Integer productPosition) {
		Integer qty = 0;
		Item item = lookupProductAtPostion(productPosition);
        qty = itemInventory.getQuantity(item);
        return qty;
	}
	
    @Override
    public Integer refund() {
        Integer refund = currentBalance;
        currentBalance = 0;
        currentItem = null;
        this.showMessage("Refund Successful : Returning " + refund + " cents");
        return refund;
    }

    @Override
    public void reset() {
    	itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
    }

    public long getTotalSales() {
        return totalSales;
    }
    
	//Product Lookup function based on Product Position
     public Item lookupProductAtPostion(Integer productPosition)
    {
    	Item item = null;
    	switch(productPosition)
        {
            case 1 :
           	item= Item.COKE;
           	break;
            case 2 :
            item= Item.PEPSI;
           	break;
            case 3 :
            item = Item.CHIPS;
           	break;
        }
    	return item;
    }


}


