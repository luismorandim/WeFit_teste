package com.example.wefit_teste.presentation.home

enum class HomeTab(val index: Int) {
    Cart(0),
    Home(1),
    Profile(2);

    companion object {
        fun fromIndex(index: Int): HomeTab {
            return values().find { it.index == index }
                ?: throw IllegalArgumentException("Tab inválido: $index")
        }
    }
}
