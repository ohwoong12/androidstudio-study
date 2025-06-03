package com.cookandroid.kiosk_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.kiosk_project.MenuAdapter
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // xml 레이아웃에서 TabLayout과 ViewPager 연결
        tabLayout = findViewById(R.id.store_fragment_tablayout)
        viewPager = findViewById(R.id.viewPager)

        // 메뉴 카테고리 목록
        val categories = listOf("커피", "라떼", "음료", "디저트")
        viewPager.adapter = MenuPagerAdapter(this, categories)  // Viewpager에 카테고리 별 프래그먼트를 제공하는 어댑터 연결

        // 탭과 ViewPager 연동
        // 탭 클릭 시 해당 카테고리 프래그먼트로 이동
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = categories[position]
        }.attach()
    }
}