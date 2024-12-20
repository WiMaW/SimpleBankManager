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

    private var callback: ViewBalanceFragmentListener? = null

    private lateinit var balanceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as ViewBalanceFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        balanceTextView = view.findViewById(R.id.viewBalanceAmountTextView)

        balanceTextView.text = String.format("$%.2f", callback?.getBalance() ?: 100.00)
    }
    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface ViewBalanceFragmentListener {
        fun getBalance() : Double?
    }
}