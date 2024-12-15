package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class UserMenuFragment : Fragment() {

    private var username: String? = null

    private lateinit var userMenuWelcomeTextView: TextView
    private lateinit var userMenuBalanceButton: Button
    private lateinit var userMenuTransferFundsButton: Button
    private lateinit var userMenuExchangeCalculatorButton: Button
    private lateinit var userMenuPayBillsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString("username")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userMenuWelcomeTextView = view.findViewById(R.id.userMenuWelcomeTextView)
        userMenuBalanceButton = view.findViewById(R.id.userMenuViewBalanceButton)
        userMenuTransferFundsButton = view.findViewById(R.id.userMenuTransferFundsButton)
        userMenuExchangeCalculatorButton = view.findViewById(R.id.userMenuExchangeCalculatorButton)
        userMenuPayBillsButton = view.findViewById(R.id.userMenuPayBillsButton)

        userMenuWelcomeTextView.text = "Welcome $username"
    }
}