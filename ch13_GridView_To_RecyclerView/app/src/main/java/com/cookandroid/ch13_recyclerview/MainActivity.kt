package com.cookandroid.ch13_recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 리사이클러뷰 연결
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // 데이터 불러오기
        val data: MutableList<Memo> = loadData()

        // 어댑터 생성 및 데이터 연결
        val adapter = CustomAdapter()
        adapter.listData = data

        // 리사이클러뷰에 어댑터와 리니어레이아웃매니저 설정
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * 리사이클러뷰에 표시할 데이터 생성 함수
     * 제목과 현재 시간, Memo 객체 생성 후 리스트에 추가함
     */
    fun loadData(): MutableList<Memo> {
        val data: MutableList<Memo> = mutableListOf()
        for (no in 1..100) {
            val title = "RecyclerView ${no}"
            //var num1= "number ${no}"
            val date = System.currentTimeMillis()
            var memo = Memo(no, title, date)
            data.add(memo)
        }
        return data
    }
}