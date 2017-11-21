package deepvip.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {

        String csvFile = "/assets/JobNumber_v2.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("[UniProt= " + country[0] + " , Gene=" + country[1] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
