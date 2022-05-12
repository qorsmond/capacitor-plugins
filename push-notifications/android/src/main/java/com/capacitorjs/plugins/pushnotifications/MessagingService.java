package com.capacitorjs.plugins.pushnotifications;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;
import android.content.Intent;

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // PushNotificationsPlugin.sendRemoteMessage(remoteMessage);

        // this is the issue
        // https://github.com/ionic-team/capacitor-plugins/issues/200

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            PushNotificationsPlugin.sendRemoteDataMessage(remoteMessage);

            // Intent broadcastIntent = new Intent("android.intent.action.capacitor.pushnotification");
            // broadcastIntent.putExtra("remoteMessage", remoteMessage);
            // sendBroadcast(broadcastIntent);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            PushNotificationsPlugin.sendRemoteMessage(remoteMessage);
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        PushNotificationsPlugin.onNewToken(s);
    }
}
