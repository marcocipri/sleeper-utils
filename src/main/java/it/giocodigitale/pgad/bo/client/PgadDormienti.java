package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class PgadDormienti {


    public static void main(String[] args) {



        //Create the CSVFormat object
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');

        //initialize the CSVParser object
        CSVParser parser = null;
        try {
            parser = new CSVParser(new FileReader("employees.csv"), format);

            for (CSVRecord record : parser) {
                record.get("ID");
                record.get("Name");
                record.get("Role");
                record.get("Salary");
            }
            //close the processor
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }


}
