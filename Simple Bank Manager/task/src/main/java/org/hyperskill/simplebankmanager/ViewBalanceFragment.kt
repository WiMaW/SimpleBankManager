package org.hyperskill.simplebankmanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.hyperskill.simplebankmanager.LoginFragment.LoginFragmentListener

class ViewBalanceFragment : Fragment() {

    private lateinit var balanceTextView: TextView
    private var balance: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            balance = it.getDouble("balance")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        balanceTextView = view.findViewById(R.id.viewBalanceAmountTextView)
        balanceTextView.text = String.format("$%.2f", balance ?: 100.00)
    }
}