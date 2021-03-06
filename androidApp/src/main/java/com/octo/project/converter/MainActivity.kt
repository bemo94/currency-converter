package com.octo.project.converter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.octo.project.R
import com.octo.project.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ConverterDisplay {

    private val mainDi: MainDi by lazy {
        MainDi(this)
    }
    private val controller: ConverterController by lazy {
        mainDi.getController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokens = arrayOf(
            "USD",
            "EUR",
            "CHF",
            "NZD"
        )

        val array = ArrayAdapter(this, android.R.layout.simple_spinner_item, tokens)
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = array
        spinner2.adapter = array

        convert.setOnClickListener {
            controller.convert(
                spinner1.selectedItem.toString(),
                spinner2.selectedItem.toString(),
                result.text.toString()
            )
        }

        history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        revert.setOnClickListener {
            val tmp = spinner1.selectedItemPosition
            spinner1.setSelection(spinner2.selectedItemPosition)
            spinner2.setSelection(tmp)
        }

        reset.setOnClickListener {
            controller.reset()
        }
    }

    fun numberClick(view: View) {
        val button = view as Button
        controller.append(result.text.toString(), button.text.toString())
    }

    override fun displayResult(base: String, to: String, value: String) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(getString(R.string.Result))
        builder.setMessage(getString(R.string.result_message, base, value, to))
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun displayError() {
        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun displayAppend(value: String) {
        result.text = value

    }

    override fun displayReset(value: String) {
        result.text = value

    }
}



