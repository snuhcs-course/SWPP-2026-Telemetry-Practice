package com.example.telemetrypractice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val analytics = if (FirebaseApp.initializeApp(this) != null) {
            FirebaseAnalytics.getInstance(this)
        } else null

        findViewById<TextView>(R.id.tvStatus).setText(
            if (analytics == null) R.string.analytics_missing else R.string.analytics_ready
        )
        val value = findViewById<TextView>(R.id.tvCount)
        var count = 0

        fun record(buttonId: String) {
            val params = Bundle().apply { putString("button_id", buttonId) }
            analytics?.logEvent("button_tap", params)
        }

        findViewById<Button>(R.id.btnCount).setOnClickListener {
            count++
            value.text = count.toString()
            record("count")
        }
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            count = 0
            value.text = "0"
            record("reset")
        }
    }
}
