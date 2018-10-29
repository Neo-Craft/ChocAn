package org.chocan;

import org.chocan.entities.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {


    @Test
    public void testCoordinateGetters(){
        Coordinate myCordinate = new Coordinate("Bruce Lane", "Tigard", "OR", 97008);
        assertEquals("Bruce Lane", myCordinate.getStreetAddress());
        assertEquals(97008, myCordinate.getZipCode());

    }

}
