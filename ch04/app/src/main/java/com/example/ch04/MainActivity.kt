package com.example.ch04

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 전역변수 선언
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var checkAgree: CheckBox
    lateinit var radioGroup1: RadioGroup
    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton
    lateinit var radioButton3: RadioButton
    lateinit var oKBtn: Button
    lateinit var petImg: ImageView

    // onCreate는 앱의 진입점
    override fun onCreate(savedInstanceState: Bundle?) {    // Nullability in type system
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)  // 레이아웃 정의
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "애완동물 사진 보기"    // 앱 상단 이름

        // 생성한 전역변수를 xml내의 이름과 매칭시키기
        text1 = findViewById(R.id.Agree_Text)
        checkAgree = findViewById(R.id.Start_CheckBox)
        text2 = findViewById(R.id.PreferPet_Text)
        radioGroup1 = findViewById(R.id.Pet_RadioGroup)
        radioButton1 = findViewById(R.id.Pet_Dog)
        radioButton2 = findViewById(R.id.Pet_Cat)
        radioButton3 = findViewById(R.id.Pet_Fox)
        oKBtn = findViewById(R.id.CompleteSelect_Button)
        petImg = findViewById(R.id.Pet_ImageView)

        //setOnCheckedChangeListener는 체크박스의 상태 변화를 감지하는 리스너
        //View.VISIBLE: 뷰를 보이게, View.GONE: 뷰 안보이게
        //아래 코드는 시작함 체크박스를 누르면 밑에 항목이 보이게, 다시 누르면 안보이게 구현한 기능
        checkAgree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                text2.visibility = View.VISIBLE
                radioGroup1.visibility = View.VISIBLE
                oKBtn.visibility = View.VISIBLE
                petImg.visibility = View.VISIBLE
            } else {
                text2.visibility = View.GONE
                radioGroup1.visibility = View.GONE
                oKBtn.visibility = View.GONE
                petImg.visibility = View.GONE
            }
        }

        // 선택 완료 버튼을 눌렀을 때, 무슨 라디오버튼이 눌렸는지 체크한 후 해당 라디오버튼에 맞는 사진을 출력
        // 아무것도 선택 안했다면 토스트 메시지 출력
        oKBtn.setOnClickListener {
            if (radioButton1.isChecked) {
                //Toast.makeText(this, "Dog 선택", Toast.LENGTH_SHORT).show() // 테스트 코드
                petImg.setImageResource(R.drawable.golden)
                petImg.visibility = View.VISIBLE
            } else if (radioButton2.isChecked) {
                petImg.setImageResource(R.drawable.fold)
                petImg.visibility = View.VISIBLE
            } else if (radioButton3.isChecked) {
                petImg.setImageResource(R.drawable.fox)
                petImg.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "아무것도 선택안함", Toast.LENGTH_SHORT).show()
            }
        }
    }
}