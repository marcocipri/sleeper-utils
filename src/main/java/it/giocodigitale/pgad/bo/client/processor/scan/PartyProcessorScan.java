package it.giocodigitale.pgad.bo.client.processor.scan;

import it.giocodigitale.pgad.bo.client.processor.status.PartyProcessorStatus;
import it.giocodigitale.pgad.bo.client.sogei.stub.Dormiente;
import it.giocodigitale.pgad.bo.client.sogei.stub.Status;
import it.giocodigitale.pgad.bo.client.sogei.stub.utils.CsvFileProcessorAbs;
import it.giocodigitale.pgad.bo.client.sogei.stub.utils.StubUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mcipri on 28/08/14.
 */
public class PartyProcessorScan extends CsvFileProcessorAbs {
    private String idPgad;
    private String lastLogin;
    private String cashAmount;
    //initialize the CSVParser object
    JSONObject response;
    JSONObject request;


    @Override
    public void process() {

        System.out.println(" starting GdProcessorDormienti");
        //Create the CSVFormat object

        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        Dormiente dormiente = new Dormiente(pgadEndPoint);
        Status statusStub = new Status(pgadEndPoint);
        PartyProcessorStatus partyProcessorStatus = new PartyProcessorStatus();


        try {
            for (CSVRecord record : parser) {

                idPgad = record.get(fields[0]);
                lastLogin = record.get(fields[1]);
                cashAmount = record.get(fields[2]).replaceAll("\\.","").replaceAll(",","").trim();
                String status = partyProcessorStatus.checkStatus(idPgad, statusStub);
                System.out.println("--------------------------------------");
                System.out.println("status  idPgad  " + idPgad + " ; " + status + ";" + cashAmount);
                System.out.println("--------------------------------------");


            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
