package ru.dmitryzyrynov.lab15

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ContactsAdapter
    private val contacts: MutableList<Content> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapter = ContactsAdapter(contacts, this)
        val listA = findViewById<RecyclerView>(R.id.list)
        listA.adapter = adapter

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Добавить")
            .setPositiveButton("ОК") { dialog, which ->
                // Здесь мы не добавляем элементы, это будет в onClick
            }
            .setNegativeButton("Отмена") { dialog, which ->
                dialog.dismiss()
            }

        val button: FloatingActionButton = findViewById(R.id.fab_add)
        button.setOnClickListener {
            // Создаем диалоговое окно и EditText каждый раз при нажатии
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog, null)
            val b1: EditText = dialogView.findViewById(R.id.dialog_prod)
            val b2: EditText = dialogView.findViewById(R.id.dialog_count)

            builder.setView(dialogView)
            builder.setPositiveButton("ОК") { dialog, which ->
                // Добавляем элемент только после того, как пользователь нажал "ОК"
                contacts.add(Content(b1.text.toString(), b2.text.toString()))
                adapter.notifyItemInserted(contacts.size - 1) // Уведомляем адаптер о добавлении
            }
            builder.show()
        }

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                contacts.removeAt(pos)
                adapter.notifyItemRemoved(pos)
            }
        }
        val swipeHelper = ItemTouchHelper(swipeCallback)
        swipeHelper.attachToRecyclerView(listA)

    }

}

data class Content(
        val prod: String,
        val count: String
        )

class ContactsAdapter(val contacts: MutableList<Content>, private val context: Context) :
    RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {
    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prod_text: TextView
        val count_text: TextView
        val card: CardView


        init {
            prod_text  = itemView.findViewById(R.id.prod_text)
            count_text = itemView.findViewById(R.id.count_text)
            card = itemView.findViewById(R.id.card_veiwe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.asset, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.prod_text.text = contacts[position].prod
        holder.count_text.text = contacts[position].count
        holder.card.setOnClickListener()
        {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Редактировать контакт")

            // Создаем EditText для ввода данных
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialog, null)
            val b1: EditText = dialogView.findViewById(R.id.dialog_prod)
            val b2: EditText = dialogView.findViewById(R.id.dialog_count)

            // Заполняем EditText текущими значениями
            b1.setText(contacts[position].prod)
            b2.setText(contacts[position].count)
            builder.setView(dialogView)
            builder.setPositiveButton("Сохранить") { dialog, which ->
                // Обновляем данные и уведомляем адаптер
                contacts[position] = Content(b1.text.toString(), b2.text.toString())
                notifyItemChanged(position)
            }
            builder.setNegativeButton("Отмена") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}