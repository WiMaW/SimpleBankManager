package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity(), LoginFragment.LoginFragmentListener,
    UserMenuFragment.UserMenuFragmentListener, UserMenuFragment.UserMenuFragmentListenerForBalance {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            Tests for android can not guarantee the correctness of solutions that make use of
            mutation on "static" variables to keep state. You should avoid using those.
            Consider "static" as being anything on kotlin that is transpiled to java
            into a static variable. That includes global variables and variables inside
            singletons declared with keyword object, including companion object.
            This limitation is related to the use of JUnit on tests. JUnit re-instantiate all
            instance variable for each test method, but it does not re-instantiate static variables.
            The use of static variable to hold state can lead to state from one test to spill over
            to another test and cause unexpected results.
            Using mutation on static variables to keep state
            is considered a bad practice anyway and no measure
            attempting to give support to that pattern will be made.
         */
    }

    override fun getExchangeMap() : Serializable? {
        return intent.extras?.getSerializable("exchangeMap")
    }

    override fun getBillInfoMap(): Serializable? {
        return intent.extras?.getSerializable("billInfo")
    }

    override fun getLoginAndPassword(usernameInput: String, passwordInput: String) {
        var usernameIntent = "Lara"
        var passwordIntent = "1234"

        if (!intent.extras?.getString("username").isNullOrEmpty()) {
            usernameIntent = intent.extras?.getString("username").toString()
        }

        if (!intent.extras?.getString("password").isNullOrEmpty()) {
            passwordIntent = intent.extras?.getString("password").toString()
        }

        if (usernameIntent == usernameInput && passwordIntent == passwordInput) {
            Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getBalance(): Double? {
        var balance: Double? = 100.0

        if (!intent.extras?.getDouble("balance").toString().isNullOrEmpty()) {
            balance = intent.extras?.getDouble("balance")
        }
        return balance
    }
}