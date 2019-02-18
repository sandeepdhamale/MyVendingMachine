package com.carrierdirect.vm;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class VendingMachineTest {

    private static VendingMachine vm;

    @BeforeClass
    public static void setUp() {
        vm = VendingMachineFactory.createVendingMachine();
    }

    @AfterClass
    public static void tearDown() {
        vm = null;
    }
    
    @Test
    public void testCheckOnlyPrice() {
    	/*
    	 * Use case 1
    	 *  As a user, I would like to be able to find the price of a specific product. 
    	 *  workflow
    	 *  1. User presses the button corresponding to slot 3 (VendingMachine.buttonPress(3) is called) 
    	 *  2. The name and price of that product is displayed.
    	 **/
    	System.out.println("** Test Started testCheckOnlyPrice **");
        //select item, price in cents
        long price = vm.buttonPress(3);
        //price should be equal to CHIPS price
        assertEquals(Item.CHIPS.getPrice(), price);
    }

    @Test
    public void testBuyItemWithMorePrice() {
    	/*
    	 * Use case 2
    	 * As a user, I would like to be able to purchase a product workflow
    	 *     	1. User adds a quarter (VendingMachine.addUserMoney(25) is called) 
    	 *      2. User adds a quarter (VendingMachine.addUserMoney(25) is called)
    	 *      3. User presses the button corresponding to slot 3 
    	 *      (VendingMachine.buttonPress(3) is called) 
    	 *      Note: In this example, the product in slot 3 costs 45 cents. 
    	 *      4. The product from slot 3 and a nickel is dispensed to the user. 
    	*/

    	System.out.println("** Test Started testBuyItemWithMorePrice **");
        long price = vm.buttonPress(3);
        assertEquals(Item.CHIPS.getPrice(), price);

        vm.addUserMoney(Coin.QUARTER.getDenomination());
        vm.addUserMoney(Coin.QUARTER.getDenomination());

        Bucket<Item, Integer> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        Integer change = bucket.getSecond();

        //should be CHIPS
        assertEquals(Item.CHIPS, item);
        //comparing change
        assertTrue(change==5);
        assertEquals(50 - Item.CHIPS.getPrice(),change.intValue());
    }
    
    @Test
    public void testAdminAddMoreProduct() {
    	/**
    	 * Use case 3:
    	 * As an administrator, I would like to be able to add more product.
    	 * Define and implement an interface method that can 
    	 * be called to add additional product 
    	 ***/
        System.out.println("** Test Started testAdminAddMoreProduct **");
        Integer quantity = vm.checkProductQty((Item.PEPSI.getLocation()));
        System.out.println("PRE : Quantity of PEPSI before Admin adds new items - " + quantity);
        //added 15 more items
        vm.addMoreProduct(Item.PEPSI.getLocation(), 15);

        int updatedQty = vm.checkProductQty(Item.PEPSI.getLocation());
        System.out.println("POST : Quantity of PEPSI after Admin adds new items - " + updatedQty);
        assertEquals( updatedQty , quantity + 15);
     }

    @Test
    public void testBuyItemWithExactPrice() {
    	/* 
    	 * buttonPress select Coke ( Position 1)
    	 * Add exact same price which is 25 Cents in this case
    	 * CollecItem and Change and validate you have right item and no change
    	 */
        System.out.println("** Test Started testBuyItemWithExactPrice **");

        //select item, price in cents
        long price = vm.buttonPress(Item.COKE.getLocation());
        //price should be equal to Coke's price
        assertEquals(Item.COKE.getPrice(), price);
        //25 cents paid
        vm.addUserMoney(Coin.QUARTER.getDenomination());

        Bucket<Item, Integer> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        Integer change = bucket.getSecond();

        //should be Coke
        assertEquals(Item.COKE, item);
        //there should not be any change
        assertTrue(change==0);
    }

 
    @Test(expected = SoldOutException.class)
    public void testSoldOut() {
    	/*
    	 * Iterate over more number of times than the qty of products available
    	 */
        System.out.println("** Test Started testSoldOut **");
        for (int i = 0; i < 8; i++) {
            vm.buttonPress(Item.COKE.getLocation());
            vm.addUserMoney(Coin.QUARTER.getDenomination());
            vm.collectItemAndChange();
        }
    }

    
   @Test(expected = SoldOutException.class)
    public void testReset() {
   	/*
   	 *  Use Admin function RESET and try to buy something, should give appropriate message
   	 */
        System.out.println("** Test Started testReset **");

        VendingMachine vmachine = VendingMachineFactory.createVendingMachine();
        vmachine.reset();
        vmachine.buttonPress(Item.COKE.getLocation());

    }
   
   @Test
   public void testRefund() {
       System.out.println("** Test Started testRefund **");
       long price = vm.buttonPress(Item.PEPSI.getLocation());
       assertEquals(Item.PEPSI.getPrice(), price);
       vm.addUserMoney(Coin.DIME.getDenomination());
     //  vm.addUserMoney(Coin.NICKLE.getDenomination());
       vm.addUserMoney(Coin.QUARTER.getDenomination());
       // refund exact same amount as much you put in.
       assertEquals(35, vm.refund().intValue());
      // assertEquals(40, vm.refund().intValue());
   }
   
     
}


