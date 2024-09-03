package com.ak.test9

import android.annotation.SuppressLint
import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.Toast

class WhatsAppNotificationListener : NotificationListenerService() {

    @SuppressLint("LongLogTag")
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Check if the notification is from WhatsApp

        Log.d("WhatsAppNotificationListener", "onNotificationPosted: ${sbn.packageName}")
        if (sbn.packageName == "com.whatsapp") {
            val notification: Notification = sbn.notification
            val extras = notification.extras

            // Get the notification title and text
            val title = extras.getString(Notification.EXTRA_TITLE)
            val text = extras.getString(Notification.EXTRA_TEXT)

            Log.d("WhatsAppNotificationListener", "onNotificationPosted: $title")
            Log.d("WhatsAppNotificationListener", "onNotificationPosted: $text")

            // Check if the text contains specific content
            if (text != null && text.contains("Ringing…")) {
                // Your logic when the notification contains the specific text
                // For example, show a toast or perform an action
                Toast.makeText(applicationContext, "Text found in WhatsApp notification", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Handle notification removal if necessary
    }

}