package ru.dmitryzyrynov.lab7

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

var type = 0
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
        val Button_Eateble : Button = findViewById(R.id.button)
        val Button_NoEateble : Button = findViewById(R.id.button2)
        val Image: ImageView = findViewById(R.id.imv)
        val Ans: TextView = findViewById(R.id.ans)
        var ErorCheck = 0
        var CorrCheck = 0


        Button_Eateble.setOnClickListener {
            if (type == 0){
                CorrCheck +=1
            }
            else{
                ErorCheck+=1
            }
            val d = getResources().getIdentifier( generateImg(),"drawable",getPackageName())
            Image.setImageResource(d)
            Ans.text = "Верных ответов: $CorrCheck\nНеправильных ответов: $ErorCheck"
        }
        Button_NoEateble.setOnClickListener {
            if (type == 1){
                CorrCheck +=1
            }
            else{
                ErorCheck+=1
            }
            val d = getResources().getIdentifier( generateImg(),"drawable",getPackageName())
            Image.setImageResource(d)
            Ans.text = "Верных ответов: $CorrCheck\nНеправильных ответов: $ErorCheck"
        }

    }
    fun generateImg() :String{
        val typeindex = Random.nextInt(0,2)
        type = typeindex
        val typefood = arrayOf("food", "sport")
        val num = String.format("%02d",Random.nextInt(1,21))
        return typefood[typeindex]+num
    }

}