package org.chocan;

import org.chocan.Database;
import org.chocan.dao.MemberDao;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Member;
import org.junit.Test;
import org.omg.CORBA.DATA_CONVERSION;

public class DatabaseTest {

    private int maxMembers = 10;

    private int getRandNum(int min, int max){
        return ((int) (Math.random()*(max - min))) + min;
    }

    private void printMemberInfo(Member member) {
        if (member == null)
            System.out.print("Null Member");
        else{
            System.out.print("#" + member.getNumber() + ": " + member.getName() + "\n");
            System.out.print("Address: "
                    + member.getCoordinate().getStreetAddress() + ", "
                    + member.getCoordinate().getCity() + ", "
                    + member.getCoordinate().getState() + ", "
                    + member.getCoordinate().getZipCode() + "."
                    + "\n\n");
        }
    }

    @Test
    public void testDatabase() {
        String names[] = {
                "Chris", "Jacob", "Michael", "Sandy", "Tom", "Cindy", "Jimbo",
                "Nick", "Jack", "Sarah", "Tyrone", "Carol", "Shary", "Stephanie",
                "Timmothy", "John", "Rachael", "Andrew", "Andy", "Michelle", "Lilly",
                "Brian", "Bree", "Charlotte", "Christopher", "Ian", "Natalie", "Nathan",
                "Darren", "Brittany", "Sarah", "Zoe", "Elizabeth", "Fransico", "Gerald",
                "Terry", "Molly", "Fred", "Marlo", "Veronica", "Winston", "Sandy", "Mario"
        };

        String adresses[] = {
                "NW Sparrow Road", "Saint Street", "Oak Terrace", "SE John Street", "Barlow Road",
                "SW Beat Street", "North Barrel Avenue", "South Kirtus Road", "West Hood Street",
                "NE Deak Steet", "East Jurk Road", "Earth Worm Avenue", "West Chicken Street",
                "North Bull Terrace", "SW Jangle Road", "NE Babo Street", "Kent Avenue", "Kosh Street"
        };

        String cities[] = {
                "Gundam", "Mallard", "Dallas", "Salem", "Salmon", "Arlo", "Jesupe", "Cuda", "Kerro",
                "Barnen", "Kelpfield", "Tengi", "Lalapope", "Insata", "Yerner", "Vernen", "Vitsburgh"
        };

        String states[] = {
                "Oregon", "Texas", "Nevada", "Idaho", "California", "Florida", "Washington", "Alaska",
                "Vermont", "North Dakota", "South Dakota", "New Mexico", "Arizona", "Kentucky", "Iowa"
        };

        Database.load();
        for(int i = 0; i < maxMembers; i++) {
            String Addr = getRandNum(1000,5000) + " " + adresses[getRandNum(0,adresses.length)];
            Coordinate cord = new Coordinate(Addr, cities[getRandNum(0, cities.length)], states[getRandNum(0, states.length)], getRandNum(8000,9999));
            Member member = new Member(names[getRandNum(0, names.length-1)], i, cord, false);
            Database.MEMBERS.add(member);
        }
        Database.save();

        for(int i = 0; i < maxMembers; i++) {
            Member member = Database.MEMBERS.get(i).orElse(null);
            printMemberInfo(member);
        }
    }
}

