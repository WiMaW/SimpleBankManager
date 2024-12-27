package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.android.awaitFrame

class TransferFundsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var balance: Double? = null

    private lateinit var accountNumberEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var transferButton: Button

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer_funds, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transferButton = view.findViewById(R.id.transferFundsButton)
        accountNumberEditText = view.findViewById(R.id.transferFundsAccountEditText)
        amountEditText = view.findViewById(R.id.transferFundsAmountEditText)

        transferButton.setOnClickListener {

            val accountNumber = accountNumberEditText.text
            val amount = amountEditText.text

            var amountStringFormat = ""

            val regex = Regex("^(sa|ca)\\d{4}$")

            if (accountNumber.isNullOrBlank() || !regex.matches(accountNumber)) {
                accountNumberEditText.error = "Invalid account number"
            }

            if (amount.isNullOrBlank()) {
                amountEditText.error = "Invalid amount"
            } else if (amount.toString().toDouble() <= 0) {
                amountEditText.error = "Invalid amount"
            } else {
                amountStringFormat = String.format("$%.2f", amount?.toString()?.toDouble() ?: 0.00)

                if (balance != null) {
                    if (balance!! < amount.toString().toDouble()) {
                        Toast.makeText(
                            context,
                            "Not enough funds to transfer $amountStringFormat",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        var bundle = Bundle()
                        bundle.putDouble("transferredAmount", amount.toString().toDouble())
                        Toast.makeText(
                            context,
                            "Transferred $amountStringFormat to account $accountNumber",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.userMenuFragment, bundle)
                    }
                }
            }

        }

    }
}