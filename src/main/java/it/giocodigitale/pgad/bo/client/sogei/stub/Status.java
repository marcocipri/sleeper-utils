package it.giocodigitale.pgad.bo.client.sogei.stub;

/**
 * Created by mcipri on 29/08/14.
 */

import it.giocodigitale.pgad.bo.client.sogei.stub.utils.StubUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class Status {

    private String gwHost ;

    public Status(String gwHost){
        this.gwHost = gwHost;
    }


    public  JSONObject pgadRequestExe(JSONObject request){

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(gwHost + StubUtils.statusUri);
        StubUtils.setHttpHeader(httpPost);
        JSONObject result = null;





        StringEntity postRequest;

        try {

            postRequest = new StringEntity(request.toJSONString());
            httpPost.setEntity(postRequest);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        HttpResponse response;


        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();


                JSONParser parser = new JSONParser();
                Reader reader = new InputStreamReader(instream);
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;

                String responseElements =  jsonObject.get("responseElements").toString();

                Object obj1 = parser.parse(responseElements);
                result = (JSONObject) obj1;





            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return result;
    }


}
