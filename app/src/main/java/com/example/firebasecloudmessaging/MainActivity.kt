package com.example.firebasecloudmessaging

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnNext: Button
    private lateinit var btnRecy: Button


    private var db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.textInputEditText1)
        etAddress = findViewById(R.id.textInputEditText2)
        etEmail = findViewById(R.id.textInputEditText3)
        etPassword = findViewById(R.id.textInputEditText4)
        btnSave = findViewById(R.id.btnSave)
        btnNext = findViewById(R.id.btnNext)
        btnRecy = findViewById(R.id.btnRecy)


        btnSave.setOnClickListener {
            val sName = etName.text.toString().trim()
            val sAddress = etAddress.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            val userMap = hashMapOf(
                "name" to sName,
                "address" to sAddress,
                "email" to sEmail,
                "password" to sPassword
            )
//            val userId = FirebaseAuth.getInstance().currentUser?.uid

//            if (userId != null) {
                db.collection("user").document().set(userMap)
                    .addOnSuccessListener {
                        showToast("successfully Added!")
                        etName.text?.clear()
                        etAddress.text?.clear()
                        etEmail.text?.clear()
                        etPassword.text?.clear()
                    }
                    .addOnFailureListener {
                        showToast("Failed!")
                    }
          }
//           else{
//                showToast("User id is null")
//            }
//        }
        btnNext.setOnClickListener {
            val intent = Intent(this, RetrieveDataActivity::class.java)
            startActivity(intent)
        }
        btnRecy.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}