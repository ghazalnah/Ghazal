package com.example.user.ghazal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    //this method is called by the Andriod system.
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent intent11=new Intent(context,MyIntentService.class);
        context.startService(intent11);
    }
}
