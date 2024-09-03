package com.ak.test9

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var whatsapp:Button
    private lateinit var telegram:Button
    private lateinit var whatsappNo:EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        whatsapp = findViewById(R.id.whatsapp)
        telegram = findViewById(R.id.telegram)
        whatsappNo = findViewById(R.id.whatsappNo)

        whatsapp.setOnClickListener {
            val whatsappNoStr: String = whatsappNo.text.toString().trim()
            if (whatsappNoStr.isNotEmpty()) {
                val sendIntent = Intent("android.intent.action.MAIN")
                sendIntent.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
                sendIntent.putExtra("jid", "$whatsappNoStr@s.whatsapp.net")
                sendIntent.type = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call"
                startActivity(sendIntent)
            }
        }

        telegram.setOnClickListener {
            val telegramNoStr: String = whatsappNo.text.toString().trim()
            if (telegramNoStr.isNotEmpty()) {
                val i = Intent(Intent.ACTION_VIEW)
                val appName = "org.telegram.messenger"
                i.data = Uri.parse("http://telegram.me/+$telegramNoStr")
                i.setPackage(appName)
                startActivity(i)
            }
        }

        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivity(intent)


    }

//    companion object{
//
//        init {
//            System.loadLibrary("nativehook")
//
//        }
//    }

    external fun nativeHook()

}