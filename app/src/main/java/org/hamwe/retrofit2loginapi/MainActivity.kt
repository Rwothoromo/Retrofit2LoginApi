package org.hamwe.retrofit2loginapi

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtUsername: TextView = findViewById<View>(R.id.txtUsername) as TextView

        val extras = intent.extras
        val username: String?

        if (extras != null) {
            username = extras.getString("username")
            txtUsername.text = "Hello $username!"
        }
    }
}
