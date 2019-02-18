package com.carrierdirect.vm;

/*
 * Coins supported in this Vending Machine.
 */

public enum Coin {
	NICKLE(5), DIME(10), QUARTER(25);

	private int denomination;

    private Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}

