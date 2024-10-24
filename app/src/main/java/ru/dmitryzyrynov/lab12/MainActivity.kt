package ru.dmitryzyrynov.lab12

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

data class Product(val name: String, val price: String, val imageResId: Int)
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    private val products = listOf(
        Product("Товар 1", "10", R.drawable.food01),
        Product("Товар 2", "15", R.drawable.food02),
        Product("Товар 3", "20", R.drawable.food03)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter(products) { product, isAdded  ->
            val message = if (isAdded) {
                "Добавлено: ${product.name}"
            } else {
                "Удалено: ${product.name}"
            }
            val color = if (isAdded) Color.GREEN else Color.RED
            val clMain = findViewById<ConstraintLayout>(R.id.main)
            val sb = Snackbar.make(clMain, message, Snackbar.LENGTH_SHORT)
            sb.setBackgroundTint(color)

            sb.show()
        }

        recyclerView.adapter = productAdapter
    }

}

