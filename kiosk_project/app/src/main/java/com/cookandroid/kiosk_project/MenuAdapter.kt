package com.cookandroid.kiosk_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.model.MenuItem

// 메뉴 목록을 RecyclerView에 표시해주는 어댑터
class MenuAdapter(
    private val menuList: List<MenuItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    // 클릭 이벤트 처리를 위한 인터페이스
    interface OnItemClickListener {
        fun onItemClick(item: MenuItem)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val menuImage: ImageView = view.findViewById(R.id.menuImage)
        private val menuName: TextView = view.findViewById(R.id.menuName)
        private val menuPrice: TextView = view.findViewById(R.id.menuPrice)

        fun bind(item: MenuItem) {
            menuImage.setImageResource(item.imageResId)
            menuName.text = item.name
            menuPrice.text = "${item.price}원"

            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
    }
}
