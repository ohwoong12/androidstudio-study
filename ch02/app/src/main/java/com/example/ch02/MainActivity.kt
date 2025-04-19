package com.example.ch02

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var naver_button: Button //멤버변수(전역변수)는 선언하면서 초기화
    lateinit var emergency_button: Button
    lateinit var kakao_button: Button
    lateinit var exit_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  //앱이 전체 화면을 차지하도록 하고, 상태바와 네비게이션바를 투명하게 만드는 역할
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        naver_button = findViewById(R.id.naver_button)
        //val naver_button=findViewById<Button>(R.id.naver_button)
        emergency_button=findViewById(R.id.emergency_button)
        kakao_button=findViewById(R.id.kakao_button)
        exit_button=findViewById(R.id.exit_button)
        title="CH02 TITLE"

        naver_button.setOnClickListener() {
            //Toast.makeText(applicationContext,"버튼을 눌렀어요",Toast.LENGTH_SHORT).show()
            Toast
                .makeText(this, "NAVER TOAST", Toast.LENGTH_SHORT)
                .show()

            var naver_intent =Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
            startActivity(naver_intent)
        }

        emergency_button.setOnClickListener() {
            Toast
                .makeText(this, "EMERGENCY TOAST", Toast.LENGTH_SHORT)
                .show()
        }

        kakao_button.setOnClickListener() {
            Toast
                .makeText(this, "KAKAO TOAST", Toast.LENGTH_SHORT)
                .show()
        }

        exit_button.setOnClickListener(){
            finish()    //한 액티비티만 존재하는 경우 사용
            //finishAffinity()    //여러 액티비티가 존재하는데 종료하는 경우 사용
        }
    }
}