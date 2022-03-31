package com.android.example.my_sudoku.model

import java.lang.Exception

enum class NumberEnum(val rawValue: Int) {
    One(0x001),
    Two(0x002),
    Three(0x004),
    Four(0x008),
    Five(0x010),
    Six(0x020),
    Seven(0x040),
    Eight(0x080),
    Nine(0x1000)
}

fun Int.toFlag(): NumberEnum {
    return when(this) {
        1 -> NumberEnum.One
        2 -> NumberEnum.Two
        3 -> NumberEnum.Three
        4 -> NumberEnum.Four
        5 -> NumberEnum.Five
        6 -> NumberEnum.Six
        7 -> NumberEnum.Seven
        8 -> NumberEnum.Eight
        9 -> NumberEnum.Nine
        else -> throw Exception()
    }
}
