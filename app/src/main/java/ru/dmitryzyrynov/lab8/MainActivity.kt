package ru.dmitryzyrynov.lab8
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
val state = 0


class MainActivity : AppCompatActivity() {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val metr: TextView = findViewById(R.id.metr)
        val inch: TextView = findViewById(R.id.inch)
        val yeard: TextView = findViewById(R.id.yeard)
        val feet: TextView = findViewById(R.id.feet)
        save(metr,inch,yeard,feet)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val metr: TextView = findViewById(R.id.metr)
        val inch: TextView = findViewById(R.id.inch)
        val yeard: TextView = findViewById(R.id.yeard)
        val feet: TextView = findViewById(/* id = */ R.id.feet)
        metr.text = parametr.metr
        inch.text = parametr.inch
        yeard.text = parametr.yeard
        feet.text = parametr.feet
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val metr: TextView = findViewById(R.id.metr)
        val inch: TextView = findViewById(R.id.inch)
        val yeard: TextView = findViewById(R.id.yeard)
        val feet: TextView = findViewById(R.id.feet)


        metr.doAfterTextChanged {
            var isNone = notNul(metr,inch,yeard,feet)
            if(metr.isInputMethodTarget && isNone )
            {
                var floarmert = metr.text.toString().trim().toFloat()
                inch.text = (floarmert/39.37).toString()
                yeard.text = (floarmert/1.09361329834).toString()
                feet.text = (floarmert/3.28).toString()

            }
        }

        inch.doAfterTextChanged {
            var isNone = notNul(metr,inch,yeard,feet)
            if(inch.isInputMethodTarget && isNone)
            {
                var floatinch = inch.text.toString().trim().toFloat()
                metr.text = (floatinch*39.37).toString()
                yeard.text = (floatinch/0.9144).toString()
                feet.text = (floatinch/3.28).toString()

            }
        }

        yeard.doAfterTextChanged {
            var isNone = notNul(metr,inch,yeard,feet)
            if(yeard.isInputMethodTarget && isNone)
            {
                var floatyeard = yeard.text.toString().trim().toFloat()
                inch.text = (floatyeard/3.37).toString()
                metr.text = (floatyeard/3.29834).toString()
                feet.text = (floatyeard/2.28).toString()

            }
        }

        feet.doAfterTextChanged {
            var isNone = notNul(metr,inch,yeard,feet)
            if(feet.isInputMethodTarget && isNone)
            {
                var floatfeet = metr.text.toString().trim().toFloat()
                inch.text = (floatfeet/39.37).toString()
                yeard.text = (floatfeet/4).toString()
                metr.text = (floatfeet/38).toString()

            }
        }


    }
}
fun notNul(metr:TextView,inch:TextView,yeard:TextView,feet:TextView): Boolean {
    return (metr.text!="" && inch.text!="" && yeard.text!="" && feet.text!="")
}


fun save(metr:TextView,inch:TextView,yeard:TextView,feet:TextView) {
    parametr.metr = metr.text.toString()
    parametr.inch = inch.text.toString()
    parametr.yeard = yeard.text.toString()
    parametr.feet = feet.text.toString()
}