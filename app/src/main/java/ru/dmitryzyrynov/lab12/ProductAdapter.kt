package ru.dmitryzyrynov.lab12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product, Boolean) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val addedToCart = mutableSetOf<Product>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val buttonAddToCart: ImageButton = itemView.findViewById(R.id.buttonAddToCart)

        fun bind(product: Product) {
            productImage.setImageResource(product.imageResId)
            productName.text = product.name
            productPrice.text = "Цена: ${product.price} руб."
            val isAdded = addedToCart.contains(product)
            updateButton(isAdded)

            buttonAddToCart.setOnClickListener {
                val isAdded = addedToCart.contains(product)
                if (isAdded) {
                    addedToCart.remove(product)
                    updateButton(false)
                    onProductClick(product, false)
                } else {
                    addedToCart.add(product)
                    updateButton(true)
                    onProductClick(product, true)
                }
            }
        }

        private fun updateButton(isAdded: Boolean) {
            buttonAddToCart.setImageResource(if (isAdded) R.drawable.shopping_cart_add else R.drawable.shopping_cart )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}
