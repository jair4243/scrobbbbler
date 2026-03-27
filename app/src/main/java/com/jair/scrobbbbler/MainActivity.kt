package com.jair.scrobbbbler

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var latestTextView: TextView
    private lateinit var grantAccessButton: Button
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        latestTextView = findViewById(R.id.latestTextView)
        grantAccessButton = findViewById(R.id.grantAccessButton)
        refreshButton = findViewById(R.id.refreshButton)

        grantAccessButton.setOnClickListener {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }

        refreshButton.setOnClickListener {
            refreshLatestText()
        }

        refreshLatestText()
    }

    override fun onResume() {
        super.onResume()
        refreshLatestText()
    }

    private fun refreshLatestText() {
        val latest = NotificationStore.getLatestEntry(this)
        latestTextView.text = latest ?: "Nothing yet. Open Eddict and play something from SyncLink."
    }
}