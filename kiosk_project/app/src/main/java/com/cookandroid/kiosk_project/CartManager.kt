package com.cookandroid.kiosk_project

import com.cookandroid.kiosk_project.model.CartItem
import com.cookandroid.kiosk_project.model.MenuItem
import com.cookandroid.kiosk_project.PersonalOption

object CartManager {
    val cartItems = mutableListOf<CartItem>()

    fun addItem(menuItem: MenuItem, option: PersonalOption) {
        val existing = cartItems.find {
            it.menuItem.name == menuItem.name && it.personalOption == option
        }

        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(CartItem(menuItem, 1, option))
        }
    }

    fun removeItem(menuItem: MenuItem) {
        cartItems.removeAll { it.menuItem == menuItem }
    }

    fun increaseQuantity(item: CartItem) {
        item.quantity++
    }

    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) item.quantity-- else cartItems.remove(item)
    }

    fun clearCart() {
        cartItems.clear()
    }

    // ✅ 총 금액 계산 함수 추가
    fun getTotalPrice(): Int {
        return cartItems.sumOf { it.menuItem.price * it.quantity }
    }
}

