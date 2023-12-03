package com.example.firebasecloudmessaging

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class UpdateActivity : AppCompatActivity() {
    private lateinit var etName: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnUpdate: Button
    private lateinit var btnPrev: Button

    private var db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        etName = findViewById(R.id.textInputEditText1)
        etAddress = findViewById(R.id.textInputEditText2)
        etEmail = findViewById(R.id.textInputEditText3)
        etPassword = findViewById(R.id.textInputEditText4)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnPrev = findViewById(R.id.btnPrev)

        btnUpdate.setOnClickListener {
            val sName = etName.text.toString().trim()
            val sAddress = etAddress.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            val userMap = mapOf(
                "name" to sName,
                "address" to sAddress,
                "email" to sEmail,
                "password" to sPassword
            )

//            To delete a particular field
//            val userMap = mapOf(
//                "password" to FieldValue.delete()
//            )
//            val userId = FirebaseAuth.getInstance().currentUser?.uid

//            if (userId != null) {
            db.collection("user").document("VixLEpD3YLz9avpd6N6a").update(userMap)
                .addOnSuccessListener {
                    showToast("successfully Updated!")
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
        btnPrev.setOnClickListener {
            val intent = Intent(this, RetrieveDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}