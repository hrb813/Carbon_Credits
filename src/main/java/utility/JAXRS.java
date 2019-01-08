package utility;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JAXRS {

    public static JSONObject jsonObject;
    public static JSONParser parser;
    public static String host ;
    public static String app;
    public static String xparameter ;
    public static String xvalue ;

        public static void main(String[] args) {
            String resultkey = null;
            String resultObject = null;
            getJSONObject("/v1/Categories/6327/Details.json","catalogue","true");
            try {
                resultkey = getKeyValues("Name");
                resultObject = getObjectValues("Promotions", "Name", "Gallery", "Description");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(resultkey.equals("Carbon credits")){
                System.out.println("PASS");
            }
            else {
                System.out.println("FAIL");
            }

            if(resultObject.contains("2x larger image")){
                System.out.println("PASS");
            }
            else {
                System.out.println("FAIL");
            }


            try {
                resultkey = getKeyValues("CanRelist");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(resultkey.equals("true")){
                System.out.println("PASS");
            }
            else {
                System.out.println("FAIL");
            }
        }

        public static URI getBaseURI() {
            return UriBuilder.fromUri(host).build();
        }

        public static void getJSONObject(String path, String paramName, String paramVal){

            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getBaseURI());

    /** Check status **/
            String response = target.path(path)
//                    .path(path2)
                    .queryParam(paramName,paramVal)
                    .request()
                    .accept(MediaType.TEXT_PLAIN)
                    .get(Response.class)
                    .toString();
            System.out.println(response);

    /** Get JSON body**/

            String jsonResponse = target.path(path)
                    .queryParam(paramName,paramVal)
                    .request()
                    .accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println(jsonResponse);

        try {
            parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(jsonResponse);
        }
        catch (Exception e){
            System.out.println(e);
        }
        }


    public static String getKeyValues(String keyHead) throws ParseException {
        String r = null;
        Iterator<?> keys = jsonObject.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (key.equals(keyHead)) {
                try{
                r = (String) jsonObject.get(key);
                }catch (Exception e){

                }
                try{
                    r = String.valueOf((Boolean) jsonObject.get(key));
                }catch (Exception e){

                }

            }
        }
        return r;
    }


        public static String getObjectValues(String keyHead, String keyVal, String arg0, String arg1) throws ParseException {
            String r = null;
            Iterator<?> keys = jsonObject.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                    if (key.equals(keyHead)) {
                        System.out.println(jsonObject.get(key));
                        JSONArray xx = (JSONArray) parser.parse(jsonObject.get(key).toString());

                    for (Object o : xx) {
                        JSONObject configdata = (JSONObject) o;
                        String v = (String) configdata.get(keyVal);
                        if (v.equals(arg0)) {
                            r = (String) configdata.get(arg1);
                        }
                    }
                }
            }
            return r;
        }
}
