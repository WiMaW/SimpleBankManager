package org.hyperskill.simplebankmanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import java.io.Serializable

class UserMenuFragment : Fragment() {

    private var username: String? = null
    private var transferredAmount: Double? = null
    private var callback: UserMenuFragmentListener ? = null

    private lateinit var userMenuWelcomeTextView: TextView
    private lateinit var userMenuBalanceButton: Button
    private lateinit var userMenuTransferFundsButton: Button
    private lateinit var userMenuExchangeCalculatorButton: Button
    private lateinit var userMenuPayBillsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString("username")
            transferredAmount = it.getDouble("transferredAmount")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as UserMenuFragmentListener
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

        var balance = callback?.getBalance() ?: 100.00

        if (transferredAmount != null) {
            balance -= transferredAmount!!
        }

        val bundleBalance = Bundle()
        bundleBalance.putDouble("balance", balance)

        userMenuBalanceButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_viewBalanceFragment, bundleBalance)
        }

        userMenuTransferFundsButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_transferFundsFragment, bundleBalance)
        }

        val exchangeMap = callback?.getExchangeMap()
        val bundleExchangeMap = Bundle()
        bundleExchangeMap.putSerializable("exchangeMap", exchangeMap)

        userMenuExchangeCalculatorButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_calculateExchangeFragment, bundleExchangeMap)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface UserMenuFragmentListener {
        fun getBalance() : Double?
        fun getExchangeMap() : Serializable?
    }
}