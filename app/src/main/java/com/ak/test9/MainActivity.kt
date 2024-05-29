package com.ak.test9

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import de.robv.android.xposed.XposedBridge

class MainActivity : AppCompatActivity() {

    private lateinit var whatsapp:Button
    private lateinit var whatsappNo:EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        whatsapp = findViewById(R.id.whatsapp)
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
    }
}