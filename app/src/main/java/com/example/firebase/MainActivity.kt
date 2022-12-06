package com.example.firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var name:EditText
    lateinit var btn:Button
    lateinit var nameText: TextView

    val database:FirebaseDatabase = FirebaseDatabase.getInstance()
    val refrence:DatabaseReference = database.reference.child("user")
    val ref2:DatabaseReference = database.reference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.editTextTextPersonName)
        btn = findViewById(R.id.button)
        nameText = findViewById(R.id.textView)

        ref2.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val realName:String = snapshot.child("user").child("newUser").value as String
                nameText.text = realName
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        btn.setOnClickListener {

            val userName:String = name.text.toString()
            refrence.child("newUser").setValue(userName)
            name.setText("")
        }

    }
}