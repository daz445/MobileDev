package ru.dmitryzyrynov.lab10

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

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
        val backcolors = listOf<SeekBar>(
            findViewById(R.id.Rback) ,
            findViewById(R.id.Gback),
            findViewById(R.id.Bback)
        )
        val textcolors = listOf<SeekBar>(
            findViewById(R.id.Rtext),
            findViewById(R.id.Gtext),
            findViewById(R.id.Btext)
        )

        val buttoncolors = listOf<SeekBar>(
            findViewById(R.id.Rbutton),
            findViewById(R.id.Gbutton),
            findViewById(R.id.Bbutton)
        )
        val textmes = findViewById<EditText>(R.id.mess_text)
        val buttonmes = findViewById<EditText>(R.id.text_button)
        setRandomSeekBar(backcolors,textcolors,buttoncolors)
        val button_show = findViewById<Button>(R.id.send_message)
        button_show.setOnClickListener {
            val clMain = findViewById<ConstraintLayout>(R.id.main)
            val sb = Snackbar.make(clMain, textmes.text.toString(), Snackbar.LENGTH_SHORT)
            sb.setBackgroundTint(Color.rgb(
                backcolors[0].progress,
                backcolors[1].progress,
                backcolors[2].progress)
            )
            sb.setTextColor(Color.rgb(
                textcolors[0].progress,
                textcolors[1].progress,
                textcolors[2].progress))
            if (buttonmes.text.toString() != ""){
                sb.setAction(buttonmes.text.toString()) {
                // Действия при нажатии на кнопку внутри сообщения
            }}
            sb.setActionTextColor(Color.rgb(
                buttoncolors[0].progress,
                buttoncolors[1].progress,
                buttoncolors[2].progress))
            sb.show()


        }


    }
}
fun setRandomSeekBar(backcolors:List<SeekBar>,textcolors:List<SeekBar>,buttoncolors:List<SeekBar>){
    for(Bar in backcolors + textcolors + buttoncolors){
        Bar.progress = Random.nextInt(0,255)
    }
}