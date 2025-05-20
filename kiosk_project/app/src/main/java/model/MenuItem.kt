package com.cookandroid.kiosk_project.model

// 메뉴 아이템의 기본 정보 클래스
data class MenuItem(
    val name: String,        // 메뉴 이름
    val price: Int,          // 가격
    val imageResId: Int      // 이미지 리소스 ID
)
