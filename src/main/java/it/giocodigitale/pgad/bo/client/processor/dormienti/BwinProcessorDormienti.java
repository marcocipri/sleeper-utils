package it.giocodigitale.pgad.bo.client.processor.dormienti;

import it.giocodigitale.pgad.bo.client.processor.status.BwinProcessorStatus;
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
public class BwinProcessorDormienti extends CsvFileProcessorAbs {
    private String idPgad;
    private String lastLogin;
    private String cashAmount;
    //initialize the CSVParser object
    JSONObject response;
    JSONObject request;


    @Override
    public void process() {

        System.out.println(" starting BwinProcessorDormienti");
        //Create the CSVFormat object

        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        Dormiente dormiente = new Dormiente(pgadEndPoint);
        Status statusStub = new Status(pgadEndPoint);
        BwinProcessorStatus bwinProcessorStatus = new BwinProcessorStatus();

        try {
            for (CSVRecord record : parser) {

                idPgad = record.get(fields[0]);
                lastLogin = record.get(fields[1]);
                cashAmount = record.get(fields[2]).replaceAll("\\.","").replaceAll(",","").trim();
                String status = bwinProcessorStatus.checkStatus(idPgad, statusStub);
                System.out.println("--------------------------------------");
                System.out.println("status  idPgad  " + idPgad + " : " + status);
                System.out.println("--------------------------------------");

                if (status.equalsIgnoreCase("1") || status.equalsIgnoreCase("2")|| status.equalsIgnoreCase("3")) {


                    System.out.println("--------------------------------------");
                    System.out.println("############ start sending ###########");
                    System.out.println("--------------------------------------");
                    List<String> empData = new ArrayList<String>();
                    SimpleDateFormat inDateFormat = new SimpleDateFormat("dd/mm/yy");
                    SimpleDateFormat traceDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
                    Date dateLastLogin = inDateFormat.parse(lastLogin);
                    String dateStartTrace = traceDateFormat.format(new Date());
                    System.out.println("request : idPgad : " + idPgad + " last login : " + dateLastLogin + " value : " + cashAmount);
                    System.out.println("request : idPgad : " + idPgad + " last login : " + dateLastLogin + " value : " + cashAmount);

                    request = StubUtils.dormientiRequest(idPgad, StubUtils.BWIN_ID, prefix + new Date().getTime() , dateLastLogin, Integer.parseInt(cashAmount));
                    response = dormiente.pgadRequestExe(request);
                    String doneStartTrace = traceDateFormat.format(new Date());

                    empData.add(idPgad);
                    String idProxyTransaction = response.get("idProxyTransaction").toString();
                    empData.add(idProxyTransaction);
                    String operatorId = response.get("operatorId").toString();
                    empData.add(operatorId);
                    String timeStamp = response.get("timeStamp").toString();
                    empData.add(timeStamp);
                    String idReceipt = response.get("idReceipt").toString();
                    empData.add(idReceipt);
                    String idTransaction = response.get("idTransaction").toString();
                    empData.add(idTransaction);
                    String requestResult = response.get("requestResult").toString();
                    empData.add(requestResult);

                    empData.add(dateStartTrace);
                    System.out.println("response : idProxyTransaction: " + idProxyTransaction + " operatorId: " + operatorId + "timeStamp: " + timeStamp + " idReceipt: " + idReceipt + " idTransaction: " + idTransaction + " requestResult: " + requestResult + " dateStartTrace: " + dateStartTrace + " doneStartTrace: " + doneStartTrace);

                    System.out.println("---------------------------------------");
                    System.out.println("############# end sending #############");
                    System.out.println("---------------------------------------");
                    empData.add(doneStartTrace);


                    printer.printRecord(empData);
                    printer.flush();
                }
                else
                {
                    System.out.println("--------------------------------------");
                    System.out.println("report as already inactive user   " + idPgad + " : " + status);
                    System.out.println("--------------------------------------");


                    List<String> empData = new ArrayList<String>();


                    SimpleDateFormat traceDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");

                    String dateStartTrace = traceDateFormat.format(new Date());

                    String doneStartTrace = traceDateFormat.format(new Date());


                    empData.add("inactive");
                    empData.add(idPgad);
                    empData.add("status");
                    empData.add(status);

                    empData.add(dateStartTrace);
                    empData.add(doneStartTrace);


                    printer.printRecord(empData);
                    printer.flush();


                }

            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
