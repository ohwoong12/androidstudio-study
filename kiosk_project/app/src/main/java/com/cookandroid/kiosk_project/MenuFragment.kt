package com.cookandroid.kiosk_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.R

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.menuRecyclerView)

        val category = arguments?.getString("category") ?: "커피"
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = MenuAdapter(getDummyMenuList(category))

        return view
    }

    private fun getDummyMenuList(category: String): List<MenuItem> {
        return when (category) {
            "커피" -> listOf(
                MenuItem("아메리카노", 3000, R.drawable.americano),
                MenuItem("카페라떼", 3500, R.drawable.latte)
            )
            "라떼" -> listOf(MenuItem("바닐라라떼", 3800, R.drawable.vanilla))
            else -> listOf(MenuItem("기본 메뉴", 3000, R.drawable.ic_launcher_foreground))
        }
    }
}
