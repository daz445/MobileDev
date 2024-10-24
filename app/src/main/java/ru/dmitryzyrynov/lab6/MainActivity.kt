package ru.dmitryzyrynov.lab6

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import kotlin.math.sqrt

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
        val plane_a : EditText = findViewById(R.id.plane_a)
        val plane_b : EditText = findViewById(R.id.plane_b)
        val plane_c : EditText = findViewById(R.id.plane_c)
        val ans : TextView = findViewById(R.id.textView7)


        plane_a.doAfterTextChanged {

            onEdit(
                plane_a.text.toString().trim(),
                plane_b.text.toString().trim(),
                plane_c.text.toString().trim(),
                ans
            )

        }

        plane_b.doAfterTextChanged {

            onEdit(
                plane_a.text.toString().trim(),
                plane_b.text.toString().trim(),
                plane_c.text.toString().trim(),
                ans
            )
        }

        plane_c.doAfterTextChanged {

            onEdit(
                plane_a.text.toString().trim(),
                plane_b.text.toString().trim(),
                plane_c.text.toString().trim(),
                ans
            )
        }
    }
    fun onEdit(a:String,b:String,c:String, ans: TextView){


        try {
            val A = a.toInt()
            val B = b.toInt()
            val C = c.toInt()
            val D = B*B-4*A*C
            if (D==0) {
                val x =- B / 2*A
                ans.text = "Найден 1 корень\n$x"
            }
            else if(D>0){
                val x1 = (-B + Math.sqrt(D.toDouble()) )/(2*A)
                val x2 = (-B - Math.sqrt(D.toDouble()) )/(2*A)
                ans.text = "Найдено 2 кореня\n$x1\n$x2"
            }
            else{
                ans.text ="Корней нет"
            }
        }
        catch (e:Exception){
            ans.text = "Продолжайте вводить"
        }





        }


    }
