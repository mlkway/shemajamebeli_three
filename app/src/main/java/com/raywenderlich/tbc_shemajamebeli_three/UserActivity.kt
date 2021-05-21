package com.raywenderlich.tbc_shemajamebeli_three

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        init()





    }


    private fun  init(){

        var nameText: TextView = findViewById(R.id.nametxttv)
        var lastName: TextView = findViewById(R.id.lastNametxtv)
        var emailText: TextView = findViewById(R.id.emailtxt)
        var savedButton: Button = findViewById(R.id.button)

        val customer = intent.getParcelableExtra<Customer>("customer")

        nameText.text = customer?.nmae
        lastName.text = customer?.lastName
        emailText.text = customer?.email

        savedButton.setOnClickListener {
            forSaveButton()
        }


    }

    private fun forSaveButton(){


        var nameEditText: EditText = findViewById(R.id.editTextTextPersonName)
        var lastNameEditText: EditText = findViewById(R.id.editTextPersonLastName)
        var emailEditText: EditText = findViewById(R.id.editTextPersonEmail)


        if (!nameEditText.text.isNullOrBlank()&& !lastNameEditText.text.isNullOrBlank()&& !emailEditText.text.isNullOrBlank()){

            var customer = Customer(nameEditText.text.toString(),lastNameEditText.text.toString(), emailEditText.text.toString())

            val intent = Intent()

            intent.putExtra("customer", customer)
            setResult(RESULT_OK,intent)
            finish()


        }else{
            makeToast("sheavse yvela veli ")
        }


    }



    private fun makeToast(str: String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }





}