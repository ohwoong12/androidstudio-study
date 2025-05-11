package com.cookandroid.ch10_project8_2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import java.io.File

class MainActivity : AppCompatActivity() {

    private val REQUEST_FOLDER = 1001               // SAF로 폴더를 선택할 때 구분용으로 쓰는 요청 코드 (임의의 숫자 사용 가능)
    private lateinit var pictureView: MyPictureView // 이미지를 출력할 커스텀 뷰
    private val imageUris = mutableListOf<Uri>()    // 폴더 내에 있는 이미지들의 URI를 저장하는 리스트
    private var currentIndex = 0                    // 현재 어떤 이미지가 선택되었는지를 나타내는 index

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 버튼과 View xml 파일과 연결
        val btnSelect = findViewById<Button>(R.id.btnSelectImage)
        val btnPrevious = findViewById<Button>(R.id.btnPrevious)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val imageContainer = findViewById<FrameLayout>(R.id.imageContainer)

        // 코틀린 코드에서 MyPictureView를 직접 생성 후 XML의 FrameLayout에 추가하여 화면에 출력
        pictureView = MyPictureView(this, null)
        imageContainer.addView(pictureView)

        // "선택하기" 버튼 클릭 시 -> SAF로 폴더 선택
        // 사용자가 폴더 선택 시 onActivityResult()가 호출 됨
        btnSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
            startActivityForResult(intent, REQUEST_FOLDER)
        }

        // "이전 그림" 버튼 클릭 시 -> 리스트의 이전 이미지 출력
        btnPrevious.setOnClickListener {
            if (imageUris.isNotEmpty()) {
                // 인덱스를 감소시켜 이전 이미지로 이동 (첫 번째에서 마지막으로 이동)
                currentIndex = (currentIndex - 1 + imageUris.size) % imageUris.size
                showImage(imageUris[currentIndex])
            }
        }

        // "다음 그림" 버튼 클릭 시 -> 리스트의 다음 이미지 출력
        btnNext.setOnClickListener {
            if (imageUris.isNotEmpty()) {
                // 인덱스를 증가시켜 다음 이미지로 이동 (마지막에서 다시 첫 번째로 이동)
                currentIndex = (currentIndex + 1) % imageUris.size
                showImage(imageUris[currentIndex])
            }
        }
    }

    // SAF에서 폴더 선택이 끝났을 때 실행되는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // SAF에서 폴더를 정상적으로 선택한 경우에만 실행하도록 하는 if문
        if (requestCode == REQUEST_FOLDER && resultCode == Activity.RESULT_OK) {
            val treeUri: Uri = data?.data ?: return // 선택된 폴더의 경로(Uri)를 가져옴, 만약 null이면 중단

            // 선택한 폴더에 대해 지속적인 읽기 권한 부여 (재실행시에도 유지)
            contentResolver.takePersistableUriPermission(
                treeUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )

            // 이미지 목록 초기화 (기존 자료 삭제)
            imageUris.clear()

            // 선택한 폴더를 documentFile 객체로 변환
            val SelectedDir = DocumentFile.fromTreeUri(this, treeUri)

            // 폴더 내부의 파일들을 검사하여 파일인지 확인하고, 이미지까지 확인하는 if문
            if (SelectedDir != null && SelectedDir.isDirectory) {
                // 하위 파일들을 반복하면서 이미지 파일만 수집
                for (file in SelectedDir.listFiles()) {
                    // 파일인지 확인하고, 이미지인지 MIME 타입으로 확인
                    if (file.isFile && file.type?.startsWith("image/") == true) {
                        imageUris.add(file.uri) // 조건을 만족하는 파일의 Uri만 리스트에 저장
                    }
                }
            }

            // 이미지가 한 장이라도 있다면 첫 번째 이미지를 표시
            if (imageUris.isNotEmpty()) {
                currentIndex = 0
                showImage(imageUris[currentIndex])
            } else {
                // 만약 폴더 내에 이미지가 한장도 없다면 토스트 메시지 출력
                Toast.makeText(this, "이미지를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /* 선택된 이미지 Uri를 실제 경로로 복사한 후 MyPictureView에 표시하는 함수 **/
    private fun showImage(uri: Uri) {
        val file = File(cacheDir, "temp.jpg")   // 앱 캐시 디렉토리에 임시 저장
        
        // SAF에서 받은 Uri -> 실제 파일 복사
        // SAF로 가져온 이미지는 직접 열 수 없기 때문에, 앱 내부로 복사하는 과정 필요
        contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output -> input.copyTo(output) }  // input.copyTo(output) = 파일 복사
        }
        // MyPictureView에 이미지 경로 전달 → onDraw()에서 비트맵을 그려줌
        pictureView.imagePath = file.absolutePath
    }
}
