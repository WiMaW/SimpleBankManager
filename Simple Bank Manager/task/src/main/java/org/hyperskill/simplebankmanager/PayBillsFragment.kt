package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController

class PayBillsFragment : Fragment() {

    private var payBillsInfoMap: Map<String, Triple<String, String, Double>>? = null
    private var balance: Double? = null

    private lateinit var payBillsCodeEditText: EditText
    private lateinit var payBillsInfoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            payBillsInfoMap =
                it.getSerializable("billInfo") as? Map<String, Triple<String, String, Double>>
                    ?: mapOf(
                        "ELEC" to Triple("Electricity", "ELEC", 45.0),
                        "GAS" to Triple("Gas", "GAS", 20.0),
                        "WTR" to Triple("Water", "WTR", 25.5)
                    )
            balance = it.getDouble("balance")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pay_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        payBillsCodeEditText = view.findViewById(R.id.payBillsCodeInputEditText)
        payBillsInfoButton = view.findViewById(R.id.payBillsShowBillInfoButton)

        var paymentFlag: Boolean = false
        var amountToPay: Double = 0.0

        payBillsInfoButton.setOnClickListener {
            val payBillsCode = payBillsCodeEditText.text.toString()
            var tripleBillInfo: Triple<String, String, Double>? = null

            if (payBillsCode.isNullOrEmpty() || !searchForCodeInMap(
                    payBillsInfoMap,
                    payBillsCode
                )
            ) {
                context?.let { it1 ->
                    AlertDialog.Builder(it1)
                        .setTitle("Error")
                        .setMessage("Wrong code")
                        .setPositiveButton("OK", null)
                        .show()
                }
            } else {
                if (payBillsInfoMap != null) {
                    for (key in payBillsInfoMap!!.keys) {
                        if (payBillsCode == key) {
                            tripleBillInfo = payBillsInfoMap!![payBillsCode]
                        }
                    }
                }

                context?.let { it1 ->
                    AlertDialog.Builder(it1)
                        .setTitle("Bill info")
                        .setMessage(
                            """
                            Name: ${tripleBillInfo!!.first}
                            BillCode: ${tripleBillInfo.second}
                            Amount: ${String.format("$%.2f", tripleBillInfo.third)}
                        """.trimIndent()
                        )
                        .setPositiveButton("Confirm", { _, _ ->
                            confirmButtonAction(
                                balance, tripleBillInfo.third,
                                requireContext(), tripleBillInfo.first
                            )
                            amountToPay = tripleBillInfo.third
                            paymentFlag = true
                        })
                        .setNegativeButton("Cancel", null)
                        .show()
                }
                if (paymentFlag) {
                    balance = balance?.minus(tripleBillInfo!!.third)
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val bundle = Bundle()
                    bundle.putDouble("amountToPayBill", amountToPay)
                    findNavController().navigate(
                        R.id.action_payBillsFragment_to_userMenuFragment,
                        bundle
                    )
                }
            })
    }

    private fun searchForCodeInMap(
        payBillsInfoMap: Map<String, Triple<String, String, Double>>?,
        userInput: String
    ): Boolean {
        if (payBillsInfoMap != null) {
            for (key in payBillsInfoMap.keys) {
                if (userInput == key) {
                    return true
                }
            }
        }
        return false
    }

    private fun confirmButtonAction(
        balance: Double?,
        amount: Double?,
        context: Context,
        billName: String
    ) {
        if (balance != null) {
            if (balance >= amount!!) {
                Toast.makeText(
                    context,
                    "Payment for bill $billName, was successful",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage("Not enough funds")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}



