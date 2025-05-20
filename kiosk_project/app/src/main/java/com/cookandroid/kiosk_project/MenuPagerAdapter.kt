package com.cookandroid.kiosk_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MenuPagerAdapter(activity: FragmentActivity, private val categories: List<String>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return MenuFragment.newInstance(categories[position])
    }
}
