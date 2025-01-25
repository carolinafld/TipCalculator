package com.example.tipcalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClear.setOnClickListener {
            clear()
        }

        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.edtTotal.text
            val numPeopleTemp = binding.edtNumPeople.text
            val percentageTemp = binding.edtTipPercentage.text

            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
            ) {
                Snackbar
                    .make(binding.edtTotal, "Fill in all the fields", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val numPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()

                val totalTemp = totalTable / numPeople
                val tip = totalTemp * percentage / 100
                val totalWithTip = totalTemp + tip

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", numPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalAmount", totalWithTip)
                }
                clear()
                startActivity(intent)

            }

        }

    }

    private fun clear() {
        binding.edtTotal.setText("")
        binding.edtNumPeople.setText("")
        binding.edtTipPercentage.setText("")
    }


}