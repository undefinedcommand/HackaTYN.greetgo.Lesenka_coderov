package notifier.bus.stand.firebase;

import notifier.bus.stand.register_stand_impl.db.Db;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daniyar on 28/01/18.
 */
@Service
public class SendNotifications {

    private Db db;

    public String URI = "https://fcm.googleapis.com/fcm/send";
    public String key = "AIzaSyBKycE23b2Mb07V0u4biURwPYbomd7UT6A";

    public SendNotifications(Db db) {
        this.db = db;
    }

    String token = "fRAQosWL050:APA91bGSFtfbFdbq6ZKFunq63Z88_7tdOKrCktOrGH8udKKE8f5kZJWJQwy_DVlBhsoL6PO-BFqF-zaOK1-mTj1JJwRxitBbRwoGkfTXVHZ7uphhMylndeGpNLCXAwoqbPm48Z3uNF1m";
    public void sendNotifications() throws IOException {
        Set<String> tokens = db.tokens;
        tokens.add("f8yQui6Cm9k:APA91bGCZQKH2WfmOCy-TO5hj9Pp-nvc7AoW1ZWF9Fk3LDVVH4LXFcMLv_36tL9cGYDS5CBjQCRKperpKtqUwxqly-OKNuV7_G8Lp_xq8jN0fXZtIJ0Hn3_KloUYk9HWGHYv_XnY43aE");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URI);

        JSONObject root = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("title", "hackatun");
        data.put("detail", "Darkhan");
        root.put("data", data);
        root.put("to", token);

        System.out.println(root.toString());

        StringEntity entity = new StringEntity(root.toString());
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "key="+key);
        httpPost.setHeader("Content-type", "application/json");

        System.out.println(httpPost.getEntity().getContent());
        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("SENDED!!!" + response.getStatusLine().toString());
        client.close();
    }

    final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";





    void send_FCM_Notification(String tokenId, String server_key, String message){


        try{

// Create URL instance.

            URL url = new URL(FCM_URL);



// create connection.

            HttpURLConnection conn;

            conn = (HttpURLConnection) url.openConnection();



            conn.setUseCaches(false);

            conn.setDoInput(true);

            conn.setDoOutput(true);



//set method as POST or GET

            conn.setRequestMethod("POST");



//pass FCM server key

            conn.setRequestProperty("Authorization","key="+server_key);



//Specify Message Format

            conn.setRequestProperty("Content-Type","application/json");



//Create JSON Object & pass value

            JSONObject infoJson = new JSONObject();

            infoJson.put("title","Here is your notification.");

            infoJson.put("body", message);



            JSONObject json = new JSONObject();

            json.put("to",tokenId.trim());

            json.put("notification", infoJson);



            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(json.toString());

            wr.flush();

            int status = 0;

            if( null != conn ){

                status = conn.getResponseCode();

            }

            if( status != 0){



                if( status == 200 ){

//SUCCESS message

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    System.out.println("Android Notification Response : " + reader.readLine());

                }else if(status == 401){

//client side error

                    System.out.println("Notification Response : TokenId : " + tokenId + " Error occurred :");

                }else if(status == 501){

//server side error

                    System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + tokenId);

                }else if( status == 503){

//server side error

                    System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);

                }



            }

        }catch(MalformedURLException mlfexception){

// Prototcal Error

            System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());

        }catch(IOException mlfexception){

//URL problem

            System.out.println("Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());

        }catch(JSONException jsonexception){

//Message format error

            System.out.println("Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());

        }catch (Exception exception) {

//General Error or exception.

            System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());

        }

    }
}
