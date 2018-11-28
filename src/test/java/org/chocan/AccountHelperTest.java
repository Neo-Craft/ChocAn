package org.chocan;

import org.chocan.common.AccountHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountHelperTest {


    @Test
    public void testCoordinateGetters(){
        //the hashedWord represent the ciphered string "test"
        final String hashedWord = "EE26B0DD4AF7E749AA1A8EE3C10AE9923F618980772E473F8819A5D4940E0DB27AC185F8A0E1D5F84F88BC887FD67B143732C304CC5FA9AD8E6F57F50028A8FF".toLowerCase();
        assertEquals(hashedWord, AccountHelper.generateHash("test"));

    }

}
