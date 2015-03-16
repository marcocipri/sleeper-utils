package it.giocodigitale.pgad.bo.client.processor.listadormienti;

import it.giocodigitale.pgad.bo.client.sogei.stub.ListaDormienti;
import it.giocodigitale.pgad.bo.client.sogei.stub.utils.CsvFileProcessorAbs;
import it.giocodigitale.pgad.bo.client.sogei.stub.utils.StubUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mcipri on 28/08/14.
 */
public class GdProcessorListaDormienti extends CsvFileProcessorAbs {
    private String idPgad;
    //initialize the CSVParser object
    JSONObject response;
    JSONObject request;

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public void setTill(int till) {
        this.till = till;
    }

    Date startDay;
    int till = 0;
int returnedNumberOfGamingAccounts = 0 ;
    int sequenceNumberOfGamingAccounts = 0;

    @Override
    public void process() {

        System.out.println(" starting GdProcessorListaDormienti");
        //Create the CSVFormat object

//        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        ListaDormienti listaDormientiStub = new ListaDormienti(pgadEndPoint);
        Date _startDay = new Date();
        _startDay.setTime(this.startDay.getTime());

        int count = 0;

        try {
            PrintWriter out = new PrintWriter(new FileWriter("BwinListaDormienti-2014-200.csv"));
            // for each day before the start date
            while (till > count) {
                System.out.println(" Iterazione " + count);
                // while the returned element is 100
                sequenceNumberOfGamingAccounts = 0;
                returnedNumberOfGamingAccounts = 0;
               // _startDay = StubUtils.addDays(_startDay, 1);
                count++;
               // while (returnedNumberOfGamingAccounts > 0) {
                do {
                _startDay = StubUtils.addDays(_startDay, 1);
                System.out.println("--------------------------------------");
                System.out.println("######## start sending listaDormienti ########");
                System.out.println("--------------------------------------");
                List<String> empData = new ArrayList<String>();
                SimpleDateFormat traceDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
                //String dateStartTrace = traceDateFormat.format(new Date());

                System.out.println("request : idPgad : " + idPgad + " operatorId : " + StubUtils.BWIN_ID + " returnedNumberOfGamingAccounts " + 1);
                request = StubUtils.listaDormientiRequest(_startDay, StubUtils.BWIN_ID, prefix + new Date().getTime(), 1);
                response = listaDormientiStub.pgadRequestExe(request);
                returnedNumberOfGamingAccounts=Integer.parseInt(response.get("numberOfGamingAccounts").toString());
                // get the account status
                JSONArray msg = (JSONArray) response.get("gamingAccountDetail");
                Iterator<JSONObject> iterator = msg.iterator();



                while (iterator.hasNext()) {
                    JSONObject factObj = (JSONObject) iterator.next();
                    String accountCode = (String) factObj.get("accountCode");
                    Long balanceAmount = (Long) factObj.get("balanceAmount");
                    System.out.println(factObj);
                    SimpleDateFormat printDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String printDate = printDateFormat.format(_startDay);

                    empData.add(accountCode);
                    empData.add(balanceAmount + "");
                    empData.add(printDate);
                    System.out.println(accountCode + ";" + balanceAmount + ";" + printDate);
                    out.println(accountCode + ";" + printDate + ";" +  balanceAmount);
                    out.flush();

                }

                //empData.add(dateStartTrace);
                //System.out.println("response : idProxyTransaction: " + idProxyTransaction + "timeStamp: " + timeStamp + " idReceipt: " + idReceipt + " requestResult: " + requestResult + " numberOfGamingAccounts " + numberOfGamingAccounts);

                System.out.println("---------------------------------------");
                System.out.println("########### end sending Status ########");
                System.out.println("---------------------------------------");
                //empData.add(doneStartTrace);



                } while(true);
                //out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
