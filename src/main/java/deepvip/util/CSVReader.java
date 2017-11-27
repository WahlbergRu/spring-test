package deepvip.util;

import deepvip.model.UserPredictionTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public UserPredictionTable CSVReader() {

        String csvFile = "assets/JobNumber_v2.csv";
        String line = "";
        String cvsSplitBy = ",";

        UserPredictionTable userPredictionTable = new UserPredictionTable();
        ArrayList<String> UniProt     = new ArrayList<String>();
        ArrayList<String> GeneName    = new ArrayList<String>();
        ArrayList<Number> Confidence  = new ArrayList<Number>();
        ArrayList<Number> Sensitivity = new ArrayList<Number>();
        ArrayList<Number> Specificity = new ArrayList<Number>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] csvLine = line.split(cvsSplitBy);

                if (i != 0){
                    UniProt.add(csvLine[0]);
                    GeneName.add(csvLine[1]);
                    Confidence.add(Float.parseFloat(csvLine[2]));
                    Sensitivity.add(Float.parseFloat(csvLine[3]));
                    Specificity.add(Float.parseFloat(csvLine[4]));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userPredictionTable
            .setUniProtID(UniProt)
            .setGeneName(GeneName)
            .setConfidence(Confidence)
            .setSensitivity(Sensitivity)
            .setSpecificity(Specificity);

    }
}
