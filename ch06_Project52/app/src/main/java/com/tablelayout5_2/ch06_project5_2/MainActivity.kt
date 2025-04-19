package com.tablelayout5_2.ch06_project5_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.BinderThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // laetinit internal로 쓰면 Kotlin의 권장 수식자 순서와 다르므로 변경
    // internal로 설정함으로써 같은 모듈(안드로이드 프로젝트 안에서만 접근 가능)
    internal lateinit var edit1: EditText
    internal lateinit var edit2: EditText
    internal lateinit var btnAdd: Button
    internal lateinit var btnSub: Button
    internal lateinit var btnMul: Button
    internal lateinit var btnDiv: Button
    internal lateinit var textResult: TextView
    internal lateinit var num1: String
    internal lateinit var num2: String
    internal var result: Int? = null    // Int형 변수이지만 null 값도 가능함, 초기값 null
    internal var numButtons = ArrayList<Button>(10)   // 숫자 버튼 10개를 저장할 배열 리스트 선언
    internal var numBtnIDs =    //숫자 버튼 10개의 id를 배열에 저장
        arrayOf(
            R.id.btnNum0,
            R.id.btnNum1,
            R.id.btnNum2,
            R.id.btnNum3,
            R.id.btnNum4,
            R.id.btnNum5,
            R.id.btnNum6,
            R.id.btnNum7,
            R.id.btnNum8,
            R.id.btnNum9
        )

    //internal var I: Int = 0 // 증가값 용도 Int형 변수 선언
    internal val zeroDiv = "계산 불가능"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //앱 제목
        title = "TableLayout 계산기"

        // xml파일에 있는 버튼들을 코틀린 파일과 연결
        edit1 = findViewById(R.id.edit1)
        edit2 = findViewById(R.id.edit2)
        btnAdd = findViewById(R.id.addBtn)
        btnSub = findViewById(R.id.subBtn)
        btnMul = findViewById(R.id.mulBtn)
        btnDiv = findViewById(R.id.divBtn)

        textResult = findViewById(R.id.numResult)

        // 더하기
        btnAdd.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            // 아무값도 넣지 않고 계산 버튼을 누르면 예외 처리를 통해서 메시지 출력
            try {
                result = Integer.parseInt(num1) + Integer.parseInt(num2)
            } catch (e: Exception) {
                if (num1 == "" && num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                }
            }

            // 미리 선언한 textResult 변수에 결과값을 String 형식으로 바꿔서 저장
            textResult.text = "계산 결과 : " + result.toString()
            //false setOnClickListener()에서는 필요 없기에 주석처리
        }

        // 빼기
        btnSub.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            // 아무값도 넣지 않고 계산 버튼을 누르면 예외 처리를 통해서 메시지 출력
            try {
                result = Integer.parseInt(num1) - Integer.parseInt(num2)
            } catch (e: Exception) {
                if (num1 == "" && num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                }
            }
            textResult.text = "계산 결과 : " + result.toString()
        }

        // 곱하기
        btnMul.setOnClickListener() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()

            // 아무값도 넣지 않고 계산 버튼을 누르면 예외 처리를 통해서 메시지 출력
            try {
                result = Integer.parseInt(num1) * Integer.parseInt(num2)
            } catch (e: Exception) {
                if (num1 == "" && num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                }
            }
            textResult.text = "계산 결과 : " + result.toString()
        }

        // 나누기
        btnDiv.setOnClickListener() {
            num1 = edit1.text.toString()    // 입력된 값을 String 형식으로 바꿔서 각 변수에 대입
            num2 = edit2.text.toString()

            // 아무값도 넣지 않고 계산 버튼을 누르면 예외 처리를 통해서 메시지 출력
            try {
                result = Integer.parseInt(num1) / Integer.parseInt(num2)
            } catch (e: Exception) {
                if (num1 == "" && num2 == "") {
                    Toast.makeText(applicationContext, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                }
                if (num1 == "0" || num2 == "0") {
                    Toast.makeText(applicationContext, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                    textResult.text = "계산 결과 : " + zeroDiv

                }
            }

            //0으로 나눌 시 계산 불가능 출력
            if (num1 == "0" || num2 == "0")
                textResult.text = "계산 결과 : " + zeroDiv
            else
                textResult.text = "계산 결과 : " + result.toString()
        }

        for (i in 0..9) {    // step1은 생략해도 기본값이라 코드의 간결성을 위해 삭제
            // 위젯 변수 열개에 10개의 버튼을 대입
            numButtons.add(findViewById(numBtnIDs[i])) // numButtons.add()는 리스트에 값을 추가하는 함수
        }

        for (i in 0 until numBtnIDs.size) { // size-1이 아닌 until이라는 코틀린 문법을 사용

            // 버튼이 눌렸을 때 실행되는 클릭 리스너 생성
            numButtons[i].setOnClickListener() {
                // Log.d("버튼테스트", "지금 ${numButtons[i].text} 버튼 눌림")    // 테스트 코드
                /*
                함수 생성으로 기존 코드 삭제
                if (edit1.isFocused == true) {
                    num1 = edit1.text.toString() + numButtons[i].getText()
                        .toString()   // 기존에 들어있던 edit.text.toString()에 지금 누른 numButtons[i]를 삽입

                    edit1.setText(num1) // 최종적으로 edit1에 수정
                } else if (edit2.isFocused == true) {
                    num2 = edit2.text.toString() + numButtons[i].getText().toString()
                    edit2.setText(num2)
                } else {
                    Toast.makeText(applicationContext, "먼저 EditText를 선택하세요", Toast.LENGTH_SHORT)
                        .show()
                }
                 */
                numberInput(numButtons[i])  // 함수 만들어서 편하게 사용
            }
        }
    }

    // 숫자 입력 처리 로직을 함수화
    fun numberInput(button: Button) {   // 지금 눌린 버튼을 매개변수로 전달
        if (edit1.isFocused == true) {
            num1 = edit1.text.toString() + button.text.toString()   // button.text로 눌린 숫자 가져옴
            edit1.setText(num1)
        } else if (edit2.isFocused == true) {
            num2 = edit2.text.toString() + button.text.toString()
            edit2.setText(num2)
        } else {
            Toast.makeText(applicationContext, "먼저 EditText를 선택하세요", Toast.LENGTH_SHORT).show()
        }
    }
}