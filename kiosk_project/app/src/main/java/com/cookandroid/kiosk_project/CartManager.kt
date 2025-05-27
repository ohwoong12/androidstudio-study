package com.cookandroid.kiosk_project

import com.cookandroid.kiosk_project.model.CartItem
import com.cookandroid.kiosk_project.model.MenuItem
import com.cookandroid.kiosk_project.PersonalOption

// 장바구니 항목 관리 객체 (싱글톤)
object CartManager {
    // 현재 장부가니에 담긴 아이템 리스트
    val cartItems = mutableListOf<CartItem>()

    /**
     * 장바구니에 아이템 추가
     * 이미 같은 메뉴에 동일 옵션이 있으면 수량 증가
     * 없으면 새로 추가
     */
    fun addItem(menuItem: MenuItem, option: PersonalOption) {
        // 동일 메뉴/옵션인지 검색
        val existing = cartItems.find {
            it.menuItem.name == menuItem.name && it.personalOption == option
        }

        if (existing != null) {
            existing.quantity++ // 이미 있으면 수량만 1 증가
        } else {
            cartItems.add(CartItem(menuItem, 1, option))    // 없으면 새 항목 추가 (기본 수량 1)
        }
    }
    
    // 장바구니에서 특정 메뉴 완전 삭제하는 함수
    fun removeItem(menuItem: MenuItem) {
        cartItems.removeAll { it.menuItem == menuItem }
    }

    // 수량 1 증가하는 함수
    fun increaseQuantity(item: CartItem) {
        item.quantity++
    }

    // 수량 1 감소, 1보다 작아지면 항목 삭제하는 함수
    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1){
            item.quantity--
        } else cartItems.remove(item)
    }

    // 장바구니 비우는 함수
    fun clearCart() {
        cartItems.clear()
    }

    // 현재 장바구니에 담기 모든 항목 가격 합산(총 금액)하는 함수
    fun getTotalPrice(): Int {
        return cartItems.sumOf { it.menuItem.price * it.quantity }
    }
}
