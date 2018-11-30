package org.chocan;

import org.chocan.common.AccountHelper;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Member;
import org.chocan.entities.Provider;
import org.chocan.entities.Service;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;

public class DatabaseTest {

    private int maxMembers = 10;        //Set number of Members to fill Database
    private int maxProviders = 10;      //Set number of Providers to fill Database
    private int maxServices = 10;       //Set number of Services to fill Database

    private int getRandNum(int min, int max){
        return ((int) Math.floor((Math.random()*(max - min))) + min);
    }

    private float getRanFloat(float min, float max) {
        return ((float) (Math.random()*(max - min))) + min;
    }

    private void printMemberInfo(Member member) {
        if (member == null)
            System.out.print("Null Member\n\n");
        else {
            System.out.print("Member #" + member.getNumber() + ": " + member.getName() + "\n");
            System.out.print("Address: "
                    + member.getCoordinate().getStreetAddress() + ", "
                    + member.getCoordinate().getCity() + ", "
                    + member.getCoordinate().getState() + ", "
                    + member.getCoordinate().getZipCode() + ".\n");
            System.out.print("Status: " + member.isSuspended() + "\n\n");
        }
    }

    private void printProviderInfo(Provider provider) {
        if(provider == null)
            System.out.print("Null Provider\n\n");
        else {
            System.out.print("Provider #" + provider.getNumber() + ": " + provider.getName() + "\n");
            System.out.print("Address: "
                    + provider.getCoordinate().getStreetAddress() + ", "
                    + provider.getCoordinate().getCity() + ", "
                    + provider.getCoordinate().getState() + ", "
                    + provider.getCoordinate().getZipCode() + "."
                    + "\n\n");

            //Print services provided

        }
    }

    private void printServiceInfo(Service service) {
        if(service == null)
            System.out.print("Null Service\n\n");
        else {
            System.out.print("Service #" + service.getServiceCode() + ": " + service.getServiceName() + "\n");
            System.out.print("Service Provider #" + service.getProviderId() + ": " + Database.PROVIDERS.get(service.getProviderId()).orElse(null).getName() + "\n");
            System.out.print("Date of service: " + service.getServiceDate() + "\n");
            System.out.print("Date of service recieved: " + service.getReceiveDate() + "\n");
            System.out.print("Service cost: $" + service.getPaidFee() + "\n");
            System.out.print("\n");
        }
    }

    private void printProviderServices(Provider provider) {
        printProviderInfo(provider);
        System.out.print(("Provided services to Member(s):\n"));
            ConcurrentHashMap<Member, ArrayList<Service>> services = provider.getServices();
            Iterator<Member> it = services.keySet().iterator();
            while(it.hasNext()){
                Member member = it.next();
                System.out.print("\t*Member #" + member.getNumber() + ": "
                            + member.getName() + "\n");
        }
                System.out.print("\n");
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

        String providers[] = {
                "Kaiser", "Providence", "Universal Health Clinic", "Health of America",
                "St. Jude Hospital", "Mercy Hospital", "Lakeview Hospital", "Mayo Clinic",
                "St. Hood Hospital", "NorthWest Clinic", "Western Hospital", "Salt Lake Clinic",
                "Jesus Hospital", "Justice Clinic", "Heartfelt Hospital", "Fortmill Hospital"
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

        String services[] = {
                "Acupuncture", "Physcology", "Therapy", "Family therapy",
                "Detox", "Inpatient rehabilitation", "Sober house", "Relaspe therapy",
                "Recovery group meeting", "Outpatient rehabilitation",
        };

        //Load the stored Database
        Database.load();
        //Create and put Member objects into Database
        for(int i = 0; i < maxMembers; i++) {
            String Addr = getRandNum(100,9999) + " " + adresses[getRandNum(0,adresses.length)];
            Coordinate cord = new Coordinate(Addr, cities[getRandNum(0, cities.length)], states[getRandNum(0, states.length)], getRandNum(10000,99999));
            Member member = new Member(names[getRandNum(0, names.length-1)], i, cord, (1==getRandNum(0,3)));
            Database.MEMBERS.add(member);
        }

        //Create and put Provider objects into Database
        for(int i = 0; i < maxProviders; i++) {
            String Addr = getRandNum(100,9999) + " " + adresses[getRandNum(0,adresses.length)];
            Coordinate cord = new Coordinate(Addr, cities[getRandNum(0, cities.length)], states[getRandNum(0, states.length)], getRandNum(10000,99999));
            Provider provider = new Provider(providers[getRandNum(0, providers.length)], i, cord, AccountHelper.generateHash( AccountHelper.KEY + "test"));
            Database.PROVIDERS.add(provider);
        }

        //Create and put Service objects into Database
        for(int i = 0; i< maxServices; i++) {
            Date date = new Date(getRandNum(110,118), getRandNum(0, 11), getRandNum(1,31));

            int serviceProvider = getRandNum(0, maxProviders);
            Service service = new Service(date, serviceProvider, i, getRanFloat(0f, 999.99f), services[getRandNum(0 , services.length)]);
            Database.SERVICES.add(service);

            //Add service to provider
            Provider provider = Database.PROVIDERS.get(serviceProvider).orElse(null);
            Member member = Database.MEMBERS.get(getRandNum(0, maxMembers)).orElse(null);
            provider.addService(member, service);
            Database.PROVIDERS.update(provider);
        }
        //Save the new Database
        Database.save();

        //Print Member object information stored in Database.
        System.out.print("*** MEMBERS ***\n\n");
        for(int i = 0; i < maxMembers; i++) {
            Member member = Database.MEMBERS.get(i).orElse(null);
            printMemberInfo(member);
        }

        //Print Provider object information stored in Database
        System.out.print("*** PROVIDERS ***\n\n");
        for(int i = 0; i < maxProviders; i++) {
            Provider provider = Database.PROVIDERS.get(i).orElse(null);
            printProviderInfo(provider);
        }

        //Print Service object information stored in Database
        System.out.print("*** SERVICES ***\n\n");
        for(int i = 0; i < maxServices; i++) {
            Service service = Database.SERVICES.get(i).orElse(null);
            printServiceInfo(service);
        }

        //Print services provided by a Provider object.
        System.out.print("***SERVICES PROVIDED BY A PROVIDER***\n\n");
        for(int i = 0; i < maxProviders; i++) {
            Provider provider = Database.PROVIDERS.get(i).orElse(null);
            printProviderServices(provider);
        }
    }
}
