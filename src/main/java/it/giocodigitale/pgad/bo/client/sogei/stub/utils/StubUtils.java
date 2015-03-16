package it.giocodigitale.pgad.bo.client.sogei.stub.utils;

import org.apache.http.client.methods.HttpPost;
import org.apache.commons.lang.time.DateUtils;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class StubUtils {

    final static public  String  dormientiUri ="/pgad-accounting-protocol/service/rest/sync/gamingAccountSleeper";
    final static public  String  statusUri ="/pgad-accounting-protocol/service/rest/sync/checkAccountStatus";
    final static public  String  listaDormientiUri ="/pgad-accounting-protocol/service/rest/sync/gamingAccountSleeperList";

    final static public  String  GD_ID=  "1";
    final static public  String  PARTY_ID=  "5";
    final static public  String  BWIN_ID=  "2";

    public static void setHttpHeader(HttpPost httpPost){

        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.8");
        httpPost.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
        httpPost.addHeader("User-Agent", "MassiveClient");

    }

    public static JSONObject dormientiRequest(String accountCode, String operatorId, String idTransaction, Date sleeperDate, int balanceAmount){

// {"requestElements":{"accountCode":"00000000000000043226","operatorId":"1","idTransaction":"0000000000000004326","sleeperDate":"2011-08-14","balanceAmount":"43226"}}

       // String request = "{\"requestElements\":{\"accountCode\":\"" + accountCode +"\",\"operatorId\":\""+ operatorId  + "\",\"idTransaction\":\""+idTransaction+"\",\"sleeperDate\":\""+ sleeperDate +"\",\"balanceAmount\":\""+ balanceAmount +"\"}}";

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        String request;

        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-mm-dd");


        obj1.put("accountCode", accountCode);
        obj1.put("operatorId", operatorId);
        obj1.put("idTransaction", idTransaction);
        obj1.put("sleeperDate", ft.format(sleeperDate).toString());
        obj1.put("balanceAmount", Integer.toString(balanceAmount));

        obj.put("requestElements", obj1);

        request = obj.toJSONString();
        //request = request.replaceAll("\\\\","");
        System.out.println(request);

        return obj;

    }

    public static JSONObject statusRequest(String accountCode, String operatorId, String idTransaction){

// {"requestElements":{"accountCode":"00000000000000043226","operatorId":"1","idTransaction":"0000000000000004326","sleeperDate":"2011-08-14","balanceAmount":"43226"}}

        // String request = "{\"requestElements\":{\"accountCode\":\"" + accountCode +"\",\"operatorId\":\""+ operatorId  + "\",\"idTransaction\":\""+idTransaction+"\",\"sleeperDate\":\""+ sleeperDate +"\",\"balanceAmount\":\""+ balanceAmount +"\"}}";

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        String request;

        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd");


        obj1.put("accountCode", accountCode);
        obj1.put("operatorId", operatorId);
        obj1.put("idTransaction", "");

        obj.put("requestElements", obj1);

        request = obj.toJSONString();
        //request = request.replaceAll("\\\\","");
        System.out.println(request);

        return obj;

    }



    public static JSONObject listaDormientiRequest(Date day, String operatorId, String idTransaction, int start){

// {"requestElements":{"accountCode":"00000000000000043226","operatorId":"1","idTransaction":"0000000000000004326","sleeperDate":"2011-08-14","balanceAmount":"43226"}}

        // String request = "{\"requestElements\":{\"accountCode\":\"" + accountCode +"\",\"operatorId\":\""+ operatorId  + "\",\"idTransaction\":\""+idTransaction+"\",\"sleeperDate\":\""+ sleeperDate +"\",\"balanceAmount\":\""+ balanceAmount +"\"}}";

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        String request;

        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd");


        obj1.put("requestDate", ft.format(day).toString());
        obj1.put("operatorId", operatorId);
        obj1.put("idTransaction", idTransaction);
        obj1.put("start", start+"");
        obj1.put("end", (start+99)+"");

        obj.put("requestElements", obj1);

        request = obj.toJSONString();
        //request = request.replaceAll("\\\\","");
        System.out.println(request);

        return obj;

    }


    public static Date addDays(Date date, int days)
    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DAY_OF_YEAR, days); //minus number would decrement the days
        //Date response = new Date();
        //response.setTime(cal.getTime());
        return cal.getTime();
    }

}
