package com.cookandroid.kiosk_project.model

import com.cookandroid.kiosk_project.PersonalOption

data class CartItem(
    val menuItem: MenuItem,
    var quantity: Int = 1,
    val personalOption: PersonalOption = PersonalOption(
        temperature = "HOT",
        shot = "기본",
        ice = "보통",
        sugar = "보통",
        cup = "매장"
    )
)
