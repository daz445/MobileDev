package ru.dmitryzyrynov.lab4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firstobj :EditText = findViewById(R.id.firstnum)
        val secobj :EditText= findViewById(R.id.secnum)
        val resobj = findViewById<TextView>(R.id.tvResult)


        val button_plus: Button = findViewById(R.id.plus)
        button_plus.setOnClickListener {
            val firsttext=firstobj.text.toString().trim()
            val sectext = secobj.text.toString().trim()
            resobj.text = firsttext + " + " + sectext + " = " +(firsttext.toInt() + sectext.toInt()).toString()
        }

        val button_min: Button = findViewById(R.id.min)
        button_min.setOnClickListener {
            val firsttext=firstobj.text.toString().trim()
            val sectext = secobj.text.toString().trim()
            resobj.text = firsttext + " - " + sectext + " = " + (firsttext.toInt() - sectext.toInt()).toString()
        }

        val button_umn: Button = findViewById(R.id.umn)
        button_umn.setOnClickListener {
            val firsttext=firstobj.text.toString().trim()
            val sectext = secobj.text.toString().trim()
            resobj.text = firsttext + " * " + sectext + " = " + (firsttext.toInt() * sectext.toInt()).toString()
        }

        val button_delit: Button = findViewById(R.id.delit)
        button_delit.setOnClickListener {
            val firsttext=firstobj.text.toString().trim()
            val sectext = secobj.text.toString().trim()
            if(sectext=="0"){
                resobj.text ="Нельзя делить на 0"
            }
            else{
            resobj.text =  firsttext + " / " + sectext + " = " + (firsttext.toInt() / sectext.toInt()).toString()
            }
        }
    }
}