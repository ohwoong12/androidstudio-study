package com.cookandroid.kiosk_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.model.MenuItem

class MenuFragment : Fragment() {

    companion object {
        fun newInstance(category: String): MenuFragment {
            val fragment = MenuFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)

        val menuRecyclerView = view.findViewById<RecyclerView>(R.id.menuRecyclerView)
        val cartRecyclerView = view.findViewById<RecyclerView>(R.id.cartRecyclerView)
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)

        val category = arguments?.getString("category") ?: "커피"
        val menuList = getDummyMenu(category)

        menuRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        menuRecyclerView.adapter = MenuAdapter(menuList, object : MenuAdapter.OnItemClickListener {
            override fun onItemClick(item: MenuItem) {
                // 옵션 다이얼로그 띄우기 → 옵션 선택 후 장바구니에 추가
                PersonalOptionDialog(requireContext()) { option ->
                    CartManager.addItem(item, option)  // 선택한 옵션과 함께 장바구니 추가
                    cartRecyclerView.adapter?.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "${item.name} 담김", Toast.LENGTH_SHORT).show()
                }.show()
            }
        })

        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = CartAdapter(CartManager.cartItems) {
            cartRecyclerView.adapter?.notifyDataSetChanged()
        }

        btnCheckout.setOnClickListener {
            val intent = Intent(requireContext(), CheckoutActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun getDummyMenu(category: String): List<MenuItem> {
        return when (category) {
            "커피" -> listOf(
                MenuItem("아메리카노", 3000, R.drawable.americano),
                MenuItem("카페라떼", 3500, R.drawable.caffee_latte),
                MenuItem("에스프레소", 3900, R.drawable.espresso),
                MenuItem("콜드브루", 3900, R.drawable.cold_brew),
                MenuItem("흑당콜드브루", 3900, R.drawable.hukdang_cold_brew),
            )

            "라떼" -> listOf(
                MenuItem("녹차라떼", 4200, R.drawable.green_tea_latte),
                MenuItem("고구마라떼", 4500, R.drawable.sweet_potato_latte),
                MenuItem("초코라떼", 4500, R.drawable.choco_latte),
                MenuItem("바닐라라떼", 4500, R.drawable.vanilla_latte),
                MenuItem("카라멜라떼", 4500, R.drawable.caramel_latte),
            )

            "음료" -> listOf(
                MenuItem("자몽 에이드", 3800, R.drawable.grapefruit_ade),
                MenuItem("레몬 에이드", 3500, R.drawable.lemon_ade),
                MenuItem("청포도 에이드", 3500, R.drawable.green_grapes_ade),
                MenuItem("초코쿠키 쉐이크", 3500, R.drawable.choco_cookie_shake),
                MenuItem("복숭아 아이스티", 3500, R.drawable.peach_ice_tea)
            )

            "디저트" -> listOf(
                MenuItem("마카롱", 4500, R.drawable.macaroon),
                MenuItem("티라미수", 4500, R.drawable.tiramisu),
                MenuItem("고구마 케이크", 4500, R.drawable.sweet_potato_cake),
                MenuItem("크로플", 4500, R.drawable.crople),
                MenuItem("아이스박스", 4500, R.drawable.icebox)
            )

            else -> emptyList()
        }
    }
}
