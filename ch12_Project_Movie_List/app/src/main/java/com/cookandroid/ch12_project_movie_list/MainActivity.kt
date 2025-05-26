package com.cookandroid.ch12_project_movie_list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // activity_main.xml에 있는 ListView를 연결
        val list = findViewById<ListView>(R.id.list)

        // 커스텀 어댑터 생성 및 ListView에 연결
        val vAdapter = MyViewAdapter(this)
        list.adapter = vAdapter
    }

    // BaseAdapter를 상속받아 MyViewAdapter라는 커스텀 어댑터 생성
    inner class MyViewAdapter(var context: Context) : BaseAdapter() {
        // 각 영화 포스터를 나타내는 이미지 배열
        var images = arrayOf(
            R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10
        )

        // 영화 제목 배열
        var titles = arrayOf(
            "포틀 맨카인드", "파이널 데스티네이션", "Minecraft",
            "워킹맨", "산도르", "러브,데스 로봇",
            "미션 임파서블:파이널 레코딩", "머더봇 다이어리", "더 라스트 오브 어스", "백설공주"
        )

        // 항목 개수 리턴하는 함수
        override fun getCount(): Int {
            return images.size
        }

        // 특정 position의 데이터를 리턴하는 함수
        override fun getItem(position: Int): Any {
            return titles[position]
        }

        // 고유 ID를 리턴하는 함수 (id => 위치값)
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // 각 항목 뷰를 생성하여 반환하는 함수
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // 재사용 가능한 convertView가 없으면 새로 뷰 생성
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_listitem, parent, false)

            // 각 UI의 구성요소를 코틀린 파일과 연결
            val title = view.findViewById<TextView>(R.id.title)
            val rating = view.findViewById<TextView>(R.id.rating)
            val genre = view.findViewById<TextView>(R.id.genre)
            val releaseYear = view.findViewById<TextView>(R.id.releaseYear)
            val image = view.findViewById<ImageView>(R.id.image)

            // 데이터 바인딩
            title.text = titles[position]
            rating.text = "9.0"
            genre.text = "MOVIE"
            releaseYear.text = "2024"
            image.setImageResource(images[position])    // 이미지를 동적으로 변경

            // 포스터의 이미지를 클릭했을 때 동작하는 코드
            image.setOnClickListener {
                // 다이얼로그에 사용할 뷰 inflate
                val dialogView = View.inflate(this@MainActivity, R.layout.activity_dialog, null)
                val dlg = AlertDialog.Builder(this@MainActivity)
                val ivPoster = dialogView.findViewById<ImageView>(R.id.ivPoster)

                // 다이얼로그 설정
                ivPoster.setImageResource(images[position])
                dlg.setTitle(titles[position])                  // 제목
                dlg.setIcon(R.drawable.ic_launcher_foreground)  // 아이콘
                dlg.setView(dialogView)                         // 커스텀 뷰
                dlg.setNegativeButton("닫기", null)  // 닫기 버튼
                dlg.show()
            }

            return view
        }
    }
}