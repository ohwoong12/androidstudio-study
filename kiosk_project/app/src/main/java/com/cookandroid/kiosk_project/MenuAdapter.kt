package com.cookandroid.kiosk_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.model.MenuItem

/**
 * 메뉴 목록을 보여주는 RecyclerView의 어댑터 클래스
 */
class MenuAdapter(
    private val menuList: List<MenuItem>,       // 메뉴 항목 리스트
    private val listener: OnItemClickListener
) : // 클릭 이벤트 콜백 인터페이스
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    // 클릭 이벤트 처리를 위한 인터페이스
    interface OnItemClickListener {
        fun onItemClick(item: MenuItem)
    }

    // 각 항목에 대응하는 ViewHolder 정의
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val menuImage: ImageView = view.findViewById(R.id.menuImage)    // 메뉴 이미지
        private val menuName: TextView = view.findViewById(R.id.menuName)       // 메뉴 이름
        private val menuPrice: TextView = view.findViewById(R.id.menuPrice)     // 메뉴 가격

        /**
         * 데이터를 View에 Binding 하는 함수
         */
        fun bind(item: MenuItem) {
            menuImage.setImageResource(item.imageResId)
            menuName.text = item.name
            menuPrice.text = "${item.price}원"

            /**
             * 항목 클릭 시 리스너 콜백 호출
             */
            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    /**
     * ViewHolder 생성하는 함수
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    /**
     * 전체 항목 수를 반환하는 함수
     */
    override fun getItemCount(): Int = menuList.size

    /**
     * ViewHolder에 데이터 Binding
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
    }
}
