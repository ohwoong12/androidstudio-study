package com.cookandroid.kiosk_project

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 결제 화면 액티비티
class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // RecyclerView 초기화
        val recyclerView = findViewById<RecyclerView>(R.id.checkoutRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 어댑터 연결: 수량 변경 시 총금액 갱신
        recyclerView.adapter = CartAdapter(CartManager.cartItems) {
            recyclerView.adapter?.notifyDataSetChanged()
            findViewById<TextView>(R.id.totalPriceText).text =
                "총 금액: ${CartManager.getTotalPrice()}원"
        }

        // 최초 총 금액 표시
        val totalPrice = CartManager.getTotalPrice()
        findViewById<TextView>(R.id.totalPriceText).text = "총 금액: ${totalPrice}원"
    }
}
