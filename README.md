# Coding assessment  

Design a Vending Machine in Java

**Use case 1:** As a user, I would like to be able to ﬁnd the price of a speciﬁc product. 
Workﬂow: 
1. User presses the button corresponding to slot 3 (`VendingMachine.buttonPress(3)` is called)
2. The name and price of that product is displayed. 

**Use case 2:** As a user, I would like to be able to purchase a product. 
Workﬂow: 
1. User adds a quarter (`VendingMachine.addUserMoney(25)` is called) 
2. User adds a quarter (`VendingMachine.addUserMoney(25)` is called) 
3. User presses the button corresponding to slot 31 (`VendingMachine.buttonPress(1)` is called)   
* Note: In this example, the product in slot 3 costs 45 cents. 
4. The product from slot 1 and a nickel is dispensed to the user.

 **Use case 3:** 
 As an admin, I would like to be able to add more product. Deﬁne and implement an interface method that can be called to add additional product.

 ## Notes & Assumptions 
 * All inputs are assumed to be valid. 
 * The VendingMachine does not contain an inﬁnite supply of products. 
 * For simplicity you do not need to keep track of the number of coins in the vending machine. 
 Additionally,  `VendingMachineHardwareFunctions.dispenseChange` has been provided to eliminate the need to calculate the type and quantity of coins used when making change.