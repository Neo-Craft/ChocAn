package org.chocan;

import org.chocan.dao.ProviderDao;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Provider;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DaoTest {

    @Test
    public void testDaoServiceInsert(){
        ProviderDao providers = new ProviderDao();
        Provider sampleProvider = new Provider("Kaiser Chocolate",
                101,
                new Coordinate("Bruce Lane", "Tigard", "OR", 97008)
        );
        Provider fakeProvider = new Provider("I Don't Exist",
                666,
                new Coordinate("No where", "Saint Thomas", "Virgin Islands", 801)
        );

        providers.add(sampleProvider);
        assertTrue(providers.get(sampleProvider.getGID()).isPresent());
        assertFalse(providers.get(fakeProvider.getGID()).isPresent());


    }

}
