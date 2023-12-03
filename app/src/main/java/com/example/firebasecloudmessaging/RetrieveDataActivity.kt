package com.example.firebasecloudmessaging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class RetrieveDataActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAddress: TextView
    private lateinit var btnNext: Button

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_data)

        tvName = findViewById(R.id.textView)
        tvEmail = findViewById(R.id.textView2)
        tvAddress = findViewById(R.id.textView3)
        btnNext = findViewById(R.id.btnNext)

        val ref = db.collection("user").document("VixLEpD3YLz9avpd6N6a")
        ref.get().addOnSuccessListener {
            if(it!=null){
                val name = it.data?.get("name").toString()
                val email = it.data?.get("email").toString()
                val address = it.data?.get("address").toString()

                tvName.text = name
                tvEmail.text = email
                tvAddress.text = address
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        btnNext.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }
    }
}