package com.example.wishlist

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: MutableList<Item>
    private lateinit var adapter: AdapterClass
    private lateinit var toolbar: MaterialToolbar

    private lateinit var editTextName: EditText
    private lateinit var editPrice:EditText
    private lateinit var editTextLink:EditText
    private lateinit var buttonAdd:View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        editTextName = findViewById(R.id.editTextName)
        editPrice = findViewById(R.id.editTextPrice)
        editTextLink = findViewById(R.id.editTextLink)
        buttonAdd = findViewById(R.id.buttonAdd)

        dataList = mutableListOf()

        adapter = AdapterClass(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            addItem()
        }

    }
    private fun addItem(){
        val name = editTextName.text.toString()
        val price = editPrice.text.toString()
        val link = editTextLink.text.toString()

        val newItem = Item(name,price,link)
        dataList.add(newItem)

        adapter.notifyItemInserted(dataList.size-1)

        editTextName.text.clear()
        editPrice.text.clear()
        editTextLink.text.clear()
    }
}

