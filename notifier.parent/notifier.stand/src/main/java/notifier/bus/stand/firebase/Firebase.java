package notifier.bus.stand.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by daniyar on 27/01/18.
 */
@Component
public class Firebase implements ApplicationListener<ApplicationReadyEvent> {

    private SendNotifications sendNotifications;

    public Firebase(SendNotifications sendNotifications) {
        this.sendNotifications = sendNotifications;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("hackatun-firebase-adminsdk-x901s-7ca43c5fd7.json").getFile());

            FileInputStream serviceAccount = new FileInputStream(file);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://hackatun.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            //sendNotifications.sendNotifications();
            sendNotifications.send_FCM_Notification(
                    "fRAQosWL050:APA91bGSFtfbFdbq6ZKFunq63Z88_7tdOKrCktOrGH8udKKE8f5kZJWJQwy_DVlBhsoL6PO-BFqF-zaOK1-mTj1JJwRxitBbRwoGkfTXVHZ7uphhMylndeGpNLCXAwoqbPm48Z3uNF1m",
                    "AAAA9BPV9oU:APA91bEucwbWZ5Lh_RBywHRmzMAI6NsI66O1kd4dZ7jPqLWVguo2HSJG6-MdSqOBu1Gaew3aCOke3xkVk-fH_E_oTSttjIYv5kBz94sQWNWWxzfwOzbbXJAKqu3cMEpY6jTJL8nvVseL",
                    "Darkhan!!!"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
