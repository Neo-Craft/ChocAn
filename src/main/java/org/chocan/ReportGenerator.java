package org.chocan;

import org.chocan.dao.ProviderDao;
import org.chocan.entities.Member;
import org.chocan.entities.Service;
import org.chocan.entities.Provider;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportGenerator {

    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-DD-yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-DD-yyyy hh:mm:ss a");
    private static final SimpleDateFormat fileDateFormat = new SimpleDateFormat("_MM-DD-yyyy_HH:mm:ss");
    private String path;
    private boolean appendToFile = false;

    public ReportGenerator(){
        String user_home = new File(System.getProperty("user.home")).getAbsolutePath();
        path = user_home + "/Reports";
        File reports_dir = new File(path + "\\Member\\..\\Provider\\..\\EFT");
        if (!reports_dir.exists()) {
            if (!reports_dir.mkdirs()) {
                System.out.println("Failed to create Reports directory");
            }
        }
    }

    public void generateProviderReport(Provider provider) throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        FileWriter write = new FileWriter(path + "/Provider/" + provider.getName() + todayString + ".txt", appendToFile);
        PrintWriter printReport = new PrintWriter(write);

        printReport.printf("Weekly Report for " + provider.getName() + "%n");
        printReport.printf("ID No.: %d%n", provider.getNumber());
        printReport.printf(provider.getCoordinate().getStreetAddress() + "%n");
        printReport.printf(provider.getCoordinate().getCity() + "%n");
        printReport.printf(provider.getCoordinate().getState() + "%n");
        printReport.printf("%d%n", provider.getCoordinate().getZipCode());

        Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        for(Map.Entry<Member, ArrayList<Service>> entry: provider.getServices().entrySet()){
           for(Service service: entry.getValue()){
               if(service.getServiceDate().after(oneWeekAgo)){
                   printReport.printf("%n==================================================%n");
                   printReport.printf("Date of Service: " + dateFormat.format(service.getServiceDate()) + "%n");
                   printReport.printf("Date Received:   " + dateTimeFormat.format(service.getReceiveDate()) + "%n");
                   printReport.printf("Member Name:     " + entry.getKey().getName() + "%n");
                   printReport.printf("Member ID No.:   %d%n", entry.getKey().getNumber());
                   printReport.printf("Service Code:    %d%n", service.getServiceCode());
                   printReport.printf("Fee to be paid:  $%.2f%n", service.getPaidFee());
               }
           }
        }
        printReport.printf("%n==================================================%n");
        printReport.printf("Total Weekly Consultations: %d%n", provider.totalWeeklyConsultations());
        printReport.printf("Total Fees to be Paid:      $%.2f%n", provider.totalWeeklyFees());
        printReport.close();
    }

    public void generateMemberReport(Member member) throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        FileWriter write = new FileWriter(path + "/Member/" + member.getName() + todayString + ".txt", appendToFile);
        PrintWriter printReport = new PrintWriter(write);

        printReport.printf("Weekly Report for " + member.getName() + "%n");
        printReport.printf("ID No.: %d%n", member.getNumber());
        printReport.printf(member.getCoordinate().getStreetAddress() + "%n");
        printReport.printf(member.getCoordinate().getCity() + "%n");
        printReport.printf(member.getCoordinate().getState() + "%n");
        printReport.printf("%d%n", member.getCoordinate().getZipCode());

        ProviderDao providers = new ProviderDao();
        Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        for(Service service: member.getServices()){
            if(service.getServiceDate().after(oneWeekAgo)){
                printReport.printf("%n==================================================%n");
                printReport.printf("Date of Service: " + dateFormat.format(service.getServiceDate()) + "%n");
                Provider provider = providers.get(service.getProviderId()).orElse(null);
                if(provider != null) {
                    printReport.printf("Provider Name:   " + provider.getName() + "%n");
                }
                printReport.printf("Service Name:    " + service.getServiceName() + "%n");
            }
        }
        printReport.printf("%n==================================================%n");
        printReport.close();
    }

    public void generateEFTReport()throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        FileWriter write = new FileWriter(path + "/EFT/EFT_Summary" + todayString + ".txt", appendToFile);
        PrintWriter printReport = new PrintWriter(write);
        ProviderDao providerDao = new ProviderDao();
        List<Provider> providers = providerDao.getAll();
        double totalFees = 0.0;
        int totalConsultations = 0;

        printReport.printf("Weekly EFT Summary Report%n");
        for(Provider provider: providers){
            int providerConsultations = provider.totalWeeklyConsultations(); // TODO: implement this function to count consultations
            if(providerConsultations > 0) {
                double providerFees = provider.totalWeeklyFees(); // TODO: implement this function to calculate fees

                printReport.printf("%n==================================================%n");
                printReport.printf("Provider Name:           " + provider.getName() + "%n");
                printReport.printf("Consultations This Week: %d%n", providerConsultations);
                totalFees += providerFees;
                totalConsultations += providerConsultations;
            }
        }
        printReport.printf("%n==================================================%n");
        printReport.printf("Total Weekly Consultations: %d%n", totalConsultations);
        printReport.printf("Total Weekly Fees:          $%.2f%n", totalFees);

        printReport.close();
    }
}
