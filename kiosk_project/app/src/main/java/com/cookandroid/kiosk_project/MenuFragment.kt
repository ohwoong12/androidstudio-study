package com.cookandroid.kiosk_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.kiosk_project.R
import com.cookandroid.kiosk_project.MenuAdapter
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.menuRecyclerView)

        val category = arguments?.getString("category") ?: "커피"
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = MenuAdapter(getDummyMenu(category))

        return view
    }

    private fun getDummyMenu(category: String): List<MenuItem> {
        return listOf(
            MenuItem("$category 메뉴1", 3000, R.drawable.ic_launcher_foreground),
            MenuItem("$category 메뉴2", 3500, R.drawable.ic_launcher_foreground),
            MenuItem("$category 메뉴3", 4000, R.drawable.ic_launcher_foreground)
        )
    }
}
