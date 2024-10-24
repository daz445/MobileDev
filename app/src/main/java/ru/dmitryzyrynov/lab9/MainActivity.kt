package ru.dmitryzyrynov.lab9

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val agree = findViewById<CheckBox>(R.id.agree)
        val login = findViewById<Button>(R.id.Log_in)
        val spinner = findViewById<Spinner>(R.id.country)
        val disagree = findViewById<Button>(R.id.disagree)
        val code_regino = findViewById<TextView>(R.id.codtelephone)
        val telehone = findViewById<EditText>(R.id.telephone)
        val items = arrayOf(getString(R.string.rus), getString(R.string.bel),
            getString(R.string.cin))


        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                code_regino.text = resources.getText(R.string.Rus_code)

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                when(position){
                    0 ->{telehone.hint = resources.getText(R.string.Rus_telephone_hint)
                    code_regino.text = resources.getText(R.string.Rus_code)}
                    1 ->{telehone.hint = resources.getText(R.string.bel_telephone_hint)
                        code_regino.text = resources.getText(R.string.bel_code)}
                    2->{telehone.hint = resources.getText(R.string.cin_telephone_hint)
                    code_regino.text = resources.getText(R.string.cin_code)}

                }

            }
        }




        disagree.setOnClickListener {
            finish()
            System.out.close()
        }
        agree.setOnCheckedChangeListener { buttonView, isChecked ->

            if (agree.isChecked) {
                login.backgroundTintList = ContextCompat.getColorStateList(this,R.color.green)
                login.isEnabled = true
            }
            else{
                login.backgroundTintList = ContextCompat.getColorStateList(this,R.color.grey)
                login.isEnabled = false
            }

        }







    }
}