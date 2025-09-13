package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: MutableList<Item>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>(){
    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView){
        val rv_item_name: TextView = itemView.findViewById(R.id.item_name)
        val rv_item_price: TextView = itemView.findViewById(R.id.item_price)
        val rv_item_store: TextView = itemView.findViewById(R.id.item_store)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rv_item_name.text = currentItem.item_name
        holder.rv_item_price.text = currentItem.item_price
        holder.rv_item_store.text = currentItem.item_store

        holder.itemView.setOnClickListener {
            try {
                var url = currentItem.item_store

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://$url"
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for ${currentItem.item_name}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(it.context, "Cannot open link for ${currentItem.item_name}", Toast.LENGTH_LONG).show()
            }
        }
    }
}