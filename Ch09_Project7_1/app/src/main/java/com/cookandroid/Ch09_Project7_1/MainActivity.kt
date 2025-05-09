package com.cookandroid.Ch09_Project7_1

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    /** 전역변수 선언 */
    lateinit var baseLayout: LinearLayout
    lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.baseLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        title = "배경색 바꾸기"
        baseLayout = findViewById(R.id.baseLayout)
        button1 = findViewById(R.id.btn1)
    }

    /** 옵션 메뉴를 등록하는 메소드 */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        mInflater.inflate(R.menu.menu1, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    /** 메뉴를 클릭했을 때 동작하는 메소드 */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemRed -> {
                baseLayout.setBackgroundColor(Color.RED)
                return true
            }

            R.id.itemGreen -> {
                baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }

            R.id.itemBlue -> {
                baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }

            R.id.subRotate -> {
                button1.rotation = 45f  // 45도 회전
                return true
            }

            R.id.subSize -> {
                button1.scaleX = 2f // 버튼 크기 확대
                return true
            }
        }

        return false
        //return super.onOptionsItemSelected(item)
    }
}