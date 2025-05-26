package com.cookandroid.kiosk_project

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        // 결제 버튼 클릭 시 동작하는 코드
        val btnPay = findViewById<Button>(R.id.btn_StartPay)
        btnPay.setOnClickListener {
            showPeopleCountDialog()
        }

    }

    /**
     * 처음에 인원 수를 정하여 금액을 나누는 다이얼로그
     */
    private fun showPeopleCountDialog() {
        val editText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            //hint = "예: 2"
        }

        AlertDialog.Builder(this)
            .setTitle("결제 인원 수")
            .setMessage("몇 명이 나눠서 결제하나요?")
            .setView(editText)
            .setPositiveButton("확인") { _, _ ->
                val peopleCount = editText.text.toString().toIntOrNull()

                if (peopleCount != null && peopleCount > 0) {
                    val perPersonAmount = CartManager.getTotalPrice() / peopleCount
                    showPaymentSelectDialog(perPersonAmount)
                } else {
                    Toast.makeText(this, "유효한 인원 수를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    /**
     * 현금과 카드 중에 결제 수단을 선택하게 해주는 함수
     */
    private fun showPaymentSelectDialog(perPersonAmount: Int) {
        val methods = arrayOf("현금", "카드")

        AlertDialog.Builder(this)
            .setTitle("결제 수단 선택")
            .setItems(methods) { _, which ->
                when (which) {
                    0 -> {
                        // 현금 결제 선택 시 완료 메시지 출력
                        AlertDialog.Builder(this)
                            .setMessage("현금 결제가 완료되었습니다.")
                            .setPositiveButton("확인") { _, _ -> finish() }
                            .show()
                        CartManager.clearCart() // 결제 완료 시 장바구니 초기화
                        finish()
                    }

                    1 -> {
                        // 카드 선택 → 금액 입력 다이얼로그로 이동
                        showCardPaymentDialog(perPersonAmount)
                    }
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    /**
     * 카드 결제시 처음에 입력한 인원 별로 나눠진 금액을 결제하는 다이얼로그
     */
    private fun showCardPaymentDialog(perPersonAmount: Int) {
        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = "₩ $perPersonAmount 이상 입력"
        }

        AlertDialog.Builder(this)
            .setTitle("카드 결제 금액 입력")
            .setMessage("₩ $perPersonAmount 이상 입력해주세요.")
            .setView(input)
            .setPositiveButton("결제") { _, _ ->
                val enteredAmount = input.text.toString().toIntOrNull()
                if (enteredAmount != null && enteredAmount >= perPersonAmount) {
                    AlertDialog.Builder(this)
                        .setMessage("카드 결제가 완료되었습니다.")
                        .setPositiveButton("확인") { _, _ -> finish() }
                        .show()
                    CartManager.clearCart() // 결제 완료 시 장바구니 초기화
                    finish()
                } else {
                    Toast.makeText(this, "금액이 부족합니다.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

}