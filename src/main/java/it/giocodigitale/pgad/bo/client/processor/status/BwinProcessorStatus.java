package it.giocodigitale.pgad.bo.client.processor.status;

import it.giocodigitale.pgad.bo.client.sogei.stub.utils.CsvFileProcessorAbs;
import it.giocodigitale.pgad.bo.client.sogei.stub.Status;
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
public class BwinProcessorStatus extends CsvFileProcessorAbs {
    private String idPgad;
    //initialize the CSVParser object
    JSONObject response;
    JSONObject request;


    @Override
    public void process() {

        System.out.println(" starting BwinProcessorDormienti");
        //Create the CSVFormat object

        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        Status statusStub = new Status(pgadEndPoint);
        String status = "";

        try {
            for (CSVRecord record : parser) {
                System.out.println("--------------------------------------");
                System.out.println("######## start sending status ########");
                System.out.println("--------------------------------------");
                List<String> empData = new ArrayList<String>();
                idPgad = record.get(fields[0]);
                SimpleDateFormat traceDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
                String dateStartTrace = traceDateFormat.format(new Date());
                System.out.println("request : idPgad : " + idPgad + " operatorId : " + StubUtils.BWIN_ID);
                request = StubUtils.statusRequest(idPgad, StubUtils.BWIN_ID, prefix + new Date().getTime());
                response = statusStub.pgadRequestExe(request);
                String doneStartTrace = traceDateFormat.format(new Date());


                empData.add(idPgad);

                String statusResult = response.get("status").toString();
                empData.add(statusResult);


                String idProxyTransaction = response.get("idProxyTransaction").toString();
                empData.add(idProxyTransaction);
                String timeStamp = response.get("timeStamp").toString();
                empData.add(timeStamp);
                String idReceipt = response.get("idReceipt").toString();
                empData.add(idReceipt);
                String requestResult = response.get("requestResult").toString();
                empData.add(requestResult);



                String accountCodeResult = response.get("accountCode").toString();
                empData.add(accountCodeResult);

                empData.add(dateStartTrace);
                System.out.println("response : idProxyTransaction: " + idProxyTransaction +  "timeStamp: " + timeStamp + " idReceipt: " + idReceipt + " requestResult: " + requestResult + " dateStartTrace: " + dateStartTrace + " doneStartTrace: " + doneStartTrace);

                System.out.println("---------------------------------------");
                System.out.println("########### end sending Status ########");
                System.out.println("---------------------------------------");
                empData.add(doneStartTrace);


                printer.printRecord(empData);
                printer.flush();

            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public  String checkStatus(String idPgad, Status statusStub){
        JSONObject response;
        JSONObject request;

        String status = "";

        System.out.println("--------------------------------------");
        System.out.println("########## checking  status ##########");
        System.out.println("--------------------------------------");
        SimpleDateFormat traceDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        String dateStartTrace = traceDateFormat.format(new Date());
        System.out.println("request : idPgad : " + idPgad + " operatorId : " + StubUtils.BWIN_ID);
        request = StubUtils.statusRequest(idPgad, StubUtils.BWIN_ID, prefix + new Date().getTime());
        response = statusStub.pgadRequestExe(request);
        String doneStartTrace = traceDateFormat.format(new Date());


        String statusResult = response.get("status").toString();
        status = statusResult;




        System.out.println("---------------------------------------");
        System.out.println("########## end checking Status ########");
        System.out.println("---------------------------------------");
        return status;

    }
}
