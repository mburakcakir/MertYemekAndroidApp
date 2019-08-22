package com.example.mertyemek.di;

import android.content.Context;
import android.content.Intent;
import com.example.mertyemek.ui.activity.MainActivity;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;


public class OneSignalHandler implements OneSignal.NotificationOpenedHandler
{

  Context context;

  OneSignalHandler(Context context1)
    {
        this.context=context1;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result)
    {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String activityToBeOpened = null;

        if (data != null)
        {
            activityToBeOpened = data.optString("activityToBeOpened", null);
        }

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);

        if(activityToBeOpened!=null)
        {
            DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION=activityToBeOpened;
        }
        context.startActivity(intent);

    }
}