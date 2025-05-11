package com.cookandroid.ch09_project7_3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // 전역 변수 선언
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var btn1: Button
    lateinit var dlgEdtName: EditText
    lateinit var dlgEdtEmail: EditText
    lateinit var toastText: TextView
    lateinit var dialogView: View
    lateinit var toastView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // xml 파일의 id를 코틀린 파일과 연결
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
            // dialog1.xml 파일을 inflate하여 dialogView에 대입
            dialogView = View.inflate(this@MainActivity, R.layout.dialog1, null)

            var dlg = AlertDialog.Builder(this@MainActivity)

            dlg.setTitle("사용자 정보 입력")
            dlg.setIcon(R.drawable.ic_launcher_foreground)
            dlg.setView(dialogView)

            // 확인 버튼 눌렀을 때 동작하는 코드
            dlg.setPositiveButton("확인") { dialog, which ->
                // 대화상자의 두 EditText에 접근
                dlgEdtName = dialogView.findViewById(R.id.dlgEdit1)
                dlgEdtEmail = dialogView.findViewById(R.id.dlgEdtit2)

                tvName.text = dlgEdtName.text.toString()
                tvEmail.text = dlgEdtEmail.text.toString()

            }

            // 취소 버튼 눌렀을 때 동작하는 코드
            dlg.setNegativeButton("취소") { dialog, which ->
                var toast = Toast(this@MainActivity)
                toastView = View.inflate(this@MainActivity, R.layout.toast1, null)  // toast의 xml 파일을 inflate하여 toastView에 대입
                toastText = toastView.findViewById(R.id.toastText1)
                toastText.text = "취소했습니다"
                toast.view = toastView
                toast.show()
            }
            dlg.show()
        }
    }
}