package org.hyperskill.simplebankmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private var callback: LoginFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as LoginFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton = view.findViewById(R.id.loginButton)
        usernameEditText = view.findViewById(R.id.loginUsername)
        passwordEditText = view.findViewById(R.id.loginPassword)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            callback?.getLoginAndPassword(username, password)

            val bundle = Bundle()
            bundle.putString("username", username)

            findNavController().navigate(R.id.action_loginFragment_to_userMenuFragment, bundle)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface LoginFragmentListener {
        fun getLoginAndPassword(usernameInput: String, passwordInput: String)
    }
}

