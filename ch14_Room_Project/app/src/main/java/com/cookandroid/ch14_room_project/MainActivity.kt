package com.cookandroid.ch14_room_project

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import androidx.lifecycle.Observer // LiveData observe 위해 추가

class MainActivity : AppCompatActivity() {

    // DB와 뷰 객체 선언
    private lateinit var db: AppDatabase
    private lateinit var editName: EditText
    private lateinit var editCount: EditText
    private lateinit var btnInit: Button
    private lateinit var btnInsert: Button
    private lateinit var btnSelect: Button
    private lateinit var resultContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Room DB 빌드
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-database"
        ).build()

        // XML에 정의된 뷰 연결
        editName = findViewById(R.id.editName)
        editCount = findViewById(R.id.editCount)
        btnInit = findViewById(R.id.btnInit)
        btnInsert = findViewById(R.id.btnInsert)
        btnSelect = findViewById(R.id.btnSelect)
        resultContainer = findViewById(R.id.resultContainer)

        // [초기화 버튼] 클릭 시 전체 데이터 삭제
        btnInit.setOnClickListener {
            lifecycleScope.launch {
                db.groupDao().deleteAll()  // DB 모든 데이터 삭제
                runOnUiThread {
                    clearResultContainer()  // 결과 뷰 영역 초기화
                    Toast.makeText(this@MainActivity, "초기화 완료", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // [입력 버튼] 클릭 시 데이터 입력
        btnInsert.setOnClickListener {
            val name = editName.text.toString().trim()
            val count = editCount.text.toString().toIntOrNull()

            // 유효성 검사
            if (name.isBlank() || count == null) {
                Toast.makeText(this, "이름과 인원을 올바르게 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // User 객체 생성 (id는 auto-generate)
            val user = User(id = 0, groupName = name, groupMemberCount = count)

            lifecycleScope.launch {
                db.groupDao().insert(user)  // DB에 데이터 삽입
                runOnUiThread {
                    editName.text.clear()
                    editCount.text.clear()
                    Toast.makeText(this@MainActivity, "저장 완료", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // [조회 버튼] 기능 제거 → LiveData로 자동 갱신
        btnSelect.isEnabled = false
        btnSelect.text = "LiveData 자동조회"

        // LiveData 관찰하여 데이터 변경 시 UI 자동 갱신
        db.groupDao().getAllGroups().observe(this, Observer { groups ->
            clearResultContainer()  // 이전 결과 삭제

            // 조회된 각 그룹 정보를 화면에 동적으로 추가
            for (group in groups) {
                val row = LinearLayout(this@MainActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                }

                // 그룹 이름 텍스트뷰
                val nameView = TextView(this@MainActivity).apply {
                    text = group.groupName ?: "(이름없음)"
                    textSize = 16f
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                }

                // 인원 수 텍스트뷰
                val countView = TextView(this@MainActivity).apply {
                    text = group.groupMemberCount.toString()
                    textSize = 16f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                // 결과 행에 추가
                row.addView(nameView)
                row.addView(countView)
                resultContainer.addView(row)
            }
        })
    }

    // 결과 영역에서 제목만 남기고 나머지 모두 삭제
    private fun clearResultContainer() {
        if (resultContainer.childCount > 1) {
            resultContainer.removeViews(1, resultContainer.childCount - 1)
        }
    }
}
