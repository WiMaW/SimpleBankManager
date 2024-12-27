package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class CalculateExchangeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var exchangeMap: Map<String, Map<String, Double>>? = null

    private lateinit var exchangeFromSpinner: Spinner
    private lateinit var exchangeToSpinner: Spinner
    private lateinit var enterAmount: EditText
    private lateinit var calculateButton: Button
    private lateinit var displayResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exchangeMap =
                it.getSerializable("exchangeMap") as? Map<String, Map<String, Double>> ?: mapOf(
                    "EUR" to mapOf(
                        "GBP" to 0.5,
                        "USD" to 2.0
                    ),
                    "GBP" to mapOf(
                        "EUR" to 2.0,
                        "USD" to 4.0
                    ),
                    "USD" to mapOf(
                        "EUR" to 0.5,
                        "GBP" to 0.25
                    )
                )

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exchangeFromSpinner = view.findViewById(R.id.calculateExchangeFromSpinner)
        exchangeToSpinner = view.findViewById(R.id.calculateExchangeToSpinner)
        enterAmount = view.findViewById(R.id.calculateExchangeAmountEditText)
        calculateButton = view.findViewById(R.id.calculateExchangeButton)
        displayResult = view.findViewById(R.id.calculateExchangeDisplayTextView)

        var currencyFrom: Any? = null
        var currencyTo: Any? = null

        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.currencies_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                exchangeFromSpinner.adapter = adapter
                exchangeToSpinner.adapter = adapter

                exchangeFromSpinner.setSelection(1)
            }
        }

        exchangeFromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (exchangeFromSpinner.selectedItem == exchangeToSpinner.selectedItem) {

                    if (currencyTo == currencyFrom) {
                        Toast.makeText(
                            context,
                            "Cannot convert to same currency",
                            Toast.LENGTH_SHORT
                        ).show()

                        when (exchangeToSpinner.selectedItemPosition) {
                            0, 1 -> exchangeToSpinner.setSelection(exchangeFromSpinner.selectedItemPosition + 1)
                            2 -> exchangeToSpinner.setSelection(0)
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        exchangeToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (exchangeFromSpinner.selectedItem == exchangeToSpinner.selectedItem) {

                    if (currencyTo == currencyFrom) {
                        Toast.makeText(
                            context,
                            "Cannot convert to same currency",
                            Toast.LENGTH_SHORT
                        ).show()

                        when (exchangeToSpinner.selectedItemPosition) {
                            0, 1 -> exchangeToSpinner.setSelection(exchangeFromSpinner.selectedItemPosition + 1)
                            2 -> exchangeToSpinner.setSelection(0)
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        calculateButton.setOnClickListener {
            var amount = ""
            var result = 0.0

            amount = enterAmount.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show()
            } else {
                currencyFrom = adapter?.getItem(exchangeFromSpinner.selectedItemPosition)
                currencyTo = adapter?.getItem(exchangeToSpinner.selectedItemPosition)
                if (exchangeMap != null) {
                    for (externalCurrency in exchangeMap!!.keys) {
                        if (externalCurrency == currencyFrom) {
                            val innerMap = exchangeMap!![externalCurrency]
                            for (internalCurrency in innerMap!!.keys) {
                                if (internalCurrency == currencyTo) {
                                    val multiper = innerMap[currencyTo] ?: 0.0
                                    result = amount.toDouble() * multiper

                                    val currencyFromToDisplay = when (currencyFrom) {
                                        "EUR" -> String.format("€%.2f", amount.toDouble())
                                        "USD" -> String.format("$%.2f", amount.toDouble())
                                        "GBP" -> String.format("£%.2f", amount.toDouble())
                                        else -> {
                                            ""
                                        }
                                    }

                                    val currencyToDisplay = when (currencyTo) {
                                        "EUR" -> String.format("€%.2f", result)
                                        "USD" -> String.format("$%.2f", result)
                                        "GBP" -> String.format("£%.2f", result)
                                        else -> {
                                            ""
                                        }
                                    }

                                    displayResult.text =
                                        "$currencyFromToDisplay = $currencyToDisplay"
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}