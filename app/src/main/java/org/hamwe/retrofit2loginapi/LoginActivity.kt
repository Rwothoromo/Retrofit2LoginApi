package org.hamwe.retrofit2loginapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var apiServiceInterface: ApiServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtUsername = findViewById<View>(R.id.edtUsername) as EditText
        edtPassword = findViewById<View>(R.id.edtPassword) as EditText
        btnLogin = findViewById<View>(R.id.btnLogin) as Button
        apiServiceInterface = ApiUtils.getApiService(this)
        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            //validate form
            if (validateLogin(username, password)) {
                //do login
                doLogin(username, password)
            }
        }
    }

    private fun validateLogin(username: String?, password: String?): Boolean {
        if (username == null || username.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show()
            return false
        }
        if (password == null || password.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun doLogin(username: String, password: String) {
        val logInRequest = LogInRequest(username, password)
        val call: Call<JsonObject> = apiServiceInterface.login(logInRequest)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    val jsonObject: JsonObject? = response.body()
                    if (jsonObject != null) {
                        Log.d("jsonObject", jsonObject.toString())
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                    } else {
                        Log.d("jsonObject", "null")
                        Toast.makeText(
                            this@LoginActivity,
                            "The username or password is incorrect",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Log.d("jsonObject", response.message())
                    Toast.makeText(
                        this@LoginActivity,
                        "Error! Please try again!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("jsonObject", t.message.toString())
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
