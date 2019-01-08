package utility;


import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static utility.JAXRS.*;

public class APIUtility {


    public static Client client;
//    public static String host ;
//    public static String app;
//    public static String xparameter ;
//    public static String xvalue ;
    public static WebTarget webTarget;

//    public static String authorizationHeaderValue = "No Auth" ;
    public static HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();

    public static String usernameAndPassword = "" + ":" + "";
    public static String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );


    @BeforeClass
    public static void init() {
                client = ClientBuilder.newBuilder().build();
        webTarget = client.target("https://api.tmsandbox.co.nz" );
//        client = ClientBuilder.newClient();
//        client.register(feature);

    }

    public static void getResponse(){
        client.register(feature);
        Response response = client.target(host + app + "?" + xparameter + "=" + xvalue )
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization",authorizationHeaderValue)
                .get();
        System.out.println(response);
        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());



    }

    @Test
    public void Main(){
        host = "https://api.tmsandbox.co.nz" ;
        app = "/v1/Categories/6327/Details.json";
        xparameter = "catalogue";
        xvalue = "true";
        getResponse();

    }

}
