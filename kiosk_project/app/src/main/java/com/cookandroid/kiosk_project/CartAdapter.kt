package com.cookandroid.kiosk_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.model.CartItem

// 장바구니에 담긴 항목들을 표시해주는 어댑터
class CartAdapter(
    private val cartItems: List<CartItem>,       // 장바구니 항목 리스트
    private val onCartChanged: () -> Unit         // 수량 변경 등 변경 시 UI 갱신 콜백
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    // 각 항목을 위한 ViewHolder 정의
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // UI 요소들 연결
        val name = view.findViewById<TextView>(R.id.cartItemName)
        val price = view.findViewById<TextView>(R.id.cartItemPrice)
        val quantity = view.findViewById<TextView>(R.id.cartItemQuantity)

        val btnIncrease = view.findViewById<ImageView>(R.id.btnIncrease)
        val btnDecrease = view.findViewById<ImageView>(R.id.btnDecrease)
        val btnRemove = view.findViewById<ImageView>(R.id.btnRemove)

        // 옵션 텍스트뷰
        val optionShot = view.findViewById<TextView>(R.id.cartItemOptionShot)
        val optionIce = view.findViewById<TextView>(R.id.cartItemOptionIce)
        val optionSugar = view.findViewById<TextView>(R.id.cartItemOptionSugar)
        val optionCup = view.findViewById<TextView>(R.id.cartItemOptionCup)

        // 데이터를 UI에 바인딩
        fun bind(item: CartItem) {
            name.text = item.menuItem.name
            price.text = "${item.menuItem.price * item.quantity}원"
            quantity.text = item.quantity.toString()

            // 옵션 정보 표시
            optionShot.text = "샷 추가: ${item.personalOption.shot}"
            optionIce.text = "얼음: ${item.personalOption.ice}"
            optionSugar.text = "당도: ${item.personalOption.sugar}"
            optionCup.text = "컵: ${item.personalOption.cup}"

            // 수량 증가
            btnIncrease.setOnClickListener {
                CartManager.increaseQuantity(item)
                notifyItemChanged(adapterPosition)
                onCartChanged()
            }

            // 수량 감소
            btnDecrease.setOnClickListener {
                CartManager.decreaseQuantity(item)
                notifyItemChanged(adapterPosition)
                onCartChanged()
            }

            // 항목 제거
            btnRemove.setOnClickListener {
                CartManager.removeItem(item.menuItem)
                notifyDataSetChanged()
                onCartChanged()
            }
        }
    }

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    // 항목 개수 반환
    override fun getItemCount(): Int = cartItems.size

    // 데이터 바인딩 실행
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }
}
