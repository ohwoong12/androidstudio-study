package com.example.ch03

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // lateinit은 늦은 초기화 (변수만 먼저 선언한 후 초기값은 나중에 할당)
    // But 초기값 할당 전까지는 변수를 사용할 수 없음
    // 기본 자료형에는 사용 불가능 (String class는 사용 가능)
    lateinit var edit1: EditText
    lateinit var edit2: EditText
    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMul: Button
    lateinit var btnDiv: Button
    lateinit var btnrem: Button
    lateinit var textResult: TextView
    lateinit var num1: String
    lateinit var num2: String

    var result: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        title = "초간단 계산기"

        edit1 = findViewById(R.id.edit1)    //EditText 불러오기
        edit2 = findViewById(R.id.edit2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMul = findViewById(R.id.btnMul)
        btnDiv = findViewById(R.id.btnDiv)
        btnrem = findViewById(R.id.btnrem)
        textResult = findViewById(R.id.TextResult)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnAdd.setOnClickListener() {
            num1 = edit1.text.toString()    //editText에 입력한 값을 String으로 num1에 저장
            num2 = edit2.text.toString()
            try {
                result = num1.toFloat() + num2.toFloat()    //String 형식의 값을 Float형으로 변환한 후에 계산
            } catch (e: Exception) {
                if (num1 == "" || num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            if (result == null) textResult.text = "계산 결과: "
            else {
                textResult.text = "계산 결과: " + result.toString()
            }
        }

        btnSub.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            //result = Integer.parseInt(num1) - Integer.parseInt(num2)
            //result = num1.toFloat() - num2.toFloat()
            //textResult.text = "계산 결과: " + result.toString()

            try {
                result = num1.toFloat() - num2.toFloat()    //String 형식의 값을 Float형으로 변환한 후에 계산
            } catch (e: Exception) {
                if (num1 == "" || num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            if (result == null) textResult.text = "계산 결과: "
            else {
                textResult.text = "계산 결과: " + result.toString()
            }
        }

        btnMul.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            try {
                result = num1.toFloat() * num2.toFloat()    //String 형식의 값을 Float형으로 변환한 후에 계산
            } catch (e: Exception) {
                if (num1 == "" || num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            if (result == null) textResult.text = "계산 결과: "
            else {
                textResult.text = "계산 결과: " + result.toString()
            }
        }

        btnDiv.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            try {
                result = num1.toFloat() / num2.toFloat()    //String 형식의 값을 Float형으로 변환한 후에 계산
            } catch (e: Exception) {
                if (num1 == "" || num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            if (result == null) textResult.text = "계산 결과: "
            else {
                textResult.text = "계산 결과: " + result.toString()
            }
        }

        btnrem.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            try {
                result = num1.toFloat() % num2.toFloat()    //String 형식의 값을 Float형으로 변환한 후에 계산
            } catch (e: Exception) {
                if (num1 == "" || num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            if (result == null) textResult.text = "계산 결과: "
            else {
                textResult.text = "계산 결과: " + result.toString()
            }

        }
    }
}
