package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

class activity_login : AppCompatActivity() {
    private var listStorage = ListStorage.getInstance()
    var users : List<User> = listStorage.getDataUsers()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickLogin (view: View) {
        var user = verifyLogin(username_login.text.toString() ,password_login.text.toString())
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.apply { putExtra("EXTRA_UTILIZADOR", user) }
            startActivity(intent)
            finish()
        }
    }

    fun onClickRegister(view: View) {
        val intent = Intent(this, activity_registo::class.java)
        startActivity(intent)
        finish()
    }

    fun verifyLogin(name: String, password: String): User? {
        for (user in users) {
            if (user.name == name && user.password == DigestUtils.sha256Hex(password)){
                return user
            }
        }
        return null
    }
}
