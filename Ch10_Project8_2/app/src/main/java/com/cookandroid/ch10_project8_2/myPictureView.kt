package com.cookandroid.ch10_project8_2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/*
* 사용자 정의 이미지 View
* 파일 경로를 기반으로 이미지를 그리는 역할
* 외부에서 전달받은 이미지 경로를 비트맵으로 읽어와 화면에 직접 canvas로 그림
**/
// 생성자에 Context와 AttributeSet를 받음으로써, XML에서도 사용 가능
class MyPictureView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // 외부에서 이미지 경로를 설정하면 내부에서 자동으로 bitmap 객체를 생성
    var imagePath: String? = null
        set(value) {
            field = value
            // 이미지가 변경되면 다시 그리도록 요청
            bitmap = if (value != null) BitmapFactory.decodeFile(value) else null
            invalidate()
        }

    // decode된 비트맵을 보관
    private var bitmap: android.graphics.Bitmap? = null

    // 비트맵을 그리는 부분
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        bitmap?.let {
            // 원하는 비율로 스케일 설정 가능
            canvas.scale(2f, 2f, 0f, 0f)
            canvas.drawBitmap(it, 0f, 0f, null)
        }
    }

    // 이 View가 화면에서 사라질 때 호출되는 함수
    // 비트맵은 메모리가 많이 사용되기 때문에 명시적으로 해제하는게 중요함
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        bitmap?.recycle() // View가 사라질 때 비트맵 메모리 해제
        bitmap = null
    }
}
