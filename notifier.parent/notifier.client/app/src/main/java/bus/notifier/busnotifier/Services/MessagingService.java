package bus.notifier.busnotifier.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import bus.notifier.busnotifier.MainActivity;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public class MessagingService extends FirebaseMessagingService {

    public static int REQUEST_CODE = 1;

    public void onMessageReceived(RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.ic_dialog_info);

        if (body != null && !body.isEmpty()) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(body));
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n = builder.build();
        n.defaults = Notification.DEFAULT_ALL;
        assert notificationManager != null;
        notificationManager.notify(0, n);
    }

}
