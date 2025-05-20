// MenuAdapter.kt
package com.cookandroid.kiosk_project 

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.R
import com.cookandroid.kiosk_project.model.MenuItem

class MenuAdapter(private val menuList: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MenuItem) {
            view.findViewById<ImageView>(R.id.menuImage).setImageResource(item.imageResId)
            view.findViewById<TextView>(R.id.menuName).text = item.name
            view.findViewById<TextView>(R.id.menuPrice).text = "${item.price}원"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
    }
}
