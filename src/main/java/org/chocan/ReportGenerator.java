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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss a");
    private static final SimpleDateFormat fileDateFormat = new SimpleDateFormat("_" +
            "MM-dd-yyyy_HH-mm-ss");
    private String fileDirPath;
    private boolean appendToFile = false;

    public ReportGenerator(){
        String user_home = new File(System.getProperty("user.dir")).getAbsolutePath();
        fileDirPath = user_home + File.separator+ "Reports";
        File reports_dir = new File(fileDirPath + File.separator + "Member");
        if (!reports_dir.exists()) {
            if (!reports_dir.mkdirs()) {
                System.out.println("Failed to create Reports directory");
            }
        }
        File provider_dir = new File(fileDirPath + File.separator+ "Provider");
        if(!provider_dir.exists())
            if (!provider_dir.mkdirs()) {
                System.out.println("Failed to create Provider directory");
            }
        provider_dir = new File(fileDirPath + File.separator+ "EFT");
        if(!provider_dir.exists())
            if (!provider_dir.mkdirs()) {
                System.out.println("Failed to create EFT directory");
            }
    }

    public String sanitizeFilename(String inputName) {
        return inputName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
    }

    public String generateProviderReport(Provider provider) throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        final String filePath = fileDirPath + File.separator + "Provider" + File.separator + sanitizeFilename( provider.getName() + todayString) + ".txt";
        FileWriter write = new FileWriter(filePath, appendToFile);
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
        return filePath;
    }

    public String generateMemberReport(Member member) throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        final String filePath = fileDirPath + File.separator + "Member" + File.separator + sanitizeFilename(member.getName()) + todayString + ".txt";
        FileWriter write = new FileWriter(filePath, appendToFile);
        PrintWriter printReport = new PrintWriter(write);

        printReport.printf("Weekly Report for " + member.getName() + "%n");
        printReport.printf("ID No.: %d%n", member.getNumber());
        printReport.printf(member.getCoordinate().getStreetAddress() + "%n");
        printReport.printf(member.getCoordinate().getCity() + "%n");
        printReport.printf(member.getCoordinate().getState() + "%n");
        printReport.printf("%d%n", member.getCoordinate().getZipCode());

        ProviderDao providers = Database.PROVIDERS;
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
        return filePath;
    }

    public String generateEFTReport()throws IOException {
        Date today = new Date(System.currentTimeMillis());
        String todayString = fileDateFormat.format(today);
        final String filePath = fileDirPath + File.separator + "EFT"+File.separator+ "EFT_Summary" + todayString + ".txt";
        FileWriter write = new FileWriter(filePath, appendToFile);
        PrintWriter printReport = new PrintWriter(write);
        List<Provider> providers = Database.PROVIDERS.getAll();
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
        return filePath;
    }
}
