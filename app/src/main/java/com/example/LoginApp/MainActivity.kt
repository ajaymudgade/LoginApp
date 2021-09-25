package com.example.LoginApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        button_Register_activity_mainXML.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


        button_Login_activity_mainXML.setOnClickListener {
            if (checking()) {
                val email = email_activity_mainXML.text.toString()
                val password = password_activity_mainXML.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var intent = Intent(this,LoggedIn::class.java)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Details", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checking(): Boolean {
        if (email_activity_mainXML.text.toString().trim { it <= ' ' }.isNotEmpty()
            && password_activity_mainXML.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }

}