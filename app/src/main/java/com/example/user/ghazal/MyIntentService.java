package com.example.user.ghazal;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    private static final int NOTIFICATION_ID=3;

    // TODO: Rename actions, choose action names that describe tasks that this
   /* // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.aseel.myfinalapp.action.FOO";
    private static final String ACTION_BAZ = "com.example.aseel.myfinalapp.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.aseel.myfinalapp.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.aseel.myfinalapp.extra.PARAM2";*/

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Notification.Builder builder= new Notification.Builder(this);
        builder.setContentTitle("Payment");
        builder.setContentText("Remember to pay until ten days!");
        builder.setSmallIcon(R.drawable.ic_attach_money_black_24dp);
        Intent notifyIntent = new Intent(this, ExpensesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);


    }


    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

}