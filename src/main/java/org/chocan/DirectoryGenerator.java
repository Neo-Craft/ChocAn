package org.chocan;

import org.chocan.entities.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DirectoryGenerator {

    private String fileDirPath;
    private boolean appendToFile = false;

    public DirectoryGenerator(){
        String user_home = new File(System.getProperty("user.dir")).getAbsolutePath();
        fileDirPath = user_home + File.separator + "Provider Directories";
        File provDir_dir = new File(fileDirPath);
        if (!provDir_dir.exists()) {
            if (!provDir_dir.mkdirs()) {
                System.out.println("Failed to create Provider Directories directory");
            }
        }
    }

    public String generateProviderDirectory() throws IOException {
        final String filePath = fileDirPath + File.separator + "Provider Directory.txt";
        FileWriter write = new FileWriter(filePath, appendToFile);
        PrintWriter printReport = new PrintWriter(write);

        printReport.printf("Provider Directory%n%n");
        printReport.printf("%-50s%s%n", "Service", "Service Code");
        printReport.printf("====================================================%n");
        List<Map.Entry<Integer, String>> entryList = Service.serviceDirectory.entrySet().stream()
                .sorted((e1, e2) -> e1.getValue().compareToIgnoreCase(e2.getValue()))
                .collect(Collectors.toList());
        for (Map.Entry<Integer, String> entry: entryList) {
            printReport.printf("%-50.45s%d%n", entry.getValue(), entry.getKey());
        }
        printReport.close();
        return filePath;
    }
}
