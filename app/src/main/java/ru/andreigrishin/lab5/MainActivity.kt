package ru.andreigrishin.lab5

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets }
        val text1: TextView = findViewById(R.id.text1)
        val text2: TextView = findViewById(R.id.text2)
        val text3: TextView = findViewById(R.id.text3)
        val text4: TextView = findViewById(R.id.text4)
        val text5: TextView = findViewById(R.id.text5)
        val text6: TextView = findViewById(R.id.text6)
        val text7: TextView = findViewById(R.id.text7)
        val text8: TextView = findViewById(R.id.text8)
        val text9: TextView = findViewById(R.id.text9)
        recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)
    }

    override fun onResume() {
        super.onResume()
        val text1: TextView = findViewById(R.id.text1)
        val text2: TextView = findViewById(R.id.text2)
        val text3: TextView = findViewById(R.id.text3)
        val text4: TextView = findViewById(R.id.text4)
        val text5: TextView = findViewById(R.id.text5)
        val text6: TextView = findViewById(R.id.text6)
        val text7: TextView = findViewById(R.id.text7)
        val text8: TextView = findViewById(R.id.text8)
        val text9: TextView = findViewById(R.id.text9)
        text1.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text2.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text3.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text4.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text5.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text6.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text7.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text8.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})
        text9.setOnClickListener({recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)})

    }

    override fun onPause() {
        super.onPause()
        val text1: TextView = findViewById(R.id.text1)
        val text2: TextView = findViewById(R.id.text2)
        val text3: TextView = findViewById(R.id.text3)
        val text4: TextView = findViewById(R.id.text4)
        val text5: TextView = findViewById(R.id.text5)
        val text6: TextView = findViewById(R.id.text6)
        val text7: TextView = findViewById(R.id.text7)
        val text8: TextView = findViewById(R.id.text8)
        val text9: TextView = findViewById(R.id.text9)
        recolor(text1,text2,text3,text4,text5,text6,text7,text8,text9)
    }

    private fun recolor(text1: TextView,text2: TextView,text3: TextView,text4: TextView,text5: TextView,text6: TextView,text7: TextView,text8: TextView,text9: TextView)
    {
        val Texts = arrayOf(text1,text2,text3,text4,text5,text6,text7,text8,text9)
        val r = Random.nextInt(0,155)
        val g = Random.nextInt(0,155)
        val b = Random.nextInt(0,155)
        var i = 255
        for (t in Texts)
        {
            t.setBackgroundColor(Color.argb(i,r,g,b))
            i-=Random.nextInt(20,30)
        }
    }
}