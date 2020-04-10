package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registo.*
import org.apache.commons.codec.digest.DigestUtils

class activity_registo : AppCompatActivity() {
    private val listStorage = ListStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)


    }

    fun onClickSubmit(view: View) {

        if (comparePassword()){
            val hash: String = DigestUtils.sha256Hex(password.text.toString())
            val newUser = User(username.text.toString(), hash, email.text.toString())
            listStorage.insertUser(newUser)
            Toast.makeText(this,"User $newUser", Toast.LENGTH_LONG).show()

            val intent = Intent(this, activity_login::class.java)
            startActivity(intent)
            finish()

        }
    }

    fun comparePassword(): Boolean {
        if (password.text.isNotEmpty() && password.text.toString().equals(password_confirmacao.text.toString())) {
             return true
        }
        return false
    }
}
