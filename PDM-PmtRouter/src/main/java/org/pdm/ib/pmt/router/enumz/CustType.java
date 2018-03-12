package org.pdm.ib.pmt.router.enumz;

public enum CustType {
    INDIVIDUAL(1),
    LEGAL(2);

    private final int value;

    CustType(final int newValue){
        value = newValue;
    }

    public int getValue(){
        return value;
    }
}
